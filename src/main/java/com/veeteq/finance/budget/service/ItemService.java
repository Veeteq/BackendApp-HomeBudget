package com.veeteq.finance.budget.service;

import com.veeteq.finance.budget.dto.ItemDTO;
import com.veeteq.finance.budget.dto.PageResponse;
import com.veeteq.finance.budget.model.Item;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ItemService {

  Item findById(Long id);

  Item save(Item item);

  PageResponse<ItemDTO> findAll(PageRequest pageRequest);

  List<ItemDTO> findByName(String name);

  long count();
}
