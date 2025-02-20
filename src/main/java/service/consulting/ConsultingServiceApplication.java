package service.consulting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConsultingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultingServiceApplication.class, args);
	}

}
