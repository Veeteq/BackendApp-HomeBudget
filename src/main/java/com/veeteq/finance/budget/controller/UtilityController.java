package com.veeteq.finance.budget.controller;

import java.util.Currency;
import java.util.LinkedHashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veeteq.finance.budget.model.DocumentType;
import com.veeteq.finance.budget.model.PaymentMethod;

@RestController
@RequestMapping(path = "/api/common")
@CrossOrigin(origins = "http://localhost:4203")
public class UtilityController {
    private static final Logger LOG = LoggerFactory.getLogger(UtilityController.class);

    @Value(value = "${currency.code.list}")
    private String[] currencyCodes;

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
    public ResponseEntity<DocumentType[]> getDocumentTypes() {

        return ResponseEntity.ok(DocumentType.values());
    }

    @GetMapping(path = "/paymentMethods", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentMethod[]> getPaymentMethods() {

        return ResponseEntity.ok(PaymentMethod.values());
    }

}
