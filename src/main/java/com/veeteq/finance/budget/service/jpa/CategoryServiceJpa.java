package com.veeteq.finance.budget.service.jpa;

import com.veeteq.finance.budget.model.Category;
import com.veeteq.finance.budget.repository.CategoryRepository;
import com.veeteq.finance.budget.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceJpa implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryServiceJpa(CategoryRepository categoryReposiory) {
    this.categoryRepository = categoryReposiory;
  }

  @Override
  public Category findById(Long id) {
    return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Could not find category with id: " + id));
  }

  @Override
  public Category save(Category category) {
    return categoryRepository.save(category);
  }
}
