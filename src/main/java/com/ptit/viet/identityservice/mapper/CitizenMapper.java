package com.ptit.viet.identityservice.mapper;

import com.ptit.viet.identityservice.model.dto.request.CitizenRequest;
import com.ptit.viet.identityservice.exception.ApiException;
import com.ptit.viet.identityservice.model.entity.Citizen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CitizenMapper {

    @Mapping(target = "age", source = "age", conditionQualifiedByName ="stringToInt" )
    Citizen toCitizen(CitizenRequest citizenRequest);

    default int stringToInt(String age) throws ApiException {
        try {
            return Integer.parseInt(age);
        }
        catch (NumberFormatException e) {
            throw new ApiException(400, "Invalid age");
        }
    }
}
