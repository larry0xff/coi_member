package zhongd.member.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		factoryBean.setSecurityManager(securityManager);
		
		Map<String, String> filterChainMap = new HashMap<String, String>();
		// anon表示可以匿名访问的url
		filterChainMap.put("/login", "anon");
		filterChainMap.put("/page/login/**", "anon");
		// 注销url
		filterChainMap.put("/logout", "logout");
		// 需要授权访问的链接
//		filterChainMap.put("/**", "authc");
		filterChainMap.put("/page/user/**", "authc");
		filterChainMap.put("/page/index/**", "authc");
		factoryBean.setLoginUrl("/page/login/login.html");
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
