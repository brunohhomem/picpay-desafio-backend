package com.brunohhomem.picpay_desafio.repositories;

import com.brunohhomem.picpay_desafio.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
