package fghost.carview.config.databaseSeeder;

import fghost.carview.exception.util.ExceptionUtils;
import fghost.carview.v1.car.domain.CarEntity;
import fghost.carview.v1.car.domain.CarTypeEnum;
import fghost.carview.v1.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CarSeeder implements CommandLineRunner {

    @Autowired
    CarRepository carRepository;

    @Override
    public void run(String... args) {
        try {
            databaseCarSeeder();
        } catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException("Error: " + e);
        }
    }

    private void databaseCarSeeder() {
        if (carRepository.count() == 0) {
            CarEntity seeder = new CarEntity();
            seeder.setCarModel("CLS 200");
            seeder.setAutoMaker("Mercedes");
            seeder.setYear("2023");
            seeder.setType(CarTypeEnum.SEDAN);
            seeder.setColor("Beje");
            carRepository.save(seeder);
        }
    }
}
