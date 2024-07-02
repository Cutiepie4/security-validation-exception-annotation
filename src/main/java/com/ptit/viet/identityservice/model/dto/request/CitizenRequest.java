package com.ptit.viet.identityservice.model.dto.request;

import com.ptit.viet.identityservice.exception.group.INotEmptyGroup;
import com.ptit.viet.identityservice.exception.group.INotNullGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CitizenRequest {
    @NotBlank(message = "First name is required")
    private String firstName;
    private String lastName;
    @NotEmpty(message = "Phone number is not empty", groups = INotEmptyGroup.class)
    @NotNull(message = "Phone number is not null", groups = INotNullGroup.class)
    private String phoneNumber;
    private String address;
    private LocalDate birthDate;
    private String age;
}
