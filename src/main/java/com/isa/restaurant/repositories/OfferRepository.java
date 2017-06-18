package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Milos on 10-Jun-17.
 */
public interface OfferRepository extends JpaRepository<Offer, Long>
{
    Offer findByProviderIdAndShoppingListId(Long providerId, Long shoppingListId);
}
