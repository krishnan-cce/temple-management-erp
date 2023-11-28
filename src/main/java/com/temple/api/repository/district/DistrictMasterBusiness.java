package com.temple.api.repository.district;

import com.temple.api.entity.district.DistrictMaster;
import com.temple.api.entity.state.StateMaster;
import com.temple.api.repository.common.GenericDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistrictMasterBusiness extends GenericDAO<DistrictMaster> {
    public DistrictMasterBusiness() {
        super(DistrictMaster.class);
    }

    public List<DistrictMaster> getAllDistrictByStateWithPagination(StateMaster stateMaster, int pageNo, int pageSize) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stateMaster", stateMaster);
        String query = "select d from DistrictMaster d where d.stateMaster = :stateMaster order by d.district asc";
        return listByQueryWithPagination(query, parameters, pageNo, pageSize);
    }

}
