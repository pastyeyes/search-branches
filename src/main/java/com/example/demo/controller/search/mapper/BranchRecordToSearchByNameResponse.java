package com.example.demo.controller.search.mapper;

import com.example.demo.controller.search.SearchByNameResponse;
import com.example.demo.model.BranchRecord;

public class BranchRecordToSearchByNameResponse {
    public static SearchByNameResponse map(BranchRecord branchRecord) {
        if (branchRecord == null) {
            return null;
        }
        return new SearchByNameResponse(branchRecord.code(), branchRecord.name(), branchRecord.cost());
    }
}
