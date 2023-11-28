package com.temple.api.service.district;

import com.temple.api.entity.state.StateMaster;
import com.temple.api.payload.district.DistrictDto;
import com.temple.api.payload.district.DistrictResponse;

import java.util.List;

public interface DistrictService {

    DistrictResponse getAllDistricts(int pageNo, int pageSize, String sortBy, String sortDir);

    DistrictResponse listAllByState(long stateId,int pageNo, int pageSize);

    DistrictDto createDistrict(DistrictDto districtDto);

    DistrictDto updateDistrict(DistrictDto districtDto,long districtId);

    DistrictDto getByDistrictId(long districtId);

    void deleteDistrictById(long id);
}
