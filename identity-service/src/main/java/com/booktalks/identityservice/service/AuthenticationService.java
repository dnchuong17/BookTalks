package com.booktalks.identityservice.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.booktalks.identityservice.Utils.JwtUtil;
import com.booktalks.identityservice.dto.request.Login;
import com.booktalks.identityservice.dto.request.LogoutRequest;
import com.booktalks.identityservice.dto.request.RefreshRequest;
import com.booktalks.identityservice.dto.response.AuthenticationResponse;
import com.booktalks.identityservice.entity.InvalidatedToken;
import com.booktalks.identityservice.exeption.AppException;
import com.booktalks.identityservice.exeption.ErrorCode;
import com.booktalks.identityservice.repository.InvalidatedTokenRepository;
import com.booktalks.identityservice.repository.UserRepository;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {
    UserRepository userRepository;
    JwtUtil jwtUtil;
    InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public AuthenticationResponse authenticate(Login login) {
        var user = userRepository
                .findByUsername(login.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(login.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = jwtUtil.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(token)
                .authenticated(true)
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signedJWT = jwtUtil.verifyToken(request.getToken());

        var jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jwtId).expiryTime(expiryTime).build();

        invalidatedTokenRepository.save(invalidatedToken);
        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        var token = jwtUtil.generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(token)
                .authenticated(true)
                .build();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        var signedToken = jwtUtil.verifyToken(request.getToken());

        String jwtId = signedToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jwtId).expiryTime(expiryTime).build();

        invalidatedTokenRepository.save(invalidatedToken);
    }
}
