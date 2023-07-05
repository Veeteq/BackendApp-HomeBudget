package com.veeteq.finance.budget.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veeteq.finance.budget.dto.ItemDTO;
import com.veeteq.finance.budget.dto.PageResponse;
import com.veeteq.finance.budget.service.ItemService;

@RestController
@RequestMapping(path = "/api/items")
@CrossOrigin(origins = "http://localhost:4203")
public class ItemController {
  private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);

  private final ItemService itemService;

  @Autowired
  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping(path = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PageResponse<ItemDTO>> getItems(@RequestParam(name = "page",   defaultValue = "0") int page,
                                                        @RequestParam(name = "size",   defaultValue = "25") int size,
                                                        @RequestParam(name = "column", defaultValue = "id") String column,
                                                        @RequestParam(name = "dir",    defaultValue = "ASC") String dir) {
    LOG.info("Loading items page: page=" + page + ", size=" + size + ", column: " + column + ", dir: " + dir);

    Sort.Direction sortDir = dir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort.Order order = new Sort.Order(sortDir, column).ignoreCase();
    Sort sort = Sort.by(order);
    PageRequest pageRequest = PageRequest.of(page, size, sort);

    PageResponse<ItemDTO> response = itemService.findAll(pageRequest);

    return ResponseEntity.ok(response);
  }

  @GetMapping(path = "/{pattern}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<ItemDTO>> getProductsByName(@PathVariable(name = "pattern", required = true) String pattern) {
    LOG.info("Processing search request for items with name: " + pattern);

    List<ItemDTO> result = this.itemService.findByName(pattern);
    return ResponseEntity.ok(result);
  }
}
