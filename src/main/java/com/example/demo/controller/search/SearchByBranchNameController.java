package com.example.demo.controller.search;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.advice.exception.NotFoundException;
import com.example.demo.controller.search.mapper.BranchRecordToSearchByNameResponse;
import com.example.demo.service.BranchService;


@RestController
public class SearchByBranchNameController {

  private final BranchService branchService;

  public SearchByBranchNameController(BranchService branchService) {
    this.branchService = branchService;
  }

  /**
   * Only accept not empty branch names. 
   * Even though data.json contains branches with empty names, I intentionally decided to not search for them.
   * Given the spec.yaml does not explicitly state that empty branch names should be searchable,
   *
   * TODO: Add the usecase to search for branches with empty names.
   * TODO: Add fuzzy search for branch names and/or substring search.
   */
  @GetMapping("/search")
  public ResponseEntity<List<SearchByBranchNameResponse>> invoke(@RequestParam("keyword") String branchName) {
    if(branchName.isBlank()){
      throw new IllegalArgumentException("Branch name cannot be empty");
    }
    
    final var result = branchService.searchBranchByName(branchName);

    if (result == null) {
      throw new NotFoundException("Branch not found for name: " + branchName);
    }
    
    // ... map the result to SearchByNameResponse ...
    var mappedResult = result.stream().map(BranchRecordToSearchByNameResponse::map)
      .toList();

    return ResponseEntity.ok(mappedResult);
  }
}
