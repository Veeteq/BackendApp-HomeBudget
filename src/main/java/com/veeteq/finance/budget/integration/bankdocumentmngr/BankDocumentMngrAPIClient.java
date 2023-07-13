package com.veeteq.finance.budget.integration.bankdocumentmngr;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.veeteq.finance.budget.dto.BankStatementInfoDTO;

@FeignClient(name = "bank-statement")
public interface BankDocumentMngrAPIClient {

  @GetMapping(path = "/api/bank/documents/details?date={date}", produces = MediaType.APPLICATION_JSON_VALUE)
  List<BankStatementInfoDTO> getBankStatementDetailsByDate(@PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);

}
