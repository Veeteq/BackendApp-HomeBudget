package com.veeteq.finance.budget.controller;

import java.util.Currency;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.veeteq.finance.budget.model.BudgetDocumentItemType;
import com.veeteq.finance.budget.service.BudgetDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veeteq.finance.budget.model.BudgetDocumentType;
import com.veeteq.finance.budget.model.PaymentMethod;

@RestController
@RequestMapping(path = "/api/common")
@CrossOrigin(origins = "http://localhost:4203")
public class UtilityController {
    private static final Logger LOG = LoggerFactory.getLogger(UtilityController.class);

    private final BudgetDocumentService budgetDocumentService;

    @Value(value = "${currency.code.list}")
    private String[] currencyCodes;

    @Autowired
    public UtilityController(BudgetDocumentService budgetDocumentService) {
        this.budgetDocumentService = budgetDocumentService;
    }

    @GetMapping(path = "/currencies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Currency>> getCurrencies() {
        LOG.info("Getting list of currencies in scope");
        LOG.info(new StringBuilder().append(currencyCodes).toString());

        Set<Currency> currencies = new LinkedHashSet<>();
        for (String code : currencyCodes) {
            var currency = Currency.getInstance(code);
            currencies.add(currency);
        }
        return ResponseEntity.ok(currencies);
    }

    @GetMapping(path = "/documentTypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BudgetDocumentType[]> getDocumentTypes() {

        return ResponseEntity.ok(BudgetDocumentType.values());
    }

    @GetMapping(path = "/paymentMethods", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentMethod[]> getPaymentMethods() {

        return ResponseEntity.ok(PaymentMethod.values());
    }

    @GetMapping(path = "/documentItemTypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BudgetDocumentItemType[]> getDocumentItemTypes() {

        return ResponseEntity.ok(BudgetDocumentItemType.values());
    }

    @GetMapping(path = "/documentTitles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getDocumentTitles(@RequestParam(name = "pattern", required = true) String pattern) {
        LOG.info("Requesting unique document titles with pattern: " + pattern);

        List<String> titles = budgetDocumentService.getDocumentTitlesWithPattern(pattern);

        return ResponseEntity.ok(titles);
    }
}
