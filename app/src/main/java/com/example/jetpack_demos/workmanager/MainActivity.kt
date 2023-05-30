package com.example.jetpack_demos.workmanager

import android.Manifest
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationChannelGroupCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.WindowCompat
import androidx.work.ArrayCreatingInputMerger
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.multiprocess.RemoteWorkerService
import androidx.work.workDataOf
import com.example.jetpack_demos.R
import com.example.jetpack_demos.databinding.ActivityMainBinding
import com.example.jetpack_demos.workmanager.custom.WorkerWithParam1
import com.example.jetpack_demos.workmanager.custom.WorkerWithParam2
import java.util.concurrent.TimeUnit


const val IMAGE_URL_KEY = "img_url"
const val IMAGE_URL =
    "https://img0.baidu.com/it/u=3058869686,2347697315&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //work input，输入数据
    val data1 = workDataOf(IMAGE_URL_KEY to "$IMAGE_URL-1")
    val data2 = workDataOf(IMAGE_URL_KEY to "$IMAGE_URL-2")
    val data3 = workDataOf(IMAGE_URL_KEY to "$IMAGE_URL-3")

    //创建约束
    val constraints = Constraints.Builder()
        //充足的电量
        .setRequiresBatteryNotLow(true)
        //是否需要在充电
        .setRequiresCharging(false)
        //是否需要设备空闲状态
        .setRequiresDeviceIdle(false)
        //需要的网络类型
        .setRequiredNetworkType(NetworkType.CONNECTED)
        //充足的存储空间
        .setRequiresStorageNotLow(true)
        //.addContentUriTrigger()
        //.setTriggerContentUpdateDelay()
        //.setTriggerContentMaxDelay()
        .build()

    //创建work request，定义work怎么执行以及何时执行
    val uploadRequest = OneTimeWorkRequestBuilder<UploadWorker>()
        //.setInputMerger(OverwritingInputMerger::class.java)
        //.setInputMerger(ArrayCreatingInputMerger::class.java)
        .setConstraints(constraints)
        .build()

    val filterRequest1 = OneTimeWorkRequestBuilder<FilterWorker>()
        //设置回退策略
        .setBackoffCriteria(BackoffPolicy.LINEAR, 10L, TimeUnit.SECONDS)
        .addTag("filter1")
        .setInputData(data1)
        .build()
    val filterRequest2 = OneTimeWorkRequestBuilder<FilterWorker>()
        //设置回退策略
        .setBackoffCriteria(BackoffPolicy.LINEAR, 10L, TimeUnit.SECONDS)
        .addTag("filter2")
        .setInputData(data2)
        .build()
    val filterRequest3 = OneTimeWorkRequestBuilder<FilterWorker>()
        //设置回退策略
        .setBackoffCriteria(BackoffPolicy.LINEAR, 10L, TimeUnit.SECONDS)
        .addTag("filter3")
        .setInputData(data3)
        .build()
    val compressRequest = OneTimeWorkRequestBuilder<CompressWorker>()
        //让filter并行执行，compress获得数据 IMAGE_URL_KEY to arrayOf(上面的3个image url)
        .setInputMerger(ArrayCreatingInputMerger::class.java)
        .build()

    val periodRequest = PeriodicWorkRequest.Builder(PeriodWorker::class.java, 15L, TimeUnit.MINUTES)
        .setConstraints(Constraints.Builder().setRequiresCharging(true).build())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        //监听任务状态
        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(filterRequest2.id)
            .observe(this) {
                val progress = it.progress
                binding.tvState.text = "${progress.getInt("progress", 0)}${it.state}"
            }


        //启动就执行周期任务（不会重复启动）
        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                "unique task",
                ExistingPeriodicWorkPolicy.KEEP,
                periodRequest
            )

        val PACKAGE_NAME = "com.example.background.multiprocess"
        val serviceName = RemoteWorkerService::class.java.name
        val componentName = ComponentName(PACKAGE_NAME, serviceName)
        print("package name=$PACKAGE_NAME,class name=$serviceName")
        print("package name=${componentName.packageName},class name=${componentName.className}")


        initNotification()
    }


    fun startWork(view: View) {
        //简单提交work request
        //WorkManager.getInstance(applicationContext).enqueue(uploadRequest)
        //执行任务链work
        WorkManager.getInstance(applicationContext)
            .beginWith(listOf(filterRequest1, filterRequest2, filterRequest3))
            .then(compressRequest)
            .then(uploadRequest)
            .enqueue()
    }

    fun stopWork(view: View) {
        WorkManager.getInstance(applicationContext).cancelWorkById(request.id)
    }

    fun startWorkWithParam(view: View) {
        val request = OneTimeWorkRequestBuilder<WorkerWithParam1>()
            .build()
        WorkManager.getInstance(this@MainActivity)
            .enqueueUniqueWork("uniqueWithParam", ExistingWorkPolicy.KEEP, request)
    }

    val request = OneTimeWorkRequestBuilder<WorkerWithParam2>()
        //设置为加急任务，同时设置超出配额时策略，回退为常规的work request。
        //DROP_WORK_REQUEST:丢弃request
        .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
        .build()


    fun updateWork(view: View) {
        val updateWorkRequest = OneTimeWorkRequestBuilder<WorkerWithParam2>()
            .setId(request.id)
            .addTag("extag1")
            .build()

        val updateWork = WorkManager.getInstance(view.context).updateWork(updateWorkRequest)

        val get = updateWork.get()
        println("update result=$get")
    }

    fun startWorkWithExpedited(view: View) {
        WorkManager.getInstance(view.context.applicationContext)
            .enqueueUniqueWork("exDemo1", ExistingWorkPolicy.REPLACE, request)
    }

    fun allowPostNotification(view: View) {
//        if (!NotificationManagerCompat.from(view.context).areNotificationsEnabled()) {
//            println("申请发送通知")
//            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 2000)
//        } else {
//            println("可以发送通知")
//        }
        requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 2000)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        println("requestCode=$requestCode")
        println("permissions=${permissions.joinToString()}")
        println("grantResults=${grantResults.joinToString()}")
    }

    private fun createNotificationGroupId(context: Context, channelGroupId: String) {
        val channelGroup = NotificationChannelGroupCompat.Builder(channelGroupId)
            .setName("商业新知channel—group1")
            .build()
        NotificationManagerCompat.from(context).createNotificationChannelGroup(channelGroup)
    }

    val channel_id_100 = "channel_id_102"
    private fun initNotification() {
        val channel_group_id = "channel_syxz_group_id_101"
        createNotificationGroupId(applicationContext, channel_group_id)
        val channel_name = "channel_syxz"
        createNotificationChannelId(
            applicationContext,
            channel_id_100,
            channel_name,
            channel_group_id
        )
    }

    public fun sendNotification(view: View) {
        val notification = NotificationCompat.Builder(view.context, channel_id_100)
            .setSmallIcon(R.drawable.ic_stat_name_1)
            .setContentTitle("我的通知")
            .setContentText("我的内容")
//            .addAction(R.drawable.ic_stat_name,"Hello Ni",)
            .build()
        NotificationManagerCompat.from(view.context).notify("syxz", 0, notification)
    }

    private fun createNotificationChannelId(
        context: Context,
        channelId: String,
        channelName: String,
        groupId: String
    ) {
        val channelCompat = NotificationChannelCompat
            //importance用于
            .Builder(channelId, NotificationManager.IMPORTANCE_HIGH)
            .setName(channelName)
            .setGroup(groupId)
            .setDescription("syxz重要的工作通知")
            .setLightsEnabled(true)
            .setLightColor(Color.argb(255, 0, 255, 0))
            .setVibrationEnabled(true)
            .setVibrationPattern(longArrayOf(1000, 2000, 3000, 4000))
            .build()
        NotificationManagerCompat.from(context).createNotificationChannel(channelCompat)
    }
}