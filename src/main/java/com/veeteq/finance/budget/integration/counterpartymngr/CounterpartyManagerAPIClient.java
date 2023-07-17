package com.veeteq.finance.budget.integration.counterpartymngr;

import com.veeteq.finance.budget.dto.CounterpartyInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "counterparty-manager")
public interface CounterpartyManagerAPIClient {

  @GetMapping(path = "/api/counterparties/searchByParams", produces = MediaType.APPLICATION_JSON_VALUE)
  List<CounterpartyInfoDTO> getCounterpartiesByText(@RequestBody Map<String, String> searchParams);

}
