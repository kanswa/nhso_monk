package th.go.nhso.erm.monk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {
		"th.go.nhso.erm.config",
		"th.go.nhso.erm.monk.service",
		"th.go.nhso.erm.monk.controller"
})
@EnableAutoConfiguration
@EntityScan({
		"th.go.nhso.erm.monk.entity"
})
@EnableJpaRepositories({
		"th.go.nhso.erm.monk.repository",
})
public class MonkDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonkDataApplication.class, args);
	}

}
