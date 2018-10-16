## Redis缓存
 
1. 命中
2. 失效
3. 更新


使用了AOP

    @Cacheable
    @CachePut
    @CacheEvict
    
    存储的对象要实现序列化
    结合业务场景，避免滥用