package com.booktalks.profile.mapper;

import com.booktalks.profile.dto.request.ProfileCreationRequest;
import com.booktalks.profile.dto.response.UserProfileResponse;
import com.booktalks.profile.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);
    UserProfileResponse toUserProfileResponse(UserProfile userProfile);

    void profileUpdate(@MappingTarget UserProfile profile, ProfileCreationRequest request);
}
