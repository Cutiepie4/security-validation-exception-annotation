package com.ptit.viet.identityservice.service.impl;

import com.ptit.viet.identityservice.model.entity.Citizen;
import com.ptit.viet.identityservice.repository.ICitizenRepository;
import com.ptit.viet.identityservice.service.ICitizenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CitizenServiceImpl implements ICitizenService {

    final ICitizenRepository ICitizenRepository;

    public Citizen createNewCitizen(Citizen citizen) {
        return ICitizenRepository.save(citizen);
    }
}
