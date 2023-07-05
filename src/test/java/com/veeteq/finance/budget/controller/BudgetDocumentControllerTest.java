package com.veeteq.finance.budget.controller;

import com.veeteq.finance.budget.dto.BudgetDocumentDTO;
import com.veeteq.finance.budget.service.BudgetDocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BudgetDocumentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BudgetDocumentService mockService;

  @Test
  public void saveDocument_OK() throws Exception {

    String documentJson = createFullJson();

    mockMvc.perform(post("/api/documents")
                    .content(documentJson)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());

    verify(mockService, times(1)).saveDocument(any(BudgetDocumentDTO.class));
  }

  @Test
  public void saveDocument_emptyDocumentDate() throws Exception {

    String documentJson = createFullJson();
/*
    mockMvc.perform(post("/api/documents")
                    .content(documentJson)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.timestamp", is(notNullValue())))
            .andExpect(jsonPath("$.status", is(400)))
            .andExpect(jsonPath("$.errors").isArray())
            .andExpect(jsonPath("$.errors", hasSize(3)))
            .andExpect(jsonPath("$.errors", hasItem("Author is not allowed.")))
            .andExpect(jsonPath("$.errors", hasItem("Please provide a author")))
            .andExpect(jsonPath("$.errors", hasItem("Please provide a price")));
*/
    //verify(mockService, times(0)).saveDocument(any(BudgetDocumentDTO.class));

  }

  @Test
  public void save_invalidAuthor_400() throws Exception {

    String bookInJson = "{\"name\":\" Spring REST tutorials\", \"author\":\"abc\",\"price\":\"9.99\"}";
/*
    mockMvc.perform(post("/api/documents")
                    .content(bookInJson)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.timestamp", is(notNullValue())))
            .andExpect(jsonPath("$.status", is(400)))
            .andExpect(jsonPath("$.errors").isArray())
            .andExpect(jsonPath("$.errors", hasSize(1)))
            .andExpect(jsonPath("$.errors", hasItem("Author is not allowed.")));
*/
    //verify(mockService, times(0)).saveDocument(any(BudgetDocumentDTO.class));

  }

  private String createFullJson() {
    String json = """
{
    "documentDate": "2023-06-17T22:00:00.000Z",
    "documentTitle": "Zakupy",
    "documentType": "Note",
    "accountId": 2,
    "paymentMethod": "Debit card",
    "currency": "PLN",
    "exchangeRate": "3"
}""";
    return json;
  }
}
