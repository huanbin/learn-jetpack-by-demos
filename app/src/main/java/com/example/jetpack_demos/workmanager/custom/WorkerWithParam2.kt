package com.example.jetpack_demos.workmanager.custom

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationChannelGroupCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.jetpack_demos.R
import kotlinx.coroutines.delay
import java.util.UUID

/**
 * 前台长时间执行的任务
 */
class WorkerWithParam2(private val name: String, appContext: Context, params: WorkerParameters) :
    CoroutineWorker(
        appContext,
        params
    ) {
    override suspend fun doWork(): Result {
        return try {
            setForeground(getForegroundInfo())
            println("wait......................")
            delay(1000 * 60)
            Result.success(workDataOf("data" to "get data success,name=$name"))
        } catch (error: IllegalStateException) {
            Result.failure()
        }
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {

        val channel_group_id = "channel_syxz_group_id_100"
        createNotificationGroupId(applicationContext, channel_group_id)

        val channel_name = "channel_syxz"
        val channel_id_100 = "channel_id_101"
        createNotificationChannelId(
            applicationContext,
            channel_id_100,
            channel_name,
            channel_group_id
        )
        val notification = createNotification(applicationContext, channel_id_100, id)
        //也可以在这里指定前台服务类型
        return ForegroundInfo(100, notification)
    }

    private fun createNotificationGroupId(context: Context, channelGroupId: String) {
        val channelGroup = NotificationChannelGroupCompat.Builder(channelGroupId)
            .setName("商业新知channel—group")
            .build()
        NotificationManagerCompat.from(context).createNotificationChannelGroup(channelGroup)
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
            .setDescription("syxz通知用户正在执行重要的工作，启动前台服务")
            .setLightsEnabled(true)
            .setLightColor(Color.argb(255, 0, 255, 0))
            .setVibrationEnabled(true)
            .setVibrationPattern(longArrayOf(1000, 2000, 3000, 4000))
            .build()
        NotificationManagerCompat.from(context).createNotificationChannel(channelCompat)
    }

    private fun createNotification(
        context: Context,
        channelId: String,
        workRequestId: UUID
    ): Notification {

        return NotificationCompat.Builder(context, channelId)
            .setTicker("前台服务正在上传")
            //标准通知中的第一行
            .setContentTitle("开启前台服务")
            //标准通知中的第二行
            .setContentText("content text用于执行加急任务，上传个人信息，请您不要取消，否则上传会失败。")
            //标准通知中的第三行
            .setSubText("subtext用于执行加急任务，上传个人信息...")
            //.setContentInfo("用于执行加急任务，上传个人信息...")
            //重要的引起用户注意的通知，一般不要用
//            .setFullScreenIntent()
            //当没有设置action，系统会自动添加上下文action等
            .setAllowSystemGeneratedContextualActions(true)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("big text用于执行加急任务，上传个人信息，请您不要取消，否则上传会失败。")
            )
            //.setAutoCancel()
            .setSmallIcon(R.drawable.ic_stat_name_1)
            //设置类型，用于给os系统将通知进行排名和过滤
            .setCategory(Notification.CATEGORY_MESSAGE)
            //很重要
            .setOngoing(true)
            //.setOnlyAlertOnce(true)
            //老版本设置通知的优先级，8.0之后直接在Channel中设置
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(
                R.drawable.ic_stat_name,
                "取消",
                WorkManager.getInstance(context).createCancelPendingIntent(workRequestId)
            )
            .build()
    }
}