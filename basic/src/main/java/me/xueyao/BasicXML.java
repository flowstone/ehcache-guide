package me.xueyao;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.xml.XmlConfiguration;
import org.slf4j.Logger;

import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;
import static org.slf4j.LoggerFactory.getLogger;

public class BasicXML {
  private static final Logger LOGGER = getLogger(BasicXML.class);

  public static void main(String[] args) {
    LOGGER.info("从XML中创建缓存管理器");
    Configuration xmlConfig = new XmlConfiguration(BasicXML.class.getResource("/ehcache.xml"));

    try (CacheManager cacheManager = newCacheManager(xmlConfig)) {
      //初始化缓存
      cacheManager.init();
      //获得缓存对象
      Cache<Long, String> basicCache = cacheManager.getCache("basicCache", Long.class, String.class);

      LOGGER.info("开始保存缓存");
      basicCache.put(1L, "你为什么那么奇怪的看着我");
      //Thread.sleep(5000L);
      //获得缓存中的值
      //basicCache.remove(1L);
      String value = basicCache.get(1L);
      LOGGER.info("Ehcache缓存中的数据为'{}'", value);
      LOGGER.info("关闭缓存管理器");
    } catch (Exception e) {
      e.printStackTrace();
    }

    LOGGER.info("退出");
  }
}
