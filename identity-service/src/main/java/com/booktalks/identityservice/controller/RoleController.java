package com.booktalks.identityservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.booktalks.identityservice.dto.request.ApiResponse;
import com.booktalks.identityservice.dto.request.RoleDto;
import com.booktalks.identityservice.dto.response.RoleResponse;
import com.booktalks.identityservice.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleDto roleDto) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(roleDto))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAllRoles())
                .build();
    }

    @DeleteMapping("/{roleId}")
    ApiResponse<Void> deleteRole(@PathVariable String roleId) {
        roleService.deleteRole(roleId);
        return ApiResponse.<Void>builder().build();
    }
}
