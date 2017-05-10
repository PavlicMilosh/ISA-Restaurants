import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterSMComponent } from './components/registerSM/registerSM.component';
import { RegisterRMComponent } from "./components/registerRM/registerRM.component";
import { RegisterEmployeeComponent } from "./components/registerEmployee/registerEmployee.component";

import { AddRestaurantComponent } from './components/addRestaurant/addRestaurant.component';
import { UpdateRestaurantComponent } from "./components/updateRestaurant/updateRestaurant.component";

import { RegisterGuestComponent} from './components/registerGuest/registerGuest.component';
import { UpdateGuestComponent } from "./components/updateGuest/updateGuest.component";

import { UpdateUser } from './components/updateUser/updateUser.component';
import { AddWorkerComponent } from "./components/addWorker/addWorker.component";

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
      path : 'registerGuest',
      component : RegisterGuestComponent
    },
    {
      path : 'addWorker',
      component : AddWorkerComponent
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
