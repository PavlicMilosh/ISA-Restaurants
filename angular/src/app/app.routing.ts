import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterSMComponent } from './components/registerSM/registerSM.component';
import { RegisterRMComponent } from "./components/registerRM/registerRM.component";
import { RegisterEmployeeComponent } from "./components/registerEmployee/registerEmployee.component";

import { AddRestaurantComponent } from './components/addRestaurant/addRestaurant.component';
import { UpdateRestaurantComponent } from "./components/updateRestaurant/updateRestaurant.component";

import { RegisterGuestComponent} from './components/registerGuest/registerGuest.component';
import { UpdateGuestComponent } from "./components/guest/updateGuest/updateGuest.component";
import { GuestFriendsPageComponent } from "./components/guest/guestFriendsPage/guestFriendsPage.component";
import { GuestRequestPageComponent } from "./components/guest/guestRequestPage/guestRequestPage.component";
import { GuestPeoplePageComponent } from "./components/guest/guestPeoplePage/guestPeoplePage.component";

import { UpdateUser } from './components/updateUser/updateUser.component';
import {ChangePassword} from "./components/changePassword/changePassword.component";
import {GuestRestaurantsPageComponent} from "./components/guest/guestRestaurantsPage/guestRestaurantsPage.component";
import { AddWorkerComponent } from "./components/addWorker/addWorker.component";
import { AddWorkScheduleComponent } from "./components/addWorkSchedule/addWorkSchedule.component";

import { MakeOrder } from './components/makeOrder/makeOrder.component';


const appRoutes : Routes =
  [
    {
      path : '',
      component : RegisterSMComponent
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
      path : 'registerEmployee',
      component : RegisterEmployeeComponent
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
      path : 'addWorker',
      component : AddWorkerComponent
    },
    {
      path : 'addWorkSchedule',
      component : AddWorkScheduleComponent
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
      component : GuestRestaurantsPageComponent
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
  ];

export const routing : ModuleWithProviders = RouterModule.forRoot(appRoutes);
