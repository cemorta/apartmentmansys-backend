package com.myapartment.apartment_management.service;

import com.myapartment.apartment_management.dto.ApartmentDTO;
import com.myapartment.apartment_management.dto.FlatCreateDTO;
import com.myapartment.apartment_management.entity.Apartment;
import com.myapartment.apartment_management.entity.Flat;
import com.myapartment.apartment_management.entity.FlatOwnerProfile;
import com.myapartment.apartment_management.repository.ApartmentRepository;
import com.myapartment.apartment_management.repository.FlatOwnerProfileRepository;
import com.myapartment.apartment_management.repository.FlatRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FlatService {

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private FlatOwnerProfileRepository flatOwnerProfileRepository;

    public FlatCreateDTO createFlat(FlatCreateDTO dto) {

        Apartment apartment = new Apartment();
        apartment.setId(dto.getApartmentId());

        FlatOwnerProfile owner = null;
        if (dto.getOwnerUserId() != null) {
            owner = flatOwnerProfileRepository.findByUserId(dto.getOwnerUserId())
                    .orElseThrow(() -> new EntityNotFoundException("Owner not found"));
        }

        Flat flat = new Flat(
                apartment,
                dto.getFlatNumber(),
                dto.getFloorNumber(),
                dto.getArea(),
                dto.getNumBedrooms(),
                dto.getNumBathrooms(),
                owner
        );

        flat = flatRepository.save(flat);

        return new FlatCreateDTO(
                flat.getApartment().getId(),
                flat.getArea(),
                flat.getFlatNumber(),
                flat.getFloorNumber(),
                flat.getNumBathrooms(),
                flat.getNumBedrooms(),
                flat.getOwner() != null ? flat.getOwner().getUser().getId() : null
        );
    }

    public void deleteFlat(Long id) {
        flatRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return flatRepository.existsById(id);
    }
}
