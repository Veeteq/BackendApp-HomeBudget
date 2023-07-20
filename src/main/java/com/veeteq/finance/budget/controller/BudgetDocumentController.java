package com.veeteq.finance.budget.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veeteq.finance.budget.dto.BankStatementInfoDTO;
import com.veeteq.finance.budget.dto.BudgetDocumentDTO;
import com.veeteq.finance.budget.dto.CounterpartyInfoDTO;
import com.veeteq.finance.budget.integration.bankdocumentmngr.BankDocumentMngrAPIClient;
import com.veeteq.finance.budget.integration.counterpartymngr.CounterpartyManagerAPIClient;
import com.veeteq.finance.budget.service.BudgetDocumentService;

@RestController
@RequestMapping(path = "/api/documents")
@CrossOrigin(origins = "http://localhost:4203")
public class BudgetDocumentController {
    private static final Logger LOG = LoggerFactory.getLogger(BudgetDocumentController.class);

    private final BudgetDocumentService budgetDocumentService;
    private final BankDocumentMngrAPIClient bankDocumentMngrClient;
    private final CounterpartyManagerAPIClient counterpartyMngrClient;

    @Autowired
    public BudgetDocumentController(BudgetDocumentService budgetDocumentService,
                                    BankDocumentMngrAPIClient bankDocumentMngrClient,
                                    CounterpartyManagerAPIClient counterpartyMngrClient) {
        this.budgetDocumentService = budgetDocumentService;
        this.bankDocumentMngrClient = bankDocumentMngrClient;
        this.counterpartyMngrClient = counterpartyMngrClient;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BudgetDocumentDTO> saveDocument(@Valid @RequestBody(required = true) BudgetDocumentDTO document) {
        LOG.info("Start processing POST request: " + document.getDocumentTitle());

        BudgetDocumentDTO savedDocument = budgetDocumentService.saveDocument(document);

        return ResponseEntity.ok(savedDocument);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateDocument(@Valid @RequestBody(required = true) BudgetDocumentDTO document,
                                                 @PathVariable Long id) {
        LOG.info("Start processing PUT request: " + document.getDocumentTitle());

        budgetDocumentService.updateDocument(document, id);

        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable("id") Long id) {
        LOG.info("Start processing DELETE request for document id: " + id);

        budgetDocumentService.deleteDocument(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping(path = "/bankstatements")
    public ResponseEntity<List<BankStatementInfoDTO>> getBankStatementsByDate(@RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        LOG.info("Requesting bank statement details from BankDocument Manager for: " + date);

        List<BankStatementInfoDTO> response = bankDocumentMngrClient.getBankStatementDetailsByDate(date);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/counterparties")
    public ResponseEntity<List<CounterpartyInfoDTO>> getCounterpartiesByText(@RequestParam(name = "searchText", required = true) String searchText) {
        LOG.info("Requesting counterparties search from Counterparty Manager for: " + searchText);

        Map<String, String> searchParams = new HashMap<>();
        String[] searchAttributes = {"name", "iban", "taxId"};
        for (String key : searchAttributes) {
            searchParams.put(key, searchText);
        }
        List<CounterpartyInfoDTO> response = counterpartyMngrClient.getCounterpartiesByText(searchParams);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/counterparties/{id}")
    public ResponseEntity<CounterpartyInfoDTO> getCounterpartyById(@PathVariable("id") Long id) {
        LOG.info("Requesting search for Counterparty by id: " + id);

        CounterpartyInfoDTO response = counterpartyMngrClient.getCounterpartyById(id);
        return ResponseEntity.ok(response);
    }
}
