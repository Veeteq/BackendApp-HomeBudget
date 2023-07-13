package com.veeteq.finance.budget.service.jpa;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.veeteq.finance.budget.dto.AccountDTO;
import com.veeteq.finance.budget.mapper.AccountMapper;
import com.veeteq.finance.budget.model.Account;
import com.veeteq.finance.budget.repository.AccountRepository;
import com.veeteq.finance.budget.service.AccountService;

@Service
public class AccountServiceJpa implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;

    @Autowired
    public AccountServiceJpa(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.mapper = new AccountMapper();
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public long count() {
        return accountRepository.count();
    }

    @Override
    public List<AccountDTO> getAccounts() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<AccountDTO> result = accountRepository.findAll(sort).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public AccountDTO getAccountById(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Account with id %s not found", id)));
        AccountDTO response = mapper.toDto(account);
        return response;
    }
}
