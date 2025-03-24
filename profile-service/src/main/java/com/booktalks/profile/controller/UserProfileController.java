package com.booktalks.profile.controller;

import com.booktalks.profile.dto.request.ProfileCreationRequest;
import com.booktalks.profile.dto.response.UserProfileResponse;
import com.booktalks.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @PostMapping("/user")
    UserProfileResponse createProfile(@RequestBody ProfileCreationRequest request) {
        return userProfileService.createProfile(request);
    }

    @GetMapping("/user/{id}")
    UserProfileResponse getProfile(@PathVariable long id) {
        return userProfileService.getProfile(id);
    }

    @PutMapping("/user/{id}")
    UserProfileResponse editProfile(@PathVariable long id, @RequestBody ProfileCreationRequest request) {
        return userProfileService.editProfile(id, request);
    }

    @DeleteMapping("/user/{id}")
    UserProfileResponse deleteProfile(@PathVariable long id) {
        return userProfileService.deleteProfile(id);
    }

}
