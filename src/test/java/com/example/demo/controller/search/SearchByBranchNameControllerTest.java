package com.example.demo.controller.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.BranchRecord;
import com.example.demo.service.BranchService;

@ExtendWith(MockitoExtension.class)
public class SearchByBranchNameControllerTest {

    @Mock
    private BranchService branchService;

    @InjectMocks
    private SearchByBranchNameController controller;

    @Test
    void invoke_test_meaningful_name() {
        // Given
        String keyword = "keyword";
        BranchRecord branch = new BranchRecord((short) 1, "contain keyword", null);
        List<BranchRecord> serviceResult = List.of(branch);

        // Mock the service layer to return the predefined list of branch records
        when(branchService.searchBranchByName(keyword)).thenReturn(serviceResult);

        // When
        ResponseEntity<List<SearchByBranchNameResponse>> response = controller.invoke(keyword);

        // Then
        // Verify the HTTP response status;
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<SearchByBranchNameResponse> responseBody = response.getBody();
        assertNotNull(responseBody);

        // Verify the mapping with null cost
        SearchByBranchNameResponse mappedResponse = responseBody.get(0);
        assertEquals(branch.code(), mappedResponse.code());
        assertEquals(branch.name(), mappedResponse.name());
        assertNull(mappedResponse.cost(), "Cost should be null as per @JsonInclude(JsonInclude.Include.NON_NULL) and input");
    }
}
