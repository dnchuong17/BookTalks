package com.booktalks.identityservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.booktalks.identityservice.dto.request.PermissionDto;
import com.booktalks.identityservice.dto.response.PermissionResponse;
import com.booktalks.identityservice.entity.Permission;
import com.booktalks.identityservice.mapper.PermissionMapper;
import com.booktalks.identityservice.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse createPermission(PermissionDto permissionDto) {
        Permission permission = permissionMapper.toPermission(permissionDto);

        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAllPermissions() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void deletePermission(String permission) {
        permissionRepository.deleteById(permission);
    }
}
