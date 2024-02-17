package com.br.lucasrod.gestao_vagas.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException () {
        super("User Not Found");
    }
}
