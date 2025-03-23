package com.booktalks.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.booktalks.identityservice.dto.request.UserDto;
import com.booktalks.identityservice.dto.response.UserResponse;
import com.booktalks.identityservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toUser(UserDto userDto);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void userUpdate(@MappingTarget User user, UserDto userDto);
}
