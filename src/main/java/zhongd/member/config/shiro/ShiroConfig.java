package zhongd.member.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		factoryBean.setSecurityManager(securityManager);
		
		Map<String, String> filterChainMap = new LinkedHashMap<String, String>();
		// anon表示可以匿名访问的url
		filterChainMap.put("/login", "anon");
		filterChainMap.put("/page/login/**", "anon");
		filterChainMap.put("/advice/save", "authc");
		factoryBean.setLoginUrl("/page/login/loginModal.html");
		factoryBean.setUnauthorizedUrl("/403");
		factoryBean.setFilterChainDefinitionMap(filterChainMap);
		return factoryBean;
	}
	@Bean
	public MyShiroRealm myShiroRealm() {
		return new MyShiroRealm();
	}
	@Bean
	public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    } 
}
