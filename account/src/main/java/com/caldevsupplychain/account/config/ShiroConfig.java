package com.caldevsupplychain.account.config;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.config.AbstractShiroConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.caldevsupplychain.account.jwt.service.JwtService;
import com.caldevsupplychain.account.security.JpaRealm;
import com.caldevsupplychain.account.security.JwtAuthenticationFilter;
import com.caldevsupplychain.account.security.JwtRealm;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Configuration
public class ShiroConfig extends AbstractShiroConfiguration {

	@Bean(name = "JpaRealm")
	@ConditionalOnMissingBean(name = "JpaRealm")
	@DependsOn("lifecycleBeanPostProcessor")
	public Realm jpaRealm() {
		JpaRealm jpaRealm = new JpaRealm();
		jpaRealm.setCredentialsMatcher(credentialsMatcher());
		jpaRealm.setCachingEnabled(true);
		return jpaRealm;
	}

	@Bean(name = "JwtRealm")
	@ConditionalOnMissingBean(name = "JwtRealm")
	@DependsOn("lifecycleBeanPostProcessor")
	public Realm jwtRealm() {
		return new JwtRealm();
	}

	@Bean(name = "shiroFilterFactoryBean")
	@Autowired
	public ShiroFilterFactoryBean shiroFilter(JwtService jwtService){
		ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
		shiroFilterFactory.setSecurityManager(securityManager());

		shiroFilterFactory.getFilters()
			.put("jwtFilter", new JwtAuthenticationFilter(jwtService));

		Map<String, String> chains = Maps.newHashMap();
		chains.put("/api/v1/account/issue-token", "authcBasic[permissive]");
		chains.put("/api/v1/orders/**", "jwtFilter");
		shiroFilterFactory.setFilterChainDefinitionMap(chains);;

		return shiroFilterFactory;
	}

	@Bean
	public CacheManager cacheManager() {
		return new MemoryConstrainedCacheManager();
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(Lists.newArrayList(jpaRealm(), jwtRealm()));

		SecurityUtils.setSecurityManager(securityManager);
		return securityManager;
	}

	@Bean
	public PasswordService passwordService() {
		return new DefaultPasswordService();
	}

	@Bean
	public PasswordMatcher credentialsMatcher() {
		PasswordMatcher credentialsMatcher = new PasswordMatcher();
		credentialsMatcher.setPasswordService(new DefaultPasswordService());

		return credentialsMatcher;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daapc = new DefaultAdvisorAutoProxyCreator();
		daapc.setProxyTargetClass(true);
		return daapc;
	}
}
