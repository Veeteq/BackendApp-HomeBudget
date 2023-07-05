package com.veeteq.finance.budget.mapper;

import com.veeteq.finance.budget.dto.ItemDTO;
import com.veeteq.finance.budget.model.Item;

public class ItemMapper {

  public ItemDTO toDto(Item entity) {
    if (entity == null) {
      return null;
    }
    ItemDTO dto = new ItemDTO()
            .setId(entity.getId())
            .setName(entity.getName())
            .setCategoryName(entity.getCategory().getName());
    return dto;
  }
}
