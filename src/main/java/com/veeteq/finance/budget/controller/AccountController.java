package com.veeteq.finance.budget.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veeteq.finance.budget.dto.AccountDTO;
import com.veeteq.finance.budget.service.AccountService;

@RestController
@RequestMapping(path = "/api/accounts")
@CrossOrigin(origins = "http://localhost:4203")
public class AccountController {
  private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

  private final AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping(path = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AccountDTO>> getAccounts() {
    LOG.info("Processing GET request for all Accounts");

    List<AccountDTO> accounts = accountService.getAccounts();
    return ResponseEntity.ok(accounts);
  }

  @GetMapping("/{id}")
  @Cacheable(value = "accounts", key = "#id")
  public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id, HttpServletRequest request) {
    LOG.info("Processing GET request for single Account by id: " + id);

    AccountDTO account = accountService.getAccountById(id);

    String etag = Integer.toString(account.hashCode());
    if (request.getHeader("If-None-Match") != null &&
        request.getHeader("If-None-Match").equals(etag)) {
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    } else {
      return ResponseEntity.ok().eTag(etag).body(account);
    }
  }

}
