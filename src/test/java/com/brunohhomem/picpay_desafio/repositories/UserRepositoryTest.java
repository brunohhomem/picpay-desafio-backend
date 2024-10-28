package com.brunohhomem.picpay_desafio.repositories;

import com.brunohhomem.picpay_desafio.domain.user.User;
import com.brunohhomem.picpay_desafio.domain.user.UserType;
import com.brunohhomem.picpay_desafio.dtos.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get user sucessfully from DB.")
    void findUserByDocumentSucess() {
        String document = "123456789";

        UserDTO data = new UserDTO(
                "Bruno",
                "Homem",
                document,
                new BigDecimal(10),
                "bruno@email.com",
                "123",
                UserType.COMMON
        );

        this.createUser(data);

        Optional<User> result = this.userRepository.findUserByDocument(document);
        assertThat(result.isPresent()).isTrue();
    }

    private User createUser(UserDTO data) {
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
}