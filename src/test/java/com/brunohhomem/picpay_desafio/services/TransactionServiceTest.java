package com.brunohhomem.picpay_desafio.services;

import com.brunohhomem.picpay_desafio.domain.user.User;
import com.brunohhomem.picpay_desafio.domain.user.UserType;
import com.brunohhomem.picpay_desafio.dtos.TransactionDTO;
import com.brunohhomem.picpay_desafio.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AuthorizationService authService;

    @Mock
    private NotificationService notificationService;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create transaction successfully when everything is OK.")
    void createTransactionSuccess() throws Exception {
        User sender = new User(1L, "Maria", "Silva", "9999901", "maria@email.com", "123", new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L, "Joao", "Silva", "9999902", "joao@email.com", "123", new BigDecimal(10), UserType.MERCHANT);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authService.authorizeTransaction(any(), any())).thenReturn(true);

        TransactionDTO request = new TransactionDTO(new BigDecimal(10), 1L, 2L);
        transactionService.createTransaction(request);

        verify(transactionRepository, times(1)).save(any());

        sender.setBalance(new BigDecimal(0));
        verify(userService, times(1)).saveUser(sender);

        receiver.setBalance(new BigDecimal(20));
        verify(userService, times(1)).saveUser(receiver);

        verify(notificationService, times(1)).sendNotification(sender, "Transação realizada com sucesso.");
        verify(notificationService, times(1)).sendNotification(receiver, "Transação recebida com sucesso.");
    }

    @Test
    @DisplayName("Should throw Exception when transaction is not allowed.")
    void createTransactionFail() {
    }
}