package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Offer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Milos on 18-Jun-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OfferRepositoryTest
{
    @Autowired
    private OfferRepository offerRepository;

    @Test
    public void testFindOne()
    {
        Offer found = offerRepository.findOne(0l);
        Assert.assertEquals(0l, found.getId().longValue());
        Assert.assertEquals(100l, found.getAmount().longValue());
        Assert.assertEquals(-5l, found.getProvider().getId().longValue());
        Assert.assertEquals(0l, found.getShoppingList().getId().longValue());
        Assert.assertEquals(0l, found.getVersion().longValue());
    }

    @Test
    public void testFindByProviderIdAndShoppingListId()
    {
        Offer found = offerRepository.findByProviderIdAndShoppingListId(-5l, 0l);
        Assert.assertEquals(0l, found.getId().longValue());
        Assert.assertEquals(100l, found.getAmount().longValue());
        Assert.assertEquals(-5l, found.getProvider().getId().longValue());
        Assert.assertEquals(0l, found.getShoppingList().getId().longValue());
        Assert.assertEquals(0l, found.getVersion().longValue());
    }

}
