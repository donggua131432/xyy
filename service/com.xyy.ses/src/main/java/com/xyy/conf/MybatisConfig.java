package com.xyy.conf;


import com.xyy.redis.XyyRedisBean;
import com.xyy.redis.XyyRedisFactory;
import com.xyy.util.ApplicationContextUtil;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @描述: Mybatis配置
 * @作者: DuKai
 * @创建时间: 2018/7/23 10:07
 * @版本号: V3.0
 */
@Configuration
@AutoConfigureAfter(value = { MultiDataSourceConfig.class })
@EnableCaching
public class MybatisConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.setApplicationContext(applicationContext);
    }

    @Bean
    public MapperScannerConfigurer basicMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
        mapperScannerConfigurer.setBasePackage("com.xyy.mapper,com.xyy.mapper.*");
        return mapperScannerConfigurer;
    }

    @Bean(name = "xyyRedisBean")
    public XyyRedisBean getRedisBean() {
        return new XyyRedisBean();
    }

    @Bean(name = "xyyRedisFactory")
    public Object initRedisFactory(@Autowired XyyRedisBean bean) {
        XyyRedisFactory factory = XyyRedisFactory.getInstance();
        factory.init(bean);
        return factory;
    }
}


