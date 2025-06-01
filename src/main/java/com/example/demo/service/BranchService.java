package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.BranchRecord;
import com.example.demo.repository.BranchRepository;

@Service
public class BranchService {
  
  private final BranchRepository branchRepository;

  public BranchService(BranchRepository branchRepository) {
    this.branchRepository = branchRepository;
  }

  public List<BranchRecord> searchBranchByName(String branchName) {
    // ... Some sanity checks ...
    return branchRepository.findBranchByName(branchName);
  }
}
