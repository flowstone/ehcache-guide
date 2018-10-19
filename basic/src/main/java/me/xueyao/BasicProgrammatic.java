package me.xueyao;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.slf4j.Logger;

import java.time.Duration;

import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder;
import static org.ehcache.config.builders.ResourcePoolsBuilder.heap;
import static org.ehcache.config.units.MemoryUnit.MB;
import static org.slf4j.LoggerFactory.getLogger;

public class BasicProgrammatic {
  private static final Logger LOGGER = getLogger(BasicProgrammatic.class);

  public static void main(String[] args) {
    LOGGER.info("以编程方式创建缓存管理器");
    try (CacheManager cacheManager = newCacheManagerBuilder()
            //缓存别名
      .withCache("basicCache",
              //缓存配置构造器
        newCacheConfigurationBuilder(Long.class, String.class, heap(100).offheap(1, MB))
                //设置缓存有效期，1S
        .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(1))))
      .build(true)) {
      //获得缓存对象
      Cache<Long, String> basicCache = cacheManager.getCache("basicCache", Long.class, String.class);

      LOGGER.info("开始保存到缓存");
      //保存到缓存中
      basicCache.put(1L, "快点跑，有恐龙来了");
      //Thread.sleep(5000L);
      //获得缓存中的数据
      String value = basicCache.get(1L);
      LOGGER.info("缓存中的数据为 '{}'", value);

      LOGGER.info("关闭缓存管理器");
    } catch (Exception e) {
      e.printStackTrace();
    }

    LOGGER.info("退出");

  }
}
