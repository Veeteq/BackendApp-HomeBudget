package com.veeteq.finance.budget.service;

import com.veeteq.finance.budget.dto.AccountDTO;
import com.veeteq.finance.budget.model.Account;

import java.util.List;

public interface AccountService {

  List<AccountDTO> getAccounts();
  Account save(Account user);

  AccountDTO getAccountById(Long id);

  long count();
}
