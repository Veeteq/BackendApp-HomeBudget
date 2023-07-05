package com.veeteq.finance.budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veeteq.finance.budget.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
