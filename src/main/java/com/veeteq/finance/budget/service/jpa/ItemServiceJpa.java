package com.veeteq.finance.budget.service.jpa;

import com.veeteq.finance.budget.dto.ItemDTO;
import com.veeteq.finance.budget.dto.PageResponse;
import com.veeteq.finance.budget.mapper.ItemMapper;
import com.veeteq.finance.budget.model.Item;
import com.veeteq.finance.budget.repository.ItemRepository;
import com.veeteq.finance.budget.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceJpa implements ItemService {
  private static final Logger LOG = LoggerFactory.getLogger(ItemServiceJpa.class);

  private final ItemRepository itemRepository;

  @Autowired
  public ItemServiceJpa(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @Override
  public Item findById(Long id) {
    return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Could not find item with id: " + id));
  }

  @Override
  public Item save(Item item) {
    return itemRepository.save(item);
  }

  @Override
  @Transactional
  public PageResponse<ItemDTO> findAll(PageRequest pageRequest) {
    LOG.info("getSummary: page=" + pageRequest.getPageNumber() + ", size=" + pageRequest.getPageSize() + ", dir=" + pageRequest.getSort().toString());

    ItemMapper mapper = new ItemMapper();

    Page<Item> page = itemRepository.findAllWithCategory(pageRequest);
    List<ItemDTO> content = page.getContent()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());

    PageResponse<ItemDTO> response = new PageResponse<ItemDTO>()
            .setContent(content)
            .setPageNo(page.getNumber())
            .setPageSize(page.getSize())
            .setTotalElements(page.getTotalElements())
            .setTotalPages(page.getTotalPages())
            .setLast(page.isLast());

    return response;
  }
}
