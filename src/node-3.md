卖家端

1. 扫码登录
    数据库存储openid，openid即密码
    验证身份，存储信息
2. 登出
    

## 分布式系统下的session
    
1. 什么是分布式系统?
    
    指在支持应用程序和服务的开发，可以利用物理架构由多个自治的处理元素，不共享主内存，
    但通过网络发送消息合作。
        
                -- Leslie Lamport （2013年图灵奖获得者）
                
2. 三个特点和三个概念 
    
    * 多节点               
    * 消息通信
    * 不共享内存 
    
    * 分布式系统(distributed system)
    * 集群(cluster)
    * 分布式(并行)计算(distributed computing)
     
3. 分布式系统与集群
   
   * 区别和联系
        分布式： 不同功能模块的节点
        集群： 是相同业务功能的节点
   



## session
        
1. 广义的session
    会话控制
    理解为一种保存key-value的机制
    
2. 从key的方面看
    * sessionId
    * token
   
3. 
    
    