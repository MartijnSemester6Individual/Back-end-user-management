package com.kwetteruser.usermanagement;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
				.directory("../../../.env")
				.ignoreIfMalformed()
				.ignoreIfMissing()
				.load();
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
