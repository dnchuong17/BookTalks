package com.booktalks.profile.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Node("user_profile")
public class UserProfile {
    @Id
    @GeneratedValue
    private Long id;

    @Property("userId")
    String userId;

    String firstName;
    String lastName;
    LocalDate dob;
    String city;
}
