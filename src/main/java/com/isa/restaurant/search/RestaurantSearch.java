package com.isa.restaurant.search;

import com.isa.restaurant.domain.Restaurant;
import org.hibernate.search.exception.EmptyQueryException;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Q on 14-May-17.
 */
@Repository
@Transactional
public class RestaurantSearch
{
    @PersistenceContext
    private EntityManager entityManager;


    public List<Restaurant> searchAll(String text)
    {
        FullTextEntityManager ftem = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = ftem.getSearchFactory().buildQueryBuilder().forEntity(Restaurant.class).get();

        try
        {
            @SuppressWarnings("unchecked")
            List<Restaurant> result = ftem.createFullTextQuery(
                    queryBuilder.keyword().onFields("name", "description").matching("*" + text + "*").createQuery()
                    , Restaurant.class).getResultList();
            return result;
        }
        catch (EmptyQueryException e)
        {
            return new ArrayList<>();
        }
    }
}
