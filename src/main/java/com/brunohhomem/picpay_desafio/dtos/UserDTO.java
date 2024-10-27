package com.brunohhomem.picpay_desafio.dtos;

import com.brunohhomem.picpay_desafio.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName,
                      String lastName,
                      String document,
                      BigDecimal balance,
                      String email,
                      String password,
                      UserType userType) {
}
