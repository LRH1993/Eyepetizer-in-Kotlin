# Eyepetizer-in-Kotlin
>Google在今年的IO大会上宣布，将Kotlin作为Android开发的一级语言。作为紧跟潮流的弄潮儿，对kotlin稍做了解后，发现其有优秀的特性，所以就开始了学习，而Eyepetizer-in-Kotlin便是对kotlin进行学习后的阶段性成果。

## 一、screenshot
### 1.启动界面
![](http://upload-images.jianshu.io/upload_images/3985563-90a1eea8d8618042.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
### 2.主界面
![](http://upload-images.jianshu.io/upload_images/3985563-a4246db83f83587d.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](http://upload-images.jianshu.io/upload_images/3985563-4afb1d9c8593d5f2.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
### 3.视频界面
![](http://upload-images.jianshu.io/upload_images/3985563-42c4219dc793f11c.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
### 4.搜索界面
![](http://upload-images.jianshu.io/upload_images/3985563-d04d20b1b8f2e993.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
### 5.缓存界面
![](http://upload-images.jianshu.io/upload_images/3985563-7346a9660fb778fc.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 二、app介绍
### 1.数据来源
Eyepetizer-in-Kotlin的所有数据来源自开眼视频，不涉及任何商业用途，有如侵权，立即删除。

具体api接口可以参考项目中的Api类。
### 2.功能
● 含有开眼视频每日推荐、热门及分类小视频  
● 支持视频播放，包含全屏播放等特性  
● 支持关键词搜索，想看什么看什么  
● 记录历史观看，以及缓存视频，本地播放等功能  
### 3.特点
作为学习kotlin的一款app，在撸代码的过程中学习kotlin的语法及特性。

Eyepetizer-in-Kotlin适合初学者作为学习kotlin语言的一个项目，可以在做项目的同时体验到kotlin语言的优点。

## 三、kotlin特性
在做Eyepetizer-in-Kotlin的过程中，了解到了许多kotlin的特性，用在日常开发中，大大提高了开发效率，下面就简单介绍下在项目中用到的几个重要的kotlin特性。

##### (1) 和findViewById说再见
我们可以直接使用xml中对应的id，并且import对应的文件，不在需要findViewById
```
import kotlinx.android.synthetic.main.activity_watch.*
.........
tv_hint.visibility = View.VISIBLE
tv_hint.text = "告别findViewById"
```
##### (2) 延迟加载
延迟加载有几个好处。首先由于加载时机推迟到了变量被访问时，因此它可以提高应用的启动速度。其次，这样的延迟加载也有更高的内存效率。
```
val mModel: FindDetailModel by lazy {
        FindDetailModel()
    }
```
当第一次使用mModle的时候进行初始化
```
lateinit var mAdapter: DownloadAdapter
```
在任何想进行初始化的位置进行初始化
##### (3) Lambdas 表达式
Lambdas 表达式在减少源文件中代码的总行数的同时，也支持函数式编程。

使用 lambdas 表示式时，onClickListener 的用法如下：
```
holder?.itemView?.setOnClickListener {
            var keyWord = list?.get(position)
            var intent : Intent = Intent(context,ResultActivity::class.java)
            intent.putExtra("keyWord",keyWord)
            context?.startActivity(intent)
            mDialogListener?.onDismiss()
        }
```
##### (4) 数据类
数据类简化了类的定义，自动为类添加equals()，hashCode()，copy() 和toString() 方法。它明确定义了 model 类的意图，以及应该包含什么内容，同时将纯数据与业务逻辑分离开来。
```
data class VideoBean(var feed:String?,var title:String?,var description:String?,
                     var duration: Long?,var playUrl: String?,var category: String?,
                     var blurred : String?,var collect:Int?,var share:Int?,var reply:Int?,var time:Long) : Parcelable,Serializable {}
```
就是如此简单，对比以前动辄几百行的数据类，简洁了太多。如果想实现序列化Parcelable，可以下载支持kotlin序列化的插件，一键实现，非常方便。
##### (5) 集合过滤
通过使用 Kotlin 的集合过滤功能，我们可以使代码变得更清晰简洁。
```
bean.issueList!!
                .flatMap { it.itemList!! }
                .filter { it.type.equals("video") }
                .forEach { mList.add(it) }
```
通过以上过滤，便可以得到我们想要的内容。
##### (6) 扩展
扩展的好处在于它允许我们为一个类添加功能同时无需继承它。例如，你是否曾经希望 Context 有某些方法，比如 showToast()？使用扩展，你可以很容易实现这个功能：
```
fun Context.showToast(message: String) : Toast {
    var toast : Toast = Toast.makeText(this,message,Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER,0,0)
    toast.show()
    return toast
}
```
##### (7) 其他特性
**字符串**

Kotlin在字符串之中可以使用变量，相对与在Java中的字符串拼接，更让人感觉到舒服。
```
holder?.tv_detail?.text = "发布于 $category / $realMinute:$realSecond"
```
**when**

Kotlin中when的出现替代了switch，但其功能更加强大。
```
 override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_watch ->{
                var intent = Intent(activity,WatchActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_advise ->{
                var intent = Intent(activity,AdviseActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_save ->{
                var intent = Intent(activity,CacheActivity::class.java)
                startActivity(intent)
            }
        }
    }
```

当然Kotlin的特性不止如此，还有更多高阶的特性如：高阶函数, Anko等，这些特性还需进一步学习应用
## 四、为什么要学Kotlin?
关于为什么要学Kotlin，在Google IO大会帮把kotlin作为一级开发语言后就已经有了很多的讨论。
当时学习的必要性还不明确，过了几个月之后，我们再来看一下学习的必要性。
#### 1.语言层面
通过以上分析，我们可以知道kotlin对于android开发的优势，同时kotlin也是基于JVM的一门静态语言，与Java完美兼容，不存在项目迁移等问题。除此之外，学习成本也比较低，很好入门。当然如果想要达到熟练程度，还需继续练习。
##### 2.形势层面
国外：Pinterest、Evernote、Uber等企业已经开始应用，Google也在一些小项目中应用kotlin，Google一些专家非常看好Kotlin。

国内：据我所知，美团已经开始使用kotlin。一些大型互联网公司由于业务导向，开发压力重，不敢轻易使用kotlin，不过它们已经开始跃跃欲试，着手在新的项目中使用kotlin。

讲了这么多，学习kotlin虽然不是必须的，但是应对趋势发展，并且学习成本不高，那么多掌握一门技能，百利而无一害。


