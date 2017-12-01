package com.highlander.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.util.List;

@NoRepositoryBean
public class BaseRepositoryImpl<T> extends SimpleJpaRepository<T, Long> implements BaseRepository<T> {

    private EntityManager em;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> listBySQL(String sql) {
        return em.createNativeQuery(sql).getResultList();
    }

}
