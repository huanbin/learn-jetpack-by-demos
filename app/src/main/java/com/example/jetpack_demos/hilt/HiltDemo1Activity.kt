package com.example.jetpack_demos.hilt

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_demos.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * 1.声明Application类，添加注解@HiltAndroidApp,触发Hilt为应用生成一个应用级别的依赖管理容器
 * 2.声明需要依赖，添加注解@AndroidEntryPoint（为每一个类生成一个Hilt组件），Hilt能为该注解的所在类提供依赖。特殊情况：当一个类注解了@AndroidEntryPoint，那么依赖这个类的其他类，也需要添加@AndroidEntryPoint。
 * 3.注入依赖项，@Inject表示属性注入，Hilt属性注入不能是private属性，private会导致编译错误
 *
 * Hilt binding----》提供绑定信息，用于在运行时，通过生成的代码获取依赖，inject是最普通的一种，可以用于构造器和属性注入，但这个类必须是我们自己的可控的，也就是说需要修改类的代码。
 *
 * Inject----------》属性注入、构造器注入、方法注入，它有2个作用，绑定注入和注入实例
 *
 * Hilt module-----》提供绑定的一种方式，@Module和@InstallIn必须一起使用。该方式比inject方式灵活，比如不能通过构造器注入接口、不能通过构造器注注入来自第三方库中的类情况下都可以采用这种方式，并且Hilt Module提供的依赖在与该Module的InstallIn注解所指定类的相应Hilt组件中都可用。
 *                   也就是说，这种绑定方式，可以更灵活的复用
 *                   Module---》声明Hilt Module，抽象类。
 *                   InstallIn---》声明Hilt Module中的绑定在哪里可用，与绑定的范围需要保持一致
 *                   Binds---》声明Hilt Module中的绑定接口的实例，提供依赖。通过声明抽象函数，函数返回类型表示为什么类型接口提供实例，函数参数表示提供的具体依赖实例
 *                   Provides---》声明Hilt Module中的绑定第三方库中类的实例，提供依赖。
 *
 * Hilt组件树层次结构----》最上的组件生命周期最大，是Application级别，下面的层能够访问上面层的组件内部的绑定
 *
 * Hilt Component内部是Hilt Module，Hilt Module内部是绑定，绑定就是为提供需要的依赖实例。
 * Dagger Component其实就是一个依赖图。
 */
@AndroidEntryPoint
class HiltDemo1Activity : AppCompatActivity() {

    @Inject
    lateinit var inject1Demo: Inject1Demo

    @Inject
    lateinit var inject2Demo: Inject1Demo


    @Inject
    lateinit var webService: WebService

    @Inject
    lateinit var wrapperRequest: WrapperRequest

    /**
     * 一个类型有多个绑定，定义@Qualifier注解，来区分不同的实例
     * 如下：filerTool1和filerTool2
     */
    @OneFilterTool
    @Inject
    lateinit var filerTool1: FilterTool

    @OneFilterTool
    @Inject
    lateinit var filerTool11: FilterTool

    @TwoFilterTool
    @Inject
    lateinit var filerTool2: FilterTool

    //这是因为Hilt提供了带参数的ViewModel的构造工厂，否则必须手动实现ViewModelProvider.Factory接口
    val viewModleDemo1 by viewModels<HiltViewModleDemo1>()


    @Inject
    lateinit var exampleContentProvider: ExampleContentProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt_demo1)

        //1
        inject1Demo.log()
        //
        println("=========inject================")
        println(inject1Demo)
        println(inject2Demo)

        //2
        println("============Binds=============")
        val doService = webService.doService()
        println(doService)

        //3
        println(wrapperRequest)
        println("=========filter================")
        //4
        println(filerTool1)
        println(filerTool11)
        println(filerTool2)

        val tvMsg = findViewById<TextView>(R.id.tvMsg)

        viewModleDemo1.msg.observe(this) { msg ->
            tvMsg.text = msg
        }

        findViewById<Button>(R.id.btDoService).setOnClickListener {
//            viewModleDemo1.getMessage()

            val message = exampleContentProvider.getMessage()
            viewModleDemo1.msg.value = message
        }


    }
}