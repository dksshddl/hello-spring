package dksshddl.cloudpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class CloudPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudPracticeApplication.class, args);
	}

}
