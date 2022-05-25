package com.ufps.sgd.domain.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthenticationResponse {
    @NonNull
    private String jwt;
}
