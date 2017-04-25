import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterSMComponent } from './components/registerSM/registerSM.component';
import { AddRestaurantComponent } from './components/addRestaurant/addRestaurant.component';

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
    }
  ];

export const routing : ModuleWithProviders = RouterModule.forRoot(appRoutes);
