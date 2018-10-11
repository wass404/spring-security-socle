package com.example.springsecurity;

import com.example.springsecurity.Entities.AppRole;
import com.example.springsecurity.Entities.AppUser;
import com.example.springsecurity.Entities.Task;
import com.example.springsecurity.repositories.TaskRepository;
import com.example.springsecurity.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringsecurityApplication implements CommandLineRunner{
	@Autowired
	private AccountService accountService;
	@Autowired
	private TaskRepository taskRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		accountService.saveUser(new AppUser(null,"admin","1234",null));
		accountService.saveUser(new AppUser(null,"user","1234",null));
		accountService.saveRole(new AppRole(null,"ADMIN"));
		accountService.saveRole(new AppRole(null,"USER"));

		accountService.AddRoleToUser("admin","ADMIN");
		accountService.AddRoleToUser("admin","USER");
		accountService.AddRoleToUser("user","USER");
//		AppUser user1 = new AppUser(null, "yacinezr", "123456789", null);
//		AppUser user2 = new AppUser(null, "yacinezr2", "123456789", null);
//		AppUser user3 = new AppUser(null, "yacinezr3", "123456789", null);
//
//		AppRole role1 = new AppRole(null, "admin");
//		AppRole role2 = new AppRole(null, "user");
//
//		accountService.saveUser(user1);
//		accountService.saveUser(user2);
//		accountService.saveUser(user3);
//
//		accountService.saveRole(role1);
//		accountService.saveRole(role2);
//
//		accountService.AddRoleToUser(user1.getUsername(), role1.getRole());
//		accountService.AddRoleToUser(user1.getUsername(), role2.getRole());
//		accountService.AddRoleToUser(user2.getUsername(), role2.getRole());
//		accountService.AddRoleToUser(user3.getUsername(), role2.getRole());
	Stream.of("T1","T2","T3").forEach(t ->{
		taskRepository.save(new Task(null,t));
	});
	taskRepository.findAll().forEach(t ->{
		System.out.println(t.getTaskName());
	});
	}
}
