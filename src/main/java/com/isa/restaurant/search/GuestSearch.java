package com.isa.restaurant.search;

import com.isa.restaurant.domain.Guest;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Q on 09-May-17.
 */

@Repository
@Transactional
public class GuestSearch
{
    @PersistenceContext
    private EntityManager entityManager;

    public List<Guest> searchAll(String text)
    {
        FullTextEntityManager ftem = Search.getFullTextEntityManager(entityManager);

        // HIBERNATE DSL QUERY BUILDER
        QueryBuilder queryBuilder = ftem.getSearchFactory().buildQueryBuilder().forEntity(Guest.class).get();

        //String jsonQuery = String.format("{'query': {'multi_match': {'query': %s, 'fields': ['firstName', 'lastName']}}}", text.toLowerCase());

        @SuppressWarnings("unchecked")
        List<Guest> results = ftem.createFullTextQuery(
                queryBuilder.keyword().onFields("firstName", "lastName", "email").matching(text).createQuery(), Guest.class)
                //.setProjection("firstName", "lastName", "email", ElasticsearchProjectionConstants.SCORE)
                .getResultList();

        return results;
    }
}
