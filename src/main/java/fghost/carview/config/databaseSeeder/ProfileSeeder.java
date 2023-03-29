package fghost.carview.config.databaseSeeder;

import fghost.carview.exception.util.ExceptionUtils;
import fghost.carview.v1.profiles.domain.ProfileEntity;
import fghost.carview.v1.profiles.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProfileSeeder implements CommandLineRunner {

    @Autowired
    ProfileRepository profileRepository;

    @Override
    public void run(String... args) {
        try{
            databaseProfileSeeder();
        }catch (Exception e){
            throw ExceptionUtils.buildNotPersistedException("Error: " + e);
        }
    }

    private void databaseProfileSeeder() {
        if (profileRepository.count() == 0) {
            ProfileEntity profile = new ProfileEntity();
            profile.setName("ADMIN");
            profileRepository.save(profile);
        }
    }
}
