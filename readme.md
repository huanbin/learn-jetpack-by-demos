### 开始为学习Jetpack库而创建的练手demo。

### 然后发现Android现代化开发，需要了解现代架构组件，于是学习了架构组件。

### 慢慢学习Android系统引入的新特性，比如：预测返回手势、包的可见性、多窗口、多设备（大屏幕尺寸）、屏保、壁纸、App Widget（小构件）

### 还有一些概念，虽然存在很久，但是现在对于它的理解不一样，明白了前因后果。

# 1.应用kotlin DSL 构建脚本

# 2.开始jetpack---》workmanager，可靠的持久任务

# 常见问题

    1.为什么Android应用程序在主线程上的操作不能超过16ms？为什么这样就会出现UI卡顿甚至ANR?
    这是因为现代的Android手机硬件支持每秒刷新60帧，这样能够很好的保证人眼视觉的流畅性。
    应用程序为了满足这个条件，在UI线程上的操作的时间就必须在1000ms/60=16.6ms内，因为除了我们需要的操作时间之外，系统还需要执行时间，否则这样就很容易出现卡顿现象。
    
    2.为什么出现64K问题?
    DEX ----> a single Dalvik Executable (DEX) bytecode file,Dalvik上可执行的字节文件。
    实际上我们的App（Apk文件）是包含多个dex文件，dalvik执行dex文件运行我们的App。而dex文件规定单个dex文件中能够引用的方法数为65536，这其中包括Android Framework methods、Library methods以及我们自己的代码中的方法数的总和。
    默认情况App打包Apk文件只包含一个dex文件，为了解决65536问题，需要在Android5.0及以上启用Multidex。
    