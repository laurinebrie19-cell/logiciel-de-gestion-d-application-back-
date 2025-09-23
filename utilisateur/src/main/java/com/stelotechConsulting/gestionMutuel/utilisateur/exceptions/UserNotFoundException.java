package com.stelotechConsulting.gestionMutuel.utilisateur.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
