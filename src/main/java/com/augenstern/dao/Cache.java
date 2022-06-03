package com.augenstern.dao;

import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 缓存空间
 */
@CacheNamespace(implementation = org.mybatis.caches.redis.RedisCache.class)
public interface Cache {
}
