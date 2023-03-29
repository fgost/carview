package fghost.carview.config.databaseSeeder;

import fghost.carview.exception.util.ExceptionUtils;
import fghost.carview.v1.users.domain.RequiredPreferences;
import fghost.carview.v1.users.domain.UserEntity;
import fghost.carview.v1.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) {
        try {
            databaseUserSeeder();
        } catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException("Error: " + e);
        }
    }

    private void databaseUserSeeder() {
        if (userRepository.count() == 0) {
            UserEntity seeder = new UserEntity();
            seeder.setName("Admin");
            seeder.setLastName("User");
            seeder.setEmail("admin@fghost.net");
            seeder.setPreferences(RequiredPreferences.getRequiredPreference());
            seeder.setPassword(new BCryptPasswordEncoder().encode("admin@fghost"));
            userRepository.save(seeder);
        }
    }
}
