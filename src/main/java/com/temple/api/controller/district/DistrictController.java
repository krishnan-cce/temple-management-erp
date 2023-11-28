package com.temple.api.controller.district;

import com.temple.api.payload.district.DistrictResponse;
import com.temple.api.payload.temple.TempleResponse;
import com.temple.api.service.district.DistrictService;
import com.temple.api.utils.AppConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/district")
public class DistrictController {

    private DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public DistrictResponse getAllDistrictByState(
            @RequestParam(value = "stateId") long stateId,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ){
        return districtService.listAllByState(stateId,pageNo, pageSize);
    }

}
