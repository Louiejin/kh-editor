package com.kanjihybrid.editor;

import com.kanjihybrid.editor.model.User;
import com.kanjihybrid.editor.model.lookup.Role;
import com.kanjihybrid.editor.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan({
        "com.kanjihybrid.editor.config",
        "com.kanjihybrid.editor.mapper",
        "com.kanjihybrid.editor.repository",
        "com.kanjihybrid.editor.service",
        "com.kanjihybrid.editor.resource"
})
public class Application {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner init(UserRepo userRepo) {
        return (args) -> {
            createAdmin(userRepo);
        };
    }

    private void createAdmin(UserRepo userRepo) {
        if (!userRepo.findByUsername("admin").isPresent()) {
            User administrator = new User();
            administrator.setUsername("admin");
            administrator.setFirstName("Admin");
            administrator.setLastName("Admin");

            administrator.getRoles().add(Role.ROLE_ADMIN);
            administrator.setPassword(new BCryptPasswordEncoder().encode("123qwe"));
            userRepo.save(administrator);
        }
    }

}
