package com.example.rpg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpgApplication {

    private static final Logger logger = LoggerFactory.getLogger(RpgApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RpgApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(CharacterRepository repository) {
//        return (args) -> {
//            // save a few customers
//            repository.save(new Mage("Galadriel"));
//            repository.save(new Warrior("Talion"));
//            repository.save(new Priest("Calebrimbor"));
//            logger.info("Customers found with findAll():");
//            logger.info("-------------------------------");
//            repository.findAll().forEach(customer -> {
//                logger.info(customer.toString());
//            });
//            logger.info("");
//
//        };
//    }

}
