package com.example.streamflix.profile;

import com.example.streamflix.profile.dto.ProfileDto;
import java.util.List;

import com.example.streamflix.profile.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
class ProfileServiceImpl implements ProfileService {

    @Override
    public List<ProfileDto> listarPerfiles() {
        // Implementación de ejemplo, retorna una lista vacía
        return Collections.emptyList();
    }
}