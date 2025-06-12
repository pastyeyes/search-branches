package com.example.demo.controller.search.mapper;

import com.example.demo.controller.search.SearchByBranchNameResponse;
import com.example.demo.model.BranchRecord;

public class BranchRecordToSearchByNameResponse {
    public static SearchByBranchNameResponse map(BranchRecord branchRecord) {
        if (branchRecord == null) {
            return null;
        }
        return new SearchByBranchNameResponse(branchRecord.code(), branchRecord.name(), branchRecord.cost());
    }
}
