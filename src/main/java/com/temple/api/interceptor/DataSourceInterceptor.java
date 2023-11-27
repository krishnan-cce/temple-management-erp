package com.temple.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// Dont need as per new method
@Slf4j
@Component
public class DataSourceInterceptor implements HandlerInterceptor {

//    private final DataSourceServiceImpl dataSourceServiceImpl;

//    public DataSourceInterceptor(DataSourceServiceImpl dataSourceServiceImpl) {
//        this.dataSourceServiceImpl = dataSourceServiceImpl;
//    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        String currentYear = ContextHolder.getCurrentYear();

//        String currentYear = (String) request.getSession().getAttribute("currentYear");
//        System.out.println("Interceptor Year in ContextHolder: "+ currentYear);
//        dataSourceServiceImpl.setDataSource(request, currentYear);
        return true;
    }
}

//    --BUG
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        HttpSession session = request.getSession();
//        String currentYear = (String) session.getAttribute("currentYear");
//        String finYear = request.getParameter("finYear");
//
//        if (finYear != null) {
//            if (finYear.equals("2023")) {
//                request.setAttribute("database", DataSourceType.DB1);
//            } else if (finYear.equals("2022")) {
//                request.setAttribute("database", DataSourceType.DB2);
//            }
//            // Update the currentYear attribute in the session
//            session.setAttribute("currentYear", finYear);
//        } else if (currentYear != null) {
//            if (currentYear.equals("2023")) {
//                request.setAttribute("database", DataSourceType.DB1);
//            } else if (currentYear.equals("2022")) {
//                request.setAttribute("database", DataSourceType.DB2);
//            }
//        }
//
//        return true;
//    }

//        --- OLD METHOD
//        String contextPath = request.getServletContext().getContextPath();
//        String finyear2023 = contextPath + "/2023";
//        String finyear2022 = contextPath + "/2022";
//
//        String uri = request.getRequestURI();
//        System.out.println("URI:" + uri);
//
//        if (uri.startsWith(finyear2023)) {
//            request.setAttribute("database", DataSourceType.DB1);
//        } else if (uri.startsWith(finyear2022)) {
//            request.setAttribute("database", DataSourceType.DB2);
//        }
//        return true;
