## 项目背景以及思路

```java
// 项目背景
有些文章的链接太长（比如微信推文、github的仓库文章），但是又需要分享给出去，这时候就需要短链一下，然后分享给朋友，国内的短链都需要审核域名实属麻烦，所以写一个方便自己

//项目思路：（更多细节自行去仓库看源码）
1. 用户在界面填写一个需要短链的链接，然后后台判断链接是否是http、或者https链接，如果符合则生成一个结构是【个人服务器的IP:项目端口号/UUID】，存入缓存（1天时间），并且持久化到数据库。每次用户进入短链时自动将短链放入缓存中加快响应速度，而不是都查数据库
```



## 技术栈

**后端：** mybatisplus、ehcache、springboot


**前端：** vue3、elementui-plus



## 在线演示

[个人服务器部署演示](http://qq.linruchang.work:8888)



## 界面

**功能首页**
![在这里插入图片描述](https://img-blog.csdnimg.cn/f8ce02e5f3d1462caad02d3b035e21eb.png)




**短链生成**
![在这里插入图片描述](https://img-blog.csdnimg.cn/131fe67363e1406d92330e0c0069d02b.png)


**合法短链跳转**
[上图的短链](http://qq.linruchang.work:8888/830fd28eeb3849608e92c2f13b469ace)
![在这里插入图片描述](https://img-blog.csdnimg.cn/21fa32130078463381cfc1d9eb9f5013.png)

&emsp;
**不合法短链跳转**
[上图的短链](http://qq.linruchang.work:8888/830fd28eeb3849608e92c2f13b469acefsadfdsfsdfds)

![在这里插入图片描述](https://img-blog.csdnimg.cn/0ac1bc2f4816457e91bd3170c36cb07c.png)
