package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.RegionDTO;
import com.isa.restaurant.domain.DTO.RestaurantDTO;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.repositories.*;
import com.isa.restaurant.search.RestaurantSearch;
import com.isa.restaurant.services.RestaurantOrdersService;
import com.isa.restaurant.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Milos on 13-Apr-17.
 */
@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService
{
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final DrinkRepository drinkRepository;
    private final TableRepository tableRepository;
    private final RestaurantMarkRepository restaurantMarkRepository;
    private final RestaurantSearch restaurantSearch;
    private final DishTypeRepository dishTypeRepository;
    private final RestaurantOrdersService restaurantOrdersService;


    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
                                 UserRepository userRepository,
                                 DishRepository dishRepository,
                                 DrinkRepository drinkRepository,
                                 TableRepository tableRepository,
                                 RestaurantMarkRepository restaurantMarkRepository,
                                 RestaurantSearch restaurantSearch,
                                 DishTypeRepository dishTypeRepository,
                                 RestaurantOrdersService restaurantOrdersService)
    {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
        this.restaurantMarkRepository = restaurantMarkRepository;
        this.restaurantSearch = restaurantSearch;
        this.dishTypeRepository = dishTypeRepository;
        this.restaurantOrdersService = restaurantOrdersService;
    }


    @Override
    public Restaurant addRestaurant(Restaurant restaurant)
    {
        Restaurant saved = null;
        try
        {
            saved = restaurantRepository.save(restaurant);
        }
        catch(Exception e)
        {
        }
        if(saved.getId()!=null)
        {
            restaurantOrdersService.addRestaurantOrders(saved.getId());
        }
        return saved;
    }


    @Override
    public List<Restaurant> getRestaurants()
    {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }


    @Override
    public List<RestaurantDTO> getRestaurants(Long guestId)
    {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDTO> restaurantDTOs = new ArrayList<>();
        for (Restaurant r : restaurants)
        {
            Double meanMark = 0.0;
            Double friendsMark = 0.0;
            /*TODO: Djuro ovo implementiraj imas metodu za dobijanje meanMark-a restorana u RestaurantMarkRepository,
            *       a friendsMark ces morati da dobavis prvo sve prijatelje na osnovu guesta, izvuces njihove id-eve i tek onda
            *       pomocu metode u RestaurantMarkRepositoryju dobijes jedan mark za guestId i restaurantId... skupis sve te
            *       i onda nadjes srednju vrednost*/
            restaurantDTOs.add(new RestaurantDTO(r, meanMark, friendsMark));
        }
        return restaurantDTOs;
    }


    @Override
    public Restaurant updateRestaurant(Restaurant restaurant)
    {
        Restaurant rest = getRestaurant(restaurant.getId());
        for(Dish d : restaurant.getDishes())
        {
            Dish dish = rest.getDish(d.getId());
            if (dish == null)
            {
                d.setRestaurant(rest);
                rest.addDish(d);
            }
            else
            {
                dish.setName(d.getName());
                dish.setDescription(d.getDescription());
                dish.setPrice(d.getPrice());
                dish.setRestaurant(rest);

            }
        }
        for(Drink d : restaurant.getDrinks())
        {
            Drink drink = rest.getDrink(d.getId());
            if(drink == null)
            {
                d.setRestaurant(rest);
                rest.addDrink(d);
            }
            else
            {
                drink.setName(d.getName());
                drink.setDescription(d.getDescription());
                drink.setPrice(d.getPrice());
                drink.setRestaurant(rest);
            }
        }

        for(RestaurantManager rm : rest.getManagers())
            rm.setRestaurant(rest);


        for(Region r : restaurant.getRegions())
        {
            Region region = rest.getRegion(r.getId());
            if(region == null)
            {
                rest.addRegion(r);
                r.setRestaurant(rest);
            }
            for (RestaurantTable t : r.getTables())
            {
                RestaurantTable table = rest.getTable(t.getId());
                if(table == null)
                {
                    t.setRegion(r);
                    t.setRestaurant(rest);
                    rest.addTable(t);
                }
                else
                {
                    table.setRegion(r);
                    table.setTop(t.getTop());
                    table.setLeft(t.getLeft());
                    table.setAngle(t.getAngle());
                    table.setSeats(t.getSeats());
                }
            }
        }

        Restaurant retval = restaurantRepository.save(rest);
        return retval;
    }


    @Override
    public Restaurant getRestaurant(Long id)
    {
        Restaurant r = restaurantRepository.findOne(id);
        r.getManagers();
        r.getDrinks();
        r.getDishes();
        r.getTables();
        return r;
    }


    @Override
    @Transactional
    public Restaurant getRestaurant(String name)
    {
        Restaurant r = restaurantRepository.findByName(name);
        r.getManagers();
        r.getDrinks();
        r.getDishes();
        r.getTables();
        r.getRegions();
        r.getTables();
        return r;
    }


    @Override
    public Restaurant getByManagerId(Long managerId)
    {
        RestaurantManager rm = (RestaurantManager) userRepository.findOne(managerId);
        try
        {
            return rm.getRestaurant();
        }catch(NullPointerException e)
        {
            return null;
        }
    }


    @Override
    public UserDTO addRestaurantManager(RestaurantManager restaurantManager, Long restaurantId)
    {
        Restaurant r = restaurantRepository.findOne(restaurantId);
        if(r == null)
            return null;
        restaurantManager.setRestaurant(r);
        userRepository.save(restaurantManager);
        return new UserDTO(restaurantManager);
    }


    @Override
    public UserDTO addWaiter(Waiter waiter, Long restaurantId)
    {
        Restaurant r = restaurantRepository.findOne(restaurantId);
        waiter.setRestaurant(r);
        userRepository.save(waiter);
        return new UserDTO(waiter);
    }


    @Override
    public UserDTO addBartender(Bartender bartender, Long restaurantId)
    {
        Restaurant r = restaurantRepository.findOne(restaurantId);
        bartender.setRestaurant(r);
        userRepository.save(bartender);
        return new UserDTO(bartender);
    }


    @Override
    public UserDTO addCook(Cook cook, Long restaurantId)
    {
        Restaurant r = restaurantRepository.findOne(restaurantId);
        cook.setRestaurant(r);
        userRepository.save(cook);
        return new UserDTO(cook);
    }


    @Override
    public List<UserDTO> getWorkersByRMId(Long managerId)
    {
        try
        {
            RestaurantManager rm = (RestaurantManager) userRepository.findOne(managerId);
            Restaurant r = rm.getRestaurant();
            List<UserDTO> retval = new ArrayList<>();
            for (Bartender b : r.getBartenders())
            {
                retval.add(new UserDTO(b));
            }
            for (Waiter w : r.getWaiters())
            {
                retval.add(new UserDTO(w));
            }
            for (Cook c : r.getCooks())
            {
                retval.add(new UserDTO(c));
            }
            return retval;
        }
        catch(NullPointerException e)
        {
            return null;
        }
    }


    @Override
    public List<RestaurantDTO> searchRestaurantsByNameAndDescription(String searchText)
    {
        List<Restaurant> restaurants = restaurantSearch.searchAll(searchText);
        List<RestaurantDTO> ret = new ArrayList<>();
        for (Restaurant r : restaurants)
            ret.add(new RestaurantDTO(r));
        return ret;
    }


    @Override
    public DishType addDishType(DishType dishType)
    {
        DishType saved=null;
        saved=dishTypeRepository.save(dishType);
        return saved;
    }


    @Override
    public List<RegionDTO> getRegions(Long restaurantId)
    {
        Restaurant restaurant = restaurantRepository.findOne(restaurantId);
        if (restaurant == null) return null;

        List<RegionDTO> regionDTOs = new ArrayList<>();
        Set<Region> regions = restaurant.getRegions();

        for (Region r : regions)
            regionDTOs.add(new RegionDTO(r));

        return regionDTOs;
    }


    @Override
    public Integer getMedianMark(Long restaurantId)
    {
        return 0;
    }
}
