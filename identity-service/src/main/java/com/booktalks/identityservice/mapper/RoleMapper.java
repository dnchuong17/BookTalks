package com.booktalks.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.booktalks.identityservice.dto.request.RoleDto;
import com.booktalks.identityservice.dto.response.RoleResponse;
import com.booktalks.identityservice.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleDto roleDto);

    RoleResponse toRoleResponse(Role role);
}
