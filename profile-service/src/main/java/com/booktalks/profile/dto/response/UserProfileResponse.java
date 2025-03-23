package com.booktalks.profile.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileResponse {
    long id;
    String firstName;
    String lastName;
    LocalDate dob;
    String city;
}
