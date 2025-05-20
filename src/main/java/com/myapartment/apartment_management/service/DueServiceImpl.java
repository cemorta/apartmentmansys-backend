package com.myapartment.apartment_management.service;

import com.myapartment.apartment_management.entity.Apartment;
import com.myapartment.apartment_management.entity.Due;
import com.myapartment.apartment_management.entity.DuePayment;
import com.myapartment.apartment_management.entity.Flat;
import com.myapartment.apartment_management.repository.ApartmentRepository;
import com.myapartment.apartment_management.repository.DueRepository;
import com.myapartment.apartment_management.repository.FlatRepository;
import com.myapartment.apartment_management.service.DueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DueServiceImpl implements DueService {

    private final DueRepository dueRepository;
    private final ApartmentRepository apartmentRepository;
    private final FlatRepository flatRepository;

    @Autowired
    public DueServiceImpl(DueRepository dueRepository, ApartmentRepository apartmentRepository, FlatRepository flatRepository) {
        this.dueRepository = dueRepository;
        this.apartmentRepository = apartmentRepository;
        this.flatRepository = flatRepository;
    }

    @Override
    public List<Due> getDuesByApartmentId(Long apartmentId) {
        return dueRepository.findByApartmentId(apartmentId);
    }

    @Override
    @Transactional
    public Due createDue(Long apartmentId, BigDecimal cost, String period) {
        // Find the apartment
        Optional<Apartment> optionalApartment = apartmentRepository.findById(apartmentId);
        if (optionalApartment.isEmpty()) {
            throw new IllegalArgumentException("Apartment not found with ID: " + apartmentId);
        }

        Apartment apartment = optionalApartment.get();

        // Create the due
        Due due = new Due();
        due.setApartment(apartment);
        due.setCost(cost);
        due.setPeriod(period);
        due.setCreatedAt(LocalDateTime.now());

        // Save the due to get the ID
        due = dueRepository.save(due);

        // Find all flats in the apartment
        List<Flat> flats = flatRepository.findByApartmentId(apartmentId);

        // Create a DuePayment for each flat
        for (Flat flat : flats) {
            DuePayment duePayment = new DuePayment();
            duePayment.setDue(due);
            duePayment.setFlat(flat);
            duePayment.setIsPaymentComplete(false);
            duePayment.setCreatedAt(LocalDateTime.now());

            // Add the due payment to the due
            due.addDuePayment(duePayment);
        }

        // Save the due with all the due payments
        return dueRepository.save(due);
    }

    @Override
    public List<Due> getDuesByFlatId(Long flatId) {
        return dueRepository.findDuesByFlatId(flatId);
    }

    @Override
    public Optional<Due> getDueById(Long dueId) {
        return dueRepository.findById(dueId);
    }
}
