package com.ptit.viet.identityservice.mapper;

import com.ptit.viet.identityservice.model.dto.response.SignUpResponse;
import com.ptit.viet.identityservice.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    @Mapping(target = "authorities", source = "authorities", conditionQualifiedByName = "convertAuthorities")
    SignUpResponse toUserResponse(User user);

    default List<String> convertAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
