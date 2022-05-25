package com.ufps.fablab.domain.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthenticationResponse {
    @NonNull
    private String jwt;
}
