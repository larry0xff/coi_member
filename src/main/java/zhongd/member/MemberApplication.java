package zhongd.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//扫描coi项目中的service和mybatis config
@ComponentScan(basePackages = {"zhongd.coiplatform.service", "zhongd.coiplatform.config.mybatis", "zhongd.member"})
public class MemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberApplication.class, args);
	}
}
