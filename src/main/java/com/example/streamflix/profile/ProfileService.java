package com.example.streamflix.profile;

import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import com.example.streamflix.profile.dto.ProfileDto;

public interface ProfileService {

    @PreAuthorize("hasRole('USER')")
    List<ProfileDto> getProfiles();
}