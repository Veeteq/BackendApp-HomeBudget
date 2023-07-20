package com.veeteq.finance.budget.service.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.veeteq.finance.budget.dto.BudgetDocumentDTO;
import com.veeteq.finance.budget.mapper.BudgetDocumentMapper;
import com.veeteq.finance.budget.model.BudgetDocument;
import com.veeteq.finance.budget.repository.BudgetDocumentRepository;
import com.veeteq.finance.budget.service.BudgetDocumentService;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class BudgetDocumentServiceJpa implements BudgetDocumentService {
  private static final Logger LOG = LoggerFactory.getLogger(BudgetDocumentServiceJpa.class);

  private final BudgetDocumentRepository budgetDocumentRepository;
  private final BudgetDocumentMapper mapper;

  @Autowired
  public BudgetDocumentServiceJpa(BudgetDocumentRepository budgetDocumentRepository,
                                  BudgetDocumentMapper mapper) {
    this.budgetDocumentRepository = budgetDocumentRepository;
    this.mapper = mapper;
  }

  @Override
  @Transactional
  public BudgetDocumentDTO saveDocument(BudgetDocumentDTO documentDto) {
    LOG.info("Saving new document");

    BudgetDocument document = mapper.toEntity(documentDto);

    BudgetDocument savedDocument = budgetDocumentRepository.save(document);

    BudgetDocumentDTO result = mapper.toDto(savedDocument);
    return result;
  }

  @Override
  @Modifying
  public void updateDocument(BudgetDocumentDTO document, Long id) {
    LOG.info("Updating existing document with id: " + id);
  }

  @Override
  public void deleteDocument(Long id) {
    LOG.info("Deleting budget document with id: " + id);

    BudgetDocument savedDocument = budgetDocumentRepository.getReferenceById(id);

    budgetDocumentRepository.delete(savedDocument);
  }

  @Override
  public List<String> getDocumentTitlesWithPattern(String pattern) {
    return budgetDocumentRepository.findDistinctByDocumentTitleContainingIgnoreCase(pattern);
  }

}
