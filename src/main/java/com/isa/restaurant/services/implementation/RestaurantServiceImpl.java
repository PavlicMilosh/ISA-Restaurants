package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.*;
import com.isa.restaurant.repositories.*;
import com.isa.restaurant.search.RestaurantSearch;
import com.isa.restaurant.services.FriendshipService;
import com.isa.restaurant.services.RestaurantService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
    private final ReservationRepository reservationRepository;
    private final RestaurantMarkRepository restaurantMarkRepository;
    private final RestaurantSearch restaurantSearch;
    private final DishTypeRepository dishTypeRepository;
    private final FriendshipService friendshipService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
                                 UserRepository userRepository,
                                 DishRepository dishRepository,
                                 DrinkRepository drinkRepository,
                                 TableRepository tableRepository,
                                 ReservationRepository reservationRepository,
                                 RestaurantMarkRepository restaurantMarkRepository,
                                 RestaurantSearch restaurantSearch,
                                 DishTypeRepository dishTypeRepository,
                                 FriendshipService friendshipService)
    {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
        this.reservationRepository = reservationRepository;
        this.restaurantMarkRepository = restaurantMarkRepository;
        this.restaurantSearch = restaurantSearch;
        this.dishTypeRepository = dishTypeRepository;
        this.friendshipService = friendshipService;
    }


    @Override
    public Restaurant addRestaurant(Restaurant restaurant)
    {
        Restaurant saved = null;
        Restaurant sameName = restaurantRepository.findByName(restaurant.getName());
        if(sameName != null)
            return null;
        try
        {
            saved = restaurantRepository.save(restaurant);
        }
        catch(Exception e)
        {}
        return saved;
    }


    @Override
    public List<Restaurant> getRestaurants()
    {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }


    @Override
    @Transactional
    public List<RestaurantDTO> getRestaurants(Long guestId)
    {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDTO> restaurantDTOs = new ArrayList<>();
        for (Restaurant r : restaurants)
        {
            Double meanMark = 0.0 ;
            Double friendsMark = 0.0;
            Integer visits = 0;

            if(!r.getRestaurantMarks().isEmpty())
                meanMark = restaurantMarkRepository.getRestaurantMeanMark(r.getId());


            Set<GuestDTO> friends = friendshipService.getFriends(guestId);
            for (GuestDTO friend : friends)
            {
                RestaurantMark restaurantMark = restaurantMarkRepository.getMarkByGuestIdAndRestaurantId(guestId, r.getId());
                if (restaurantMark != null)
                    friendsMark += restaurantMark.getValue();
            }
            if (friendsMark != 0)
                friendsMark /= friends.size();


            for (Reservation reservation: reservationRepository.getReservationsByRestaurantId(r.getId()))
            {
                if (reservation.getStatus().equalsIgnoreCase(ReservationStatus.FINISHED))
                {
                    visits++;
                    for (Invitation invitation : reservation.getInvitations())
                        if (invitation.getStatus().equalsIgnoreCase(InvitationStatus.ACCEPTED))
                            visits++;
                }
            }

            restaurantDTOs.add(new RestaurantDTO(r, meanMark, friendsMark, visits));
        }
        return restaurantDTOs;
    }


    @Override
    public Restaurant updateRestaurant(Restaurant restaurant)
    {
        Restaurant rest = getRestaurant(restaurant.getId());

        for(DishType dt : restaurant.getDishTypes())
        {
            DishType dishType = rest.getDishType(dt.getId());
            if (dishType == null)
            {
                dt.setRestaurant(rest);
                rest.addDishType(dt);
            }
            else
            {
                dishType.setName(dt.getName());
                dishType.setRestaurant(rest);
            }
        }

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

        rest.setAddress(restaurant.getAddress());

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
        r.getDishTypes();
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
        r.getDishTypes();
        return r;
    }


    @Override
    public Restaurant getByManagerId(Long managerId)
    {
        RestaurantManager rm = (RestaurantManager) userRepository.findOne(managerId);
        try
        {
            Restaurant r = rm.getRestaurant();
            Restaurant rr = new Restaurant(r);
            List<Reservation> reservations = reservationRepository.findAll();
            for(Reservation res : reservations)
            {
                Calendar c = Calendar.getInstance();
                if(res.getDateTimeEnd().after(c.getTime()))
                {
                    for (RestaurantTable resT : res.getTables())
                    {
                        try
                        {
                            rr.getTables().remove(resT);
                        }
                        catch(Exception e)
                        {
                            continue;
                        }
                    }
                }
            }
            return rr;
        }catch(NullPointerException e)
        {
            return null;
        }
    }


    @Override
    public UserDTO addRestaurantManager(RestaurantManager restaurantManager, Long restaurantId)
    {
        restaurantManager.setPassword(passwordEncoder.encode(restaurantManager.getPassword()));
        Restaurant r = restaurantRepository.findOne(restaurantId);
        if(r == null)
            return null;
        restaurantManager.setRestaurant(r);
        userRepository.save(restaurantManager);
        return new UserDTO(restaurantManager);
    }


    @Override
    public UserDTO addWaiter(Waiter waiter, Long managerId)
    {
        Restaurant r = getByManagerId(managerId);
        waiter.setRestaurant(r);
        userRepository.save(waiter);
        return new UserDTO(waiter);
    }


    @Override
    public UserDTO addBartender(Bartender bartender, Long managerId)
    {
        Restaurant r = getByManagerId(managerId);
        bartender.setRestaurant(r);
        userRepository.save(bartender);
        return new UserDTO(bartender);
    }


    @Override
    public UserDTO addCook(Cook cook, Long managerId)
    {
        Restaurant r = getByManagerId(managerId);
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
    public List<RegionDTO> getRegionsByRMId(Long managerId)
    {
        RestaurantManager rm = (RestaurantManager) userRepository.findOne(managerId);
        Restaurant r = rm.getRestaurant();
        List<RegionDTO> regions = new ArrayList<>();
        for (Region region : r.getRegions())
        {
            regions.add(new RegionDTO(region));
        }
        return regions;
    }

    @Override
    public Integer getMedianMark(Long restaurantId)
    {
        return 0;
    }

    @Override
    public List<RestaurantTableDTO> getTables(Long restaurantId)
    {
        List<RestaurantTable> tables = tableRepository.findByRestaurantId(restaurantId);
        List<RestaurantTableDTO> ret = new ArrayList<>();
        for (RestaurantTable rt : tables)
        {
            ret.add(new RestaurantTableDTO(rt, false));
        }
        return ret;
    }

    @Override
    public Report getReport(Long restaurantId, Date startDate)
    {
        Report report = new Report();
        Restaurant r = restaurantRepository.findOne(restaurantId);
        double restaurantMark = 0;
        for(RestaurantMark rm : r.getRestaurantMarks())
        {
            restaurantMark += rm.getValue();
        }
        restaurantMark /= r.getRestaurantMarks().size();
        report.setRestaurantMark(restaurantMark);

        for(Dish d : r.getDishes())
        {
            report.getDishMarks().put(d, 0.0);
            for(DishMark dm : d.getDishMarks())
                report.getDishMarks().put(d, report.getDishMarks().get(d) + dm.getValue());
            report.getDishMarks().put(d, report.getDishMarks().get(d) / d.getDishMarks().size());
        }

        for(Drink d : r.getDrinks())
        {
            report.getDrinkMarks().put(d, 0.0);
            for(DrinkMark dm : d.getDrinkMarks())
                report.getDrinkMarks().put(d, report.getDrinkMarks().get(d) + dm.getValue());
            report.getDrinkMarks().put(d, report.getDrinkMarks().get(d) / d.getDrinkMarks().size());
        }

        for(Waiter w : r.getWaiters())
        {
            report.getWaiterMarks().put(w, 0.0);
            for(WaiterMark wm : w.getWaiterMarks())
                report.getWaiterMarks().put(w, report.getWaiterMarks().get(w) + wm.getValue());
            report.getWaiterMarks().put(w, report.getWaiterMarks().get(w) / w.getWaiterMarks().size());

            report.getWaiterProfits().put(w, 0.0);
        }

        for(Order o : r.getOrders())
            if(o.getOrderTime().after(startDate) && o.getOrderTime().before(DateUtils.addDays(startDate, 7)))
                report.getWaiterProfits().put(o.getWaiter(), report.getWaiterProfits().get(o.getWaiter()) + o.getPrice());

        return report;
    }

    @Override
    public List<WaiterMarkReport> getWaiterMarkReport(Long restaurantId)
    {
        Restaurant r = restaurantRepository.findOne(restaurantId);
        ArrayList<WaiterMarkReport> marks = new ArrayList<>();
        for(Waiter w : r.getWaiters())
        {
            WaiterMarkReport wmr = new WaiterMarkReport(w.getId(), w.getFirstName(), w.getLastName(), 0.0);
            for(WaiterMark wm : w.getWaiterMarks())
            {
                wmr.setMeanMark(wmr.getMeanMark() + wm.getValue());
            }
            if(w.getWaiterMarks().size() == 0)
                wmr.setMeanMark(0.0);
            else
                wmr.setMeanMark(wmr.getMeanMark() / w.getWaiterMarks().size());
            marks.add(wmr);
        }

        return marks;
    }

    @Override
    public List<DishMarkReport> getDishMarkReport(Long restaurantId)
    {
        Restaurant r = restaurantRepository.findOne(restaurantId);
        ArrayList<DishMarkReport> marks = new ArrayList<>();
        for(Dish d : r.getDishes())
        {
            DishMarkReport dmr = new DishMarkReport(d.getId(), d.getName(), d.getDescription(), 0.0, 0.0, 0, 0);
            for(DishMark dm : d.getDishMarks())
            {
                dmr.setMeanMark(dmr.getMeanMark() + dm.getValue());
                dmr.setNoMarks(dmr.getNoMarks() + 1);
            }
            dmr.setMeanMark(dmr.getMeanMark() / dmr.getNoMarks());
            marks.add(dmr);
        }

        return marks;
    }



    @Override
    public List<CookMarkReport> getCookMarkReport(Long restaurantId)
    {
        Restaurant r = restaurantRepository.findOne(restaurantId);
        ArrayList<CookMarkReport> marks = new ArrayList<>();
        for(Cook c : r.getCooks())
        {
            CookMarkReport cmr = new CookMarkReport();
            cmr.setId(c.getId());
            cmr.setFirstName(c.getFirstName());
            cmr.setLastName(c.getLastName());
            cmr.setMeanMark(0.0);
            List<Order> items = orderRepository.findAll();
            for(Order order : items)
            {
                for(OrderItem item : order.getOrderItems())
                {
                    if (item.getIsDish() && item.getFinished() && item.getUserId() == c.getId())
                    {
                        cmr.setMeanMark(cmr.getMeanMark() + order.getMark());
                        cmr.setNoMarks(cmr.getNoMarks() + 1);
                        boolean found = false;
                        for(DishMarkReport dmr : cmr.getDishes())
                        {
                            dmr.setMeanMark(dmr.getMeanMark() + order.getMark());
                            dmr.setNoMarks(dmr.getNoMarks() + 1);
                            if(dmr.getId() == item.getDish().getId())
                            {
                                dmr.setCookMark(dmr.getCookMark() + order.getMark());
                                dmr.setNoCookMarks(dmr.getNoCookMarks() + 1);
                                found = true;
                                break;
                            }
                        }
                        if(!found)
                        {
                            DishMarkReport dmr = new DishMarkReport(item.getDish().getId(), item.getDish().getName(), item.getDish().getDescription(), 0.0, 0.0, 0, 0);
                            cmr.getDishes().add(dmr);
                        }
                    }
                }
            }
            cmr.setMeanMark(cmr.getMeanMark() / cmr.getNoMarks());
        }

        return marks;
    }

    @Override
    public List<ReportData> getVisitsReport(Long restaurantId, Date date)
    {
        ArrayList<ReportData> visits = new ArrayList<>();
        Restaurant r = restaurantRepository.findOne(restaurantId);

        Date d = new Date(date.getTime());
        Date end = (Date) DateUtils.addDays(date, 7);
        while (d.before(end))
        {
            List<Reservation> reservations = reservationRepository.getReservationsByRestaurantAndDate(restaurantId, d, DateUtils.addHours(date, 1));
            Date dd = new Date(d.getTime());
            ReportData rd = new ReportData(dd, (double) reservations.size());
            for(Reservation reservation : reservations)
            {
                rd.setValue(rd.getValue() + reservation.getInvitations().size());
            }

            visits.add(rd);
            d = (Date) DateUtils.addHours(d, 1);
        }

        return visits;
    }

    @Override
    public List<ReportData> getWaiterIncomeReport(Long restaurantId, Long waiterId, Date date)
    {
        ArrayList<ReportData> income = new ArrayList<>();
        Date d = new Date(date.getTime());
        Date end = (Date) DateUtils.addDays(date, 7);
        while (d.before(end))
        {
            List<Order> orders = orderRepository.getByRestaurantAndDate(restaurantId, d, DateUtils.addHours(d, 1));
            Date dd = new Date(d.getTime());
            ReportData rd = new ReportData(dd, 0.0);
            for(Order o : orders)
            {
                if(o.getWaiter().getId() == waiterId)
                {
                    rd.setValue(rd.getValue() + o.getPrice());
                }
            }
            income.add(rd);
            d = (Date) DateUtils.addHours(d, 1);
        }
        return income;
    }

    @Override
    public List<ReportData> getIncomeReport(Long restaurantId, Date date)
    {
        ArrayList<ReportData> income = new ArrayList<>();
        Date d = new Date(date.getTime());
        Date end = (Date) DateUtils.addDays(date, 7);
        while (d.before(end))
        {
            List<Order> orders = orderRepository.getByRestaurantAndDate(restaurantId, d, DateUtils.addHours(d, 1));
            Date dd = new Date(d.getTime());
            ReportData rd = new ReportData(dd, 0.0);
            for(Order o : orders)
            {
                rd.setValue(rd.getValue() + o.getPrice());
            }
            income.add(rd);
            d = (Date) DateUtils.addHours(d, 1);
        }
        return income;
    }

    @Override
    public List<DishType> getDishTypes(Long managerId)
    {
        List<DishType> dishTypes = dishTypeRepository.findAll();
        List<DishType> ret = new ArrayList<>();
        RestaurantManager rm = (RestaurantManager) userRepository.findOne(managerId);
        Restaurant r = rm.getRestaurant();
        for (DishType dt : dishTypes)
        {
            if (dt.getRestaurant().getId() == r.getId())
                ret.add(dt);
        }
        return ret;
    }

    @Override
    public List<RestaurantTableDTO> getRestaurantTables(Long waiterId)
    {
        User u = userRepository.findById(waiterId);
        Waiter waiter = (Waiter) u;
        List<RestaurantTableDTO> tables = new ArrayList<RestaurantTableDTO>();
        for(RestaurantTable rt : waiter.getRestaurant().getTables())
        {
            tables.add(new RestaurantTableDTO(rt,false));
        }
        return tables;
    }
}
