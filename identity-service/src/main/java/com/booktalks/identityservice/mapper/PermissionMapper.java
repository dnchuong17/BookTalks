package com.booktalks.identityservice.mapper;

import org.mapstruct.Mapper;

import com.booktalks.identityservice.dto.request.PermissionDto;
import com.booktalks.identityservice.dto.response.PermissionResponse;
import com.booktalks.identityservice.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionDto permissionDto);

    PermissionResponse toPermissionResponse(Permission permission);
}
