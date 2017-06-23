package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.*;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.repositories.VerificationTokenRepository;
import com.isa.restaurant.repositories.WorkScheduleRepository;
import com.isa.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Milos on 10-Apr-17.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final Integer verificationTokenExpiryTime = 1440;

    private final PasswordEncoder passwordEncoder;

    // USER RELATED (for all user subclasses)

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           VerificationTokenRepository verificationTokenRepository,
                           WorkScheduleRepository workScheduleRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDTO findByEmail(String email)
    {
        return new UserDTO(userRepository.findByEmail(email));
    }


    @Override
    public UserDTO findById(Long id)
    {
        User user = userRepository.findById(id);
        if (user == null) return null;
        return new UserDTO(user);
    }


    // GUEST RELATED

    @Override
    public UserDTO addGuest(Guest guest)
    {
        Guest g = (Guest) userRepository.findByEmail(guest.getEmail());
        if(g != null)
            return null;
        //TODO: OVO KADA SE PONOVO UVEDE MAIL OTKOMENTARISATI
        guest.setEnabled(false);
        User saved = userRepository.save(guest);
        VerificationToken verificationToken = new VerificationToken(guest, this.verificationTokenExpiryTime, VerificationTokenPurpose.REGISTRATION);
        verificationTokenRepository.save(verificationToken);
        return new UserDTO(saved);
    }

    @Override
    public GuestDTO updateGuest(Guest guest)
    {
        Guest g = (Guest) userRepository.findById(guest.getId());
        if(g == null)
            return null;
        g.setFirstName(guest.getFirstName());
        g.setLastName(guest.getLastName());
        g.setEmail(guest.getEmail());
        g.setPassword(guest.getPassword());
        Guest saved = (Guest)userRepository.save(g);
        return new GuestDTO(saved);
    }

    @Override
    public UserDTO addSystemManager(SystemManager systemManager)
    {
        systemManager.setPassword(passwordEncoder.encode(systemManager.getPassword()));
        SystemManager sm = (SystemManager) userRepository.findByEmail(systemManager.getEmail());
        if(sm != null)
            return null;
        User saved = userRepository.save(systemManager);
        return new UserDTO(saved);
    }

    @Override
    public UserDTO addProvider(Provider provider)
    {
        provider.setPassword(passwordEncoder.encode(provider.getPassword()));
        Provider p = (Provider) userRepository.findByEmail(provider.getEmail());
        if(p != null)
            return null;
        User saved = userRepository.save(provider);
        return new UserDTO(saved);
    }
    @Override
    public UserDTO changeWaiter(Waiter waiter)
    {
        Waiter w = (Waiter) userRepository.findByEmail(waiter.getEmail());
        if (w != null)
            return null;
        User saved = userRepository.save(w);
        return new UserDTO(saved);
    }

    @Override
    public UserDTO changeCook(Cook cook)
    {
        Cook ck = (Cook) userRepository.findById(cook.getId());
        if(ck == null)
            return null;
        User saved = userRepository.save(cook);
        return new UserDTO(saved);
    }

    @Override
    public UserDTO changeBartender(Bartender bartender)
    {
        Bartender br = (Bartender) userRepository.findById(bartender.getId());
        if(br == null)
            return null;
        User saved = userRepository.save(bartender);
        return new UserDTO(saved);
    }

    @Override
    public UserDTO changePassword(UserDTO userDTO)
    {
        User user = userRepository.findById(userDTO.getId());
        user.setPassword(userDTO.getPassword());
        User saved = userRepository.save(user);
        return  new UserDTO(saved);
    }

    @Override
    public Set<WorkScheduleDTO> getSchedule(Long id)
    {
        User u = userRepository.findById(id);
        Set<WorkSchedule> ws = null;
        if(u instanceof Bartender)
        {
            ws = ((Bartender) u).getSchedule();
        }
        else if(u instanceof Cook)
        {
            ws = ((Cook) u).getSchedule();
        }
        else if(u instanceof Waiter)
        {
            ws = ((Waiter) u).getSchedule();
        }
        Set<WorkScheduleDTO> workerSchedule=new HashSet<WorkScheduleDTO>();
        for(WorkSchedule schedule:ws)
        {
            workerSchedule.add(new WorkScheduleDTO(schedule.getStartTime().toString(),schedule.getEndTime().toString(),schedule.getDay()));
        }
        return workerSchedule;
    }

    @Override
    public RestaurantDTO getUserRestaurant(Long id)
    {
        Waiter w=(Waiter) userRepository.findById(id);
        if(w == null)
            return null;
        Restaurant r=w.getRestaurant();
        RestaurantDTO restaurantDTO=new RestaurantDTO(r);
        return restaurantDTO;
    }

    @Override
    public Set<OrderItemDTO> getRestaurantOrders(Long id)
    {
        User u = userRepository.findById(id);
        Restaurant r = null;
        if(u instanceof Bartender)
        {
            r = ((Bartender) u).getRestaurant();
        }
        else if(u instanceof Cook)
        {
            r = ((Cook) u).getRestaurant();
        }
        else if(u instanceof Waiter)
        {
            r = ((Waiter) u).getRestaurant();
        }
        //RestaurantOrders ro=restaurantOrdersRepository.findByRestaurantId(r.getId());
        Date dateBefore = new Date(System.currentTimeMillis() - 1200 * 1000);
        Date dateAfter = new Date(System.currentTimeMillis() + 2400 * 1000);
        Set<OrderItemDTO> orders=new HashSet<OrderItemDTO>();
        System.out.println(r.getOrders().size());
        for(Order o :r.getOrders())
        {
            if(!o.getFinished())
            {

                if(o.getOrderTime().after(dateBefore)&&o.getOrderTime().before(dateAfter))
                {
                    Set<OrderItmDTO> orderItems = new HashSet<>();
                    for (OrderItem oi : o.getOrderItems()) {
                        if (oi.getFinished() != true && oi.getPreparing() != true) {
                            orderItems.add(new OrderItmDTO(oi));
                        }
                    }
                    OrderItemDTO temp = new OrderItemDTO(o);
                    temp.setOrderItems(orderItems);
                    orders.add(temp);
                }
            }
        }
        return orders;
    }

    @Override
    public UserDTO updateProvider(Long providerId, ProviderDTO provider)
    {
        Provider p = (Provider)userRepository.findOne(providerId);
        if(p == null)
            return null;
        if(!p.getPassword().equals(provider.getPassword()))
        {
            p.setEmail(provider.getEmail());
            p.setFirstName(provider.getFirstName());
            p.setLastName(provider.getLastName());
            p.setPassword(passwordEncoder.encode(provider.getPassword()));
        }
        return new UserDTO(userRepository.save(p));
    }

    @Override
    public UpdatingUser findForUpdate(Long userId)
    {
        User u = userRepository.findOne(userId);
        return new UpdatingUser(u);
    }

    @Override
    public Long getWaiterRegionId(Long userId)
    {
        User u = userRepository.findById(userId);
        Waiter waiter = (Waiter) u;
        Set<WorkSchedule> schedule=waiter.getSchedule();
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeek=dayOfWeek-2;
        if(dayOfWeek==-1) dayOfWeek=6;
        Long regionId = -1l;
        for(WorkSchedule ws : schedule)
        {
            if(ws.getDay().ordinal()==dayOfWeek)
            {
                regionId=ws.getRegion().getId();
                break;
            }
        }
        return regionId;
    }

    @Override
    public Set<WorkScheduleDTO> getAllSchedule(Long id)
    {
        User u = userRepository.findById(id);
        Integer role=0;
        Restaurant r=null;
        Set<WorkSchedule> schedule=new HashSet<WorkSchedule>();
        if(u instanceof Bartender)
        {
            r = ((Bartender) u).getRestaurant();
            for(Bartender b:r.getBartenders())
            {
                schedule.addAll(b.getSchedule());
            }
        }
        else if(u instanceof Cook)
        {
            r = ((Cook) u).getRestaurant();
            for(Cook c:r.getCooks())
            {
                schedule.addAll(c.getSchedule());
            }
        }
        else if(u instanceof Waiter)
        {
            r = ((Waiter) u).getRestaurant();
            for(Waiter w:r.getWaiters())
            {
                schedule.addAll(w.getSchedule());
            }
        }
        Set<WorkScheduleDTO> workerSchedule=new HashSet<WorkScheduleDTO>();
        for(WorkSchedule s:schedule)
        {
            if(s.getWorker().getId()!=id)
            {
                WorkScheduleDTO tempSchedule=new WorkScheduleDTO(s.getStartTime().toString(),s.getEndTime().toString(),s.getDay());
                Bartender tempUser=new Bartender();
                tempUser.setFirstName(s.getWorker().getFirstName());
                tempUser.setLastName(s.getWorker().getLastName());
                tempSchedule.setWorker(tempUser);
                workerSchedule.add(tempSchedule);
            }

        }
        return workerSchedule;
    }

    @Override
    public GuestDTO getGuestInfo(Long guestId)
    {
        Guest guest = (Guest) userRepository.findById(guestId);
        return new GuestDTO(guest);
    }
}
