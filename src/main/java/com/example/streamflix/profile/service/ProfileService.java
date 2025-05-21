package com.example.streamflix.profile.service;

import com.example.streamflix.profile.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
    List<ProfileDto> listarPerfiles();
    default List<ProfileDto> getProfiles() {
        return listarPerfiles();
    }
}
