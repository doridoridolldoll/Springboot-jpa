package springboot.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {

		Hello hello = new Hello();
		hello.setData("data");
		String data = hello.getData();
		System.out.println("data = " + data);

		SpringApplication.run(JpaApplication.class, args);
	}

}
