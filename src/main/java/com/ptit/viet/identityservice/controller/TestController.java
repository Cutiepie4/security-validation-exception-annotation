package com.ptit.viet.identityservice.controller;

import com.ptit.viet.identityservice.model.dto.request.CitizenRequest;
import com.ptit.viet.identityservice.model.dto.response.ApiResponse;
import com.ptit.viet.identityservice.exception.ApiException;
import com.ptit.viet.identityservice.exception.group.INotEmptyGroup;
import com.ptit.viet.identityservice.mapper.CitizenMapper;
import com.ptit.viet.identityservice.model.entity.Citizen;
import com.ptit.viet.identityservice.repository.ICitizenRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class TestController {
    final ICitizenRepository icitizenRepository;
    final CitizenMapper citizenMapper;

    public TestController(ICitizenRepository icitizenRepository, CitizenMapper citizenMapper) {
        this.icitizenRepository = icitizenRepository;
        this.citizenMapper = citizenMapper;
    }

    @GetMapping("/test")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/citizen/{id}")
    public ApiResponse<Citizen> getUser(@PathVariable Long id) throws ApiException {
        return ApiResponse.<Citizen>builder()
                .code(200)
                .data(icitizenRepository.findById(id).orElseThrow(() -> new ApiException(204, "Citizen not found")))
                .build();
    }

    @PostMapping("/citizen")
    public ApiResponse<Citizen> createUser(@RequestBody @Validated(INotEmptyGroup.class) CitizenRequest citizen) {
        return ApiResponse.<Citizen>builder()
                .code(200)
                .data(icitizenRepository.save(citizenMapper.toCitizen(citizen)))
                .build();
    }
}
