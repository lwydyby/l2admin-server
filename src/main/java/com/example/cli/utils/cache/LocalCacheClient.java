package com.example.cli.utils.cache;

import com.example.cli.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地cache（程序变量）用于缓存token并续期token，分布式环境请使用memcache或redis <br/>
 * 分布式环境下请慎用
 *
 */
@Component
public class LocalCacheClient implements CacheClient<User> {
    // 缓存map
    private static Map<String, User> cacheMap = new ConcurrentHashMap<>();
    // 缓存有效期map
    private static Map<String, Long> expireTimeMap = new ConcurrentHashMap<>();


    /**
     * 获取指定的value，如果key不存在或者已过期，则返回null
     *
     * @param key
     * @return
     */
    public User get(String key) {
        if (!cacheMap.containsKey(key)) {
            return null;
        }
        if (expireTimeMap.containsKey(key)) {
            if (expireTimeMap.get(key) < System.currentTimeMillis()) { // 缓存失效，已过期
                return null;
            }
        }else {
            return null;
        }
        return cacheMap.get(key);
    }


    /**
     * 设置value（不过期）
     *
     * @param key
     * @param value
     */
    public void set(String key, User value) {
        cacheMap.put(key, value);
    }

    /**
     * 设置value
     *
     * @param key
     * @param value
     * @param millSeconds 过期时间（毫秒）
     */
    public void set(final String key, User value, int millSeconds) {
        final long expireTime = System.currentTimeMillis() + millSeconds;
        cacheMap.put(key, value);
        expireTimeMap.put(key, expireTime);
        if (cacheMap.size() > 2) { // 清除过期数据
            new Thread(new Runnable() {
                public void run() {
                    // 此处若使用foreach进行循环遍历，删除过期数据，会抛出java.util.ConcurrentModificationException异常
                    Iterator<Map.Entry<String, User>> iterator = cacheMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, User> entry = iterator.next();
                        if (expireTimeMap.containsKey(entry.getKey())) {
                            long expireTime = expireTimeMap.get(key);
                            if (System.currentTimeMillis() > expireTime) {
                                iterator.remove();
                                expireTimeMap.remove(entry.getKey());
                            }
                        }
                    }
                }
            }).start();
        }
    }

    @Override
    public void remove(String key) {
        cacheMap.remove(key);
        expireTimeMap.remove(key);
    }

    /**
     * key是否存在
     *
     * @param key
     * @return
     */
    public boolean isExist(String key) {
        return cacheMap.containsKey(key)
                &&expireTimeMap.containsKey(key)&&expireTimeMap.get(key)> System.currentTimeMillis();
    }


}
