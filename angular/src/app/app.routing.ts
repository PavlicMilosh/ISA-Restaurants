import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterSMComponent } from './components/registerSM/registerSM.component';
import { RegisterRMComponent } from "./components/registerRM/registerRM.component";
import { RegisterEmployeeComponent } from "./components/registerEmployee/registerEmployee.component";

import { AddRestaurantComponent } from './components/addRestaurant/addRestaurant.component';
import { UpdateRestaurantComponent } from "./components/updateRestaurant/updateRestaurant.component";

import { RegisterGuestComponent} from './components/guest/registerGuest/registerGuest.component';
import { UpdateGuestComponent } from "./components/guest/updateGuest/updateGuest.component";
import { GuestFriendsPageComponent } from "./components/guest/guestFriendsPage/guestFriendsPage.component";
import { GuestRequestPageComponent } from "./components/guest/guestRequestPage/guestRequestPage.component";
import { GuestPeoplePageComponent } from "./components/guest/guestPeoplePage/guestPeoplePage.component";

import { UpdateUser } from './components/updateUser/updateUser.component';
import {ChangePassword} from "./components/changePassword/changePassword.component";
import { AddWorkerComponent } from "./components/addWorker/addWorker.component";
import { AddWorkScheduleComponent } from "./components/addWorkSchedule/addWorkSchedule.component";
import { GuestReservationWizardComponent } from "./components/guest/guestReservationWizard/guestReservationWizard.component";
import { AddProviderComponent } from "./components/addProvider/addProvider.component";
import { AuthenticationComponent } from "./components/authentication/authentication.component";

import { MakeOrder } from './components/makeOrder/makeOrder.component';
import { OrderDishes } from './components/orderDishes/orderDishes.component';
import { OrderDrinks } from './components/orderDrinks/orderDrinks.component';
import  { TableDisplay } from './components/tableDisplay/tableDisplay.component';
import { EmployeeWorkShedule } from "./components/employeeWorkShedule/employeeWorkShedule.component";
import { UpdateProviderComponent } from "./components/updateProvider/updateProvider.component";
import {AddShoppingListComponent} from "./components/shopping/addShoppingList/addShoppingList.component";


const appRoutes : Routes =
  [
    {
      path : '',
      component : AuthenticationComponent
    },
    {
      path: 'auth',
      component: AuthenticationComponent
    },

    {
      path : 'registerSM',
      component : RegisterSMComponent
    },
    {
      path : 'registerRM',
      component : RegisterRMComponent
    },
    {
      path : 'addRestaurant',
      component : AddRestaurantComponent
    },
    {
      path : 'updateRestaurant',
      component : UpdateRestaurantComponent
    },
    {
      path : 'addProvider',
      component : AddProviderComponent
    },
    {
      path : 'addWorker',
      component : AddWorkerComponent
    },
    {
      path : 'addWorkSchedule',
      component : AddWorkScheduleComponent
    },
    {
      path : 'updateProvider',
      component : UpdateProviderComponent
    },

    {
      path : 'registerGuest',
      component : RegisterGuestComponent
    },
    {
      path : 'updateGuest',
      component : UpdateGuestComponent
    },
    {
      path : 'guestFriendsPage',
      component : GuestFriendsPageComponent
    },
    {
      path : 'guestRequestsPage',
      component : GuestRequestPageComponent
    },
    {
      path : 'guestPeoplePage',
      component : GuestPeoplePageComponent
    },
    {
      path : 'guestRestaurantsPage',
      component : GuestReservationWizardComponent
    },


    {
      path : 'updateUser',
      component : UpdateUser
    },
    {
      path: 'changePassword',
      component : ChangePassword
    },
    {
      path: 'makeOrder',
      component : MakeOrder
    },
    {
      path: 'orderDishes',
      component : OrderDishes
    },
    {
      path: 'orderDrinks',
      component : OrderDrinks
    },
    {
      path: 'tableDisplay',
      component: TableDisplay
    },
    {
      path : 'employeeWorkShedule',
      component : EmployeeWorkShedule
    },

    //shopping
    {
      path : 'addShoppingList',
      component: AddShoppingListComponent
    }
  ];

export const routing : ModuleWithProviders = RouterModule.forRoot(appRoutes);
