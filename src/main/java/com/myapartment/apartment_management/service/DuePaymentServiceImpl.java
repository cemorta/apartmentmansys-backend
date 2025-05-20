package com.myapartment.apartment_management.service;

import com.myapartment.apartment_management.entity.DuePayment;
import com.myapartment.apartment_management.repository.DuePaymentRepository;
import com.myapartment.apartment_management.service.DuePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DuePaymentServiceImpl implements DuePaymentService {

    private final DuePaymentRepository duePaymentRepository;

    @Autowired
    public DuePaymentServiceImpl(DuePaymentRepository duePaymentRepository) {
        this.duePaymentRepository = duePaymentRepository;
    }

    @Override
    @Transactional
    public DuePayment markPaymentComplete(Long duePaymentId) {
        Optional<DuePayment> optionalDuePayment = duePaymentRepository.findById(duePaymentId);
        if (optionalDuePayment.isEmpty()) {
            throw new IllegalArgumentException("Due payment not found with ID: " + duePaymentId);
        }

        DuePayment duePayment = optionalDuePayment.get();
        duePayment.setIsPaymentComplete(true);
        duePayment.setPaidAt(LocalDateTime.now());
        System.out.println(duePayment);

        return duePaymentRepository.save(duePayment);
    }

    @Override
    public List<DuePayment> getDuePaymentsByFlatId(Long flatId) {
        return duePaymentRepository.findByFlatId(flatId);
    }

    @Override
    public Optional<DuePayment> getDuePaymentById(Long duePaymentId) {
        return duePaymentRepository.findById(duePaymentId);
    }

    @Override
    public List<DuePayment> getDuePaymentsByDueId(Long dueId) {
        return duePaymentRepository.findByDueId(dueId);
    }
}
