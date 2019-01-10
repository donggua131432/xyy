package com.xyy.core.shiro;

import com.xyy.shiro.filter.RestPathMatchingFilterChainResolver;
import com.xyy.shiro.rule.RolePermRule;
import com.xyy.util.ApplicationContextUtil;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述: Filter 管理器
 * @作者: DuKai
 * @创建时间: 2018/11/21 09:42
 * @版本号: V1.0
 */
@Component
public class ShiroFilterChainManager {

    /**
     * 初始化获取过滤链
     * @return
     */
    public Map<String,Filter> initGetFilters() {
        Map<String,Filter> filters = new LinkedHashMap<>();
        PasswordFilter passwordFilter = new PasswordFilter();
        filters.put("auth",passwordFilter);
        BJwtFilter jwtFilter = new BJwtFilter();
        filters.put("jwt",jwtFilter);
        return filters;
    }

    /**
     * 初始化获取过滤链规则
     * @return
     */
    public Map<String,String> initGetFilterChain() {
        Map<String,String> filterChain = new LinkedHashMap<>();
        // -------------anon 默认过滤器忽略的URL
        List<String> defalutAnon = Arrays.asList("/css/**","/js/**","/user/getDynamicSecretKey");
        defalutAnon.forEach(ignoredUrl -> filterChain.put(ignoredUrl,"anon"));

        // -------------auth 默认需要认证过滤器的URL 走auth--PasswordFilter
        List<String> defalutAuth = Arrays.asList("/user/login", "/user/register");
        defalutAuth.forEach(authUrl -> filterChain.put(authUrl,"auth"));

        // -------------dynamic 动态URL 走jwt--BJwtFilter
        List<String> defalutJwt = Arrays.asList("/user/**");
        defalutJwt.forEach(jwtUrl -> filterChain.put(jwtUrl,"jwt"));
        return filterChain;
    }

}
