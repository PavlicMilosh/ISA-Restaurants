package com.isa.restaurant.search;

import com.isa.restaurant.domain.Friendship;
import com.isa.restaurant.domain.Guest;
import org.apache.lucene.search.Query;
//import org.hibernate.search.elasticsearch.ElasticsearchQueries;
import org.hibernate.search.exception.EmptyQueryException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.engine.spi.QueryDescriptor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
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

        //QueryDescriptor query = ElasticsearchQueries.fromJson("{ 'query': { 'match' : { 'lastName' : 'Brand' } } }");
        try
        {
            @SuppressWarnings("unchecked")
            List<Guest> result = ftem.createFullTextQuery(
                    queryBuilder.keyword().onFields("firstName", "lastName", "email").matching("*" + text + "*").createQuery()
                    , Guest.class).getResultList();
            return result;
        }
        catch (EmptyQueryException e)
        {
            return new ArrayList<>();
        }

    }


    public List<Guest> searchFriends(Long guestId, String text)
    {
        FullTextEntityManager ftem = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = ftem.getSearchFactory().buildQueryBuilder().forEntity(Friendship.class).get();
        Query luceneQuery = queryBuilder.bool()
                .should(queryBuilder.keyword().onField("firstUser.user_id").matching(guestId).createQuery())
                .should(queryBuilder.keyword().onField("lastUser.user_id").matching(guestId).createQuery())
                .must(queryBuilder.keyword().onFields("firstUser.firstName", "firstUser.lastName", "firstUser.email",
                                                      "secondUser.firstName", "secondUser.lastName", "secondUser.email").matching(text).createQuery())
                .createQuery();

        @SuppressWarnings("unchecked")
        List<Friendship> friendships = ftem.createFullTextQuery(luceneQuery, Friendship.class).getResultList();
        List<Guest> friends = new ArrayList<>();

        for (Friendship f : friendships)
            friends.add(f.getFriend(guestId));

        return friends;
    }
}
