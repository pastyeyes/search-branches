package com.example.demo.repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BranchRecord;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Repository
public class BranchRepository {
  /** 
   * I will place the data in this repository for simplicity.
   * I will not optimize for performance in this example.
   * Database lookups are simulated by searching through the list of branches.
   * TODO: Optimize data access through indexing/data structure.
   */

  private final List<BranchRecord> branches = new ArrayList<>();
  private final ResourceLoader resourceLoader;

  // Injection of resource loader
  public BranchRepository(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @PostConstruct
  public void initialLoad(){
    try (InputStream is = resourceLoader
            .getResource("classpath:datasource/data.json")
            .getInputStream()) {
      final ObjectMapper mapper = new ObjectMapper();
      final JsonNode branchesJsonArray = mapper.readTree(is).get("branches");
      final var branches = mapper.readValue(
        branchesJsonArray.traverse(),
        new TypeReference<List<BranchRecord>>() {}
      );
      this.branches.addAll(branches);
    }catch(Exception e) {
      throw new RuntimeException("Failed to load branch data", e);
    }
  }

  // Assuming a partial match search for branch names.
  public List<BranchRecord> findBranchByName(String branchName) {
    return branches.stream()
      .filter(e -> e.name()!=null && !e.name().isEmpty() && e.name().toUpperCase().contains(branchName.toUpperCase()))
      .toList();
  }
}
