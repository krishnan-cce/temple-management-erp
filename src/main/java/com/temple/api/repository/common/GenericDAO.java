package com.temple.api.repository.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Map;
import java.util.List;
import java.util.Map;

public abstract class GenericDAO<T> {

    @PersistenceContext
    private EntityManager em;
    private Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> listByQuery(String jpqlQuery, Map<String, Object> parameters) {
        try {
            Query query = em.createQuery(jpqlQuery);
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            @SuppressWarnings("unchecked")
            List<T> resultList = query.getResultList();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    public List<T> listByQueryWithPagination(String jpqlQuery, Map<String, Object> parameters, int pageNo, int pageSize) {
        try {
            Query query = em.createQuery(jpqlQuery);
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            int start = pageNo * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);

            @SuppressWarnings("unchecked")
            List<T> resultList = query.getResultList();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
