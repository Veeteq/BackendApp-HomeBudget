package com.veeteq.finance.budget.service;

import com.veeteq.finance.budget.model.Category;

public interface CategoryService {
  Category findById(Long id);
  Category save(Category category);
  long count();
}
