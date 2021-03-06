import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterSMComponent } from './components/registerSM/registerSM.component';
import { RegisterRMComponent } from "./components/registerRM/registerRM.component";

import { AddRestaurantComponent } from './components/addRestaurant/addRestaurant.component';
import { UpdateRestaurantComponent } from "./components/updateRestaurant/updateRestaurant.component";

import { GuestRegisterComponent} from './components/guest/guestRegister/guestRegister.component';
import { GuestUpdateComponent } from "./components/guest/guestUpdate/guestUpdate.component";
import { GuestFriendsComponent } from "./components/guest/guestFriends/guestFriends.component";
import { GuestRequestsComponent } from "./components/guest/guestRequests/guestRequests.component";
import { GuestPeopleComponent } from "./components/guest/guestPeople/guestPeople.component";
import { GuestReservationWizardComponent } from "./components/guest/guestReservationWizard/guestReservationWizard.component";
import {GuestRestaurantAttendancesComponent} from "./components/guest/guestRestaurantAttendances/guestRestaurantAttendances.component";

import { UpdateUser } from './components/updateUser/updateUser.component';
import { ChangePassword } from "./components/changePassword/changePassword.component";
import { AddWorkerComponent } from "./components/addWorker/addWorker.component";
import { AddWorkScheduleComponent } from "./components/addWorkSchedule/addWorkSchedule.component";
import { AddProviderComponent } from "./components/addProvider/addProvider.component";
import { AuthenticationComponent } from "./components/authentication/authentication.component";

import { MakeOrder } from './components/makeOrder/makeOrder.component';
import { OrderDishes } from './components/orderDishes/orderDishes.component';
import { OrderDrinks } from './components/orderDrinks/orderDrinks.component';
import { TableDisplay } from './components/tableDisplay/tableDisplay.component';
import { EmployeeWorkShedule } from "./components/employeeWorkShedule/employeeWorkShedule.component";
import { UpdateProviderComponent } from "./components/updateProvider/updateProvider.component";
import {AddShoppingListComponent} from "./components/shopping/addShoppingList/addShoppingList.component";
import {ShoppingListProviderComponent} from "./components/shopping/shoppingListProvider/shoppingListProvider.component";
import {ShoppingListsRMComponent} from "./components/shopping/shoppingListsRM/shoppingListsRM.component";
import {GuestHistoryComponent} from "./components/guest/guestHistory/guestHistory.component";
import { CreateBill } from "./components/createBill/createBill.component";
import {RestaurantReportComponent} from "./components/restaurantReport/restaurantReport.component";
import { OrderChange } from "./components/orderChange/orderChange.component";
import {Guard} from "./utils/Guard";

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


    // RESTAURANTS PART
    {
      path : 'registerSM',
      component : RegisterSMComponent,
      canActivate: [
        Guard
      ]
    },
    {
      path : 'registerRM',
      component : RegisterRMComponent,
      canActivate: [
        Guard
      ]
    },
    {
      path : 'addRestaurant',
      component : AddRestaurantComponent,
      canActivate: [
        Guard
      ]
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
      path : 'report',
      component : RestaurantReportComponent
    },


    // GUEST PART
    {
      path : 'guest/register',
      component : GuestRegisterComponent,
      canActivate: [
        Guard
      ]
    },
    {
      path : 'guest/update',
      component : GuestUpdateComponent,
      canActivate: [
        Guard
      ]
    },
    {
      path : 'guest/friends',
      component : GuestFriendsComponent,
      canActivate: [
        Guard
      ]
    },
    {
      path : 'guest/requests',
      component : GuestRequestsComponent,
      canActivate: [
        'Guard',
        Guard
      ]
    },
    {
      path : 'guest/people',
      component : GuestPeopleComponent,
      canActivate: [
        Guard
      ]
    },
    {
      path : 'guest/reservations',
      component : GuestReservationWizardComponent,
      canActivate: [
        Guard
      ]
    },
    {
      path: 'guest/attendances',
      component: GuestRestaurantAttendancesComponent,
      canActivate: [
        Guard
      ]
    },
    {
      path: 'guest/visits',
      component: GuestHistoryComponent,
      canActivate: [
        Guard
      ]
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
      path: 'makeOrder/:p1',
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
    {
      path : 'createBill/:p1',
      component : CreateBill
    },
    {
      path : 'orderChange/:p1',
      component : OrderChange
    },

    //shopping
    {
      path : 'addShoppingList',
      component : AddShoppingListComponent
    },
    {
      path : 'shoppingListsProvider',
      component : ShoppingListProviderComponent
    },
    {
      path : 'shoppingListRM',
      component : ShoppingListsRMComponent
    }
  ];

export const routing : ModuleWithProviders = RouterModule.forRoot(appRoutes);
