package com.veeteq.finance.budget.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.veeteq.finance.budget.service.BudgetDocumentService;

@WebMvcTest(BudgetDocumentController.class)
public class BudgetDocumentControllerTst {

  @Autowired
  private BudgetDocumentController controller;

  @MockBean
  private BudgetDocumentService service;

  @Test
  public void contextLoads() {
    assertThat(controller).isNotNull();
  }
}
