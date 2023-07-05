package com.veeteq.finance.budget.mapper;

import com.veeteq.finance.budget.dto.AccountDTO;
import com.veeteq.finance.budget.model.Account;

public class AccountMapper {

    public AccountDTO toDto(Account entity) {
        if (entity == null) {
            return null;
        }
        
        AccountDTO dto = new AccountDTO()
                .setId(entity.getId())
                .setDisplayName(entity.getName());
        return dto;
    }
}
