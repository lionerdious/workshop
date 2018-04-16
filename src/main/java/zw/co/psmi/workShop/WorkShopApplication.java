package zw.co.psmi.workShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "zw.co.psmi.workShop")
@EnableJpaAuditing
@EnableAspectJAutoProxy
public class WorkShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkShopApplication.class, args);
	}
}
