package com.haulmatic.usermanagement;

import com.haulmatic.usermanagement.entity.UserInfo;
import com.haulmatic.usermanagement.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserManagementApplication implements CommandLineRunner{

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		userInfoRepository.save(new UserInfo(1L,"admin", "admin@gmail.com", passwordEncoder.encode("admin"), "ROLE_ADMIN"));
		userInfoRepository.save(new UserInfo(2L,"user", "user@gmail.com", passwordEncoder.encode("user"), "ROLE_USER"));
	}
}
