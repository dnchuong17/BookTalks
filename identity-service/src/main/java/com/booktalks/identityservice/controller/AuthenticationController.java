package com.booktalks.identityservice.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.*;

import com.booktalks.identityservice.Utils.JwtUtil;
import com.booktalks.identityservice.dto.request.*;
import com.booktalks.identityservice.dto.response.AuthenticationResponse;
import com.booktalks.identityservice.dto.response.IntrospectResponse;
import com.booktalks.identityservice.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    JwtUtil verifyJwtUtil;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> login(@RequestBody Login loginRequest) {
        var result = authenticationService.authenticate(loginRequest);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> login(@RequestBody IntrospectRequest req) throws ParseException, JOSEException {
        var result = verifyJwtUtil.introspect(req);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }
}
