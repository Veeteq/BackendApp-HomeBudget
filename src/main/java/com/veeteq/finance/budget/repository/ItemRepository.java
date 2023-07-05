package com.veeteq.finance.budget.repository;

import com.veeteq.finance.budget.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {

  @Query(value = "SELECT i " +
                 "  FROM Item i " +
                 "  JOIN FETCH i.category",
         nativeQuery = false,
         countQuery = "SELECT COUNT(i) FROM Item i")
  Page<Item> findAllWithCategory(PageRequest pageRequest);
}
