package com.veeteq.finance.budget.controller;

import com.veeteq.finance.budget.dto.BankStatementDetailDTO;
import com.veeteq.finance.budget.dto.BudgetDocumentDTO;
import com.veeteq.finance.budget.integration.bankdocumentmngr.BankDocumentMngrAPIClient;
import com.veeteq.finance.budget.service.BudgetDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/documents")
@CrossOrigin(origins = "http://localhost:4203")
public class BudgetDocumentController {
    private static final Logger LOG = LoggerFactory.getLogger(BudgetDocumentController.class);

    private final BudgetDocumentService budgetDocumentService;
    private final BankDocumentMngrAPIClient bankDocumentMngrClient;

    @Autowired
    public BudgetDocumentController(BudgetDocumentService budgetDocumentService, BankDocumentMngrAPIClient bankDocumentMngrClient) {
        this.budgetDocumentService = budgetDocumentService;
        this.bankDocumentMngrClient = bankDocumentMngrClient;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveDocument(@Valid @RequestBody(required = true) BudgetDocumentDTO document) {
        LOG.info("Start processing POST request: " + document.getDocumentTitle());

        budgetDocumentService.saveDocument(document);

        return null;
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
    public ResponseEntity<List<BankStatementDetailDTO>> getBankStatementsByDate(@RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        LOG.info("Requesting bank statement details from BankDocument Manager for: " + date);

        List<BankStatementDetailDTO> response = bankDocumentMngrClient.getBankStatementDetailsByDate(date);
        return ResponseEntity.ok(response);
    }
}
