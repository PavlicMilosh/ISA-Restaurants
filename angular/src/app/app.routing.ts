import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterSMComponent } from './components/registerSM/registerSM.component';
import { AddRestaurantComponent } from './components/addRestaurant/addRestaurant.component';
import { RegisterGuestComponent} from './components/registerGuest/registerGuest.component';
import {UpdateGuestComponent} from "./components/updateGuest/updateGuest.component";
import { UpdateUser } from './components/updateUser/updateUser.component';

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
      path : 'addRestaurant',
      component : AddRestaurantComponent
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
      path : 'updateUser',
      component : UpdateUser
    },
  ];

export const routing : ModuleWithProviders = RouterModule.forRoot(appRoutes);
