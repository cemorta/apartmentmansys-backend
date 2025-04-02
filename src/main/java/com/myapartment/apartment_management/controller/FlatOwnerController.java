//package com.myapartment.apartment_management.controller;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.myapartment.apartment_management.entity.FlatOwner;
//import com.myapartment.apartment_management.repository.FlatOwnerRepository;
//
//@RestController
//@RequestMapping("/flat-owner")  // Optional: you can add a base path
//public class FlatOwnerController {
//
//    private final FlatOwnerRepository flatOwnerRepository;
//
//    FlatOwnerController(FlatOwnerRepository flatOwnerRepository) {
//        this.flatOwnerRepository = flatOwnerRepository;
//    }
//
//    @CrossOrigin(origins = "*")  // Allow from any origin for testing
//    @GetMapping("/all")
//    Iterable<FlatOwner> all() {
//      return flatOwnerRepository.findAll();
//    }
//
//    private static final Logger log = LoggerFactory.getLogger(FlatOwnerController.class);
//
//    @CrossOrigin(origins = "*")  // Allow from any origin for testing
//    @GetMapping("/greeting")
//    public FlatOwner greeting(@RequestParam(required = false, defaultValue = "World") String name) {
//        log.info("==== get greeting called with name: {} ====", name);
//        return new FlatOwner("FirstName", "LastName");
//    }
//
//    // Add a simple test endpoint to verify the controller is working
//    @GetMapping("/test")
//    public String test() {
//        log.info("==== test endpoint called ====");
//        return "Controller is working!";
//    }
//}