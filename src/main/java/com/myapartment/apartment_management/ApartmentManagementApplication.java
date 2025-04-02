package com.myapartment.apartment_management;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApartmentManagementApplication {

	// private static final Logger log = LoggerFactory.getLogger(ApartmentManagementApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ApartmentManagementApplication.class);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			// // save a few customers
			// // repository.save(new FlatOwner("Jack", "Bauer"));
			// // repository.save(new FlatOwner("Chloe", "O'Brian"));
			// // repository.save(new FlatOwner("Kim", "Bauer"));
			// // repository.save(new FlatOwner("David", "Palmer"));
			// // repository.save(new FlatOwner("Michelle", "Dessler"));

			// // fetch all customers
			// log.info("Flat owners found with findAll():");
			// log.info("-------------------------------");
			// repository.findAll().forEach(flatOwner -> {
			// log.info(flatOwner.toString());
			// });
			// log.info("");

			// // fetch an individual customer by ID
			// FlatOwner flatOwner = repository.findById(1L);
			// log.info("Flat owner found with findById(1L):");
			// log.info("--------------------------------");
			// log.info(flatOwner.toString());
			// log.info("");

			// // fetch customers by last name
			// log.info("Flat owner found with findByLastName('Bauer'):");
			// log.info("--------------------------------------------");
			// repository.findByLastName("Bauer").forEach(bauer -> {
			// log.info(bauer.toString());
			// });
			// log.info("");
		};
	}
}