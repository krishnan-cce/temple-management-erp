package com.temple.api.config;


import com.temple.api.entity.User;
import com.temple.api.utils.ContextHolder;
import com.temple.api.utils.DataSourceType;
import com.temple.api.utils.UserUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class RoutingDataSource extends AbstractRoutingDataSource {


    private UserUtils userUtils;

    @Autowired
    public void setUserUtils(UserUtils userUtils) {
        this.userUtils = userUtils;
    }
    @Override
    protected Object determineCurrentLookupKey() {

        DataSourceType dataSourceType = DataSourceType.DB1;

        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();

            HttpSession session = request.getSession();
            String finYear = (String) session.getAttribute("finYear");

            if (finYear != null) {
                if (finYear.equals("2023")) {
                    dataSourceType = DataSourceType.DB1;
                } else if (finYear.equals("2022")) {
                    dataSourceType = DataSourceType.DB2;
                }
            }
        }
//        if (RequestContextHolder.getRequestAttributes() != null) {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                    .getRequest();
//            dataSourceType = (DataSourceType) request.getAttribute("database");
//        }

        return dataSourceType;
    }

    public void initDataSources(DataSource dataSource1, DataSource dataSource2) {
        Map<Object, Object> dsMap = new HashMap<Object, Object>();
        dsMap.put(DataSourceType.DB1, dataSource1);
        dsMap.put(DataSourceType.DB2, dataSource2);

        this.setTargetDataSources(dsMap);
    }
}