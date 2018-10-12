## 手工获取openID小结

    1.设置域名
        微信白名单校验，有一个内网穿透的工具。
        有一个校验文件，需要放到网站的根目录下
    
    2.获取code
         有一个scope参数，可以穿base 也可以穿userinfo
         2.1 base方式：用户无感知，拿到的信息少
         2.2 userinfo方式，会有弹窗让用户授权，获取信息较多
         code只能用一次。
         
    3.获取access_token
        可以获取openid
     
## 第三方SDK
        
    已经有轮子
    
    github：Wechat-Group/weixin-java-tools   