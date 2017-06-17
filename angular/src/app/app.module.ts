import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ApplicationRef  } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { WizardModule } from 'ng2-archwizard';
import { MyDatePickerModule } from 'mydatepicker';
import { Ng2TableModule } from 'ng2-table/ng2-table';
import { AgmCoreModule } from '@agm/core';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';

import { AddRestaurantComponent } from "./components/addRestaurant/addRestaurant.component";
import { RegisterSMComponent } from "./components/registerSM/registerSM.component";


import { routing } from "./app.routing";

import { AuthenticationComponent } from "./components/authentication/authentication.component";

import { UpdateRestaurantComponent } from "./components/updateRestaurant/updateRestaurant.component";

import { UpdateUser } from "./components/updateUser/updateUser.component";
import { NavbarComponent } from './components/navbar/navbar.component';

import { RegisterRMComponent } from './components/registerRM/registerRM.component';
import { RegisterEmployeeComponent } from './components/registerEmployee/registerEmployee.component';
import { AddWorkerComponent } from './components/addWorker/addWorker.component';
import { ChangePassword } from './components/changePassword/changePassword.component';
import { AddWorkScheduleComponent } from './components/addWorkSchedule/addWorkSchedule.component';
import { AddProviderComponent } from './components/addProvider/addProvider.component';

import { GuestRegisterComponent } from "./components/guest/guestRegister/guestRegister.component";
import { GuestUpdateComponent } from "./components/guest/guestUpdate/guestUpdate.component";
import { GuestFriendsComponent } from './components/guest/guestFriends/guestFriends.component';
import { GuestPeopleComponent } from "./components/guest/guestPeople/guestPeople.component";
import { GuestRequestsComponent } from "./components/guest/guestRequests/guestRequests.component";
import { GuestReservationWizardComponent } from "./components/guest/guestReservationWizard/guestReservationWizard.component";
import { GuestRestaurantsComponent } from "./components/guest/guestRestaurants/guestRestaurants.component";
import { GuestInvitationsComponent} from "./components/guest/guestInvitations/guestInvitations.component";
import { GuestPreorderComponent } from "./components/guest/guestPreorder/guestPreorder.component";
import { GuestTablesComponent } from "./components/guest/guestTables/guestTables.component";
import { GuestReservationSummaryComponent } from "./components/guest/guestReservationSummary/guestReservationSummary.component";
import { GuestReservationUpdateComponent } from "./components/guest/guestReservationUpdate/guestReservationUpdate.component";

import { MakeOrder } from './components/makeOrder/makeOrder.component';
import { OrderDishes } from './components/orderDishes/orderDishes.component';
import { OrderDrinks } from './components/orderDrinks/orderDrinks.component';
import { TableDisplay } from './components/tableDisplay/tableDisplay.component';
import { EmployeeWorkShedule } from "./components/employeeWorkShedule/employeeWorkShedule.component";
import { CalendarComponent } from "ap-angular2-fullcalendar/src/calendar/calendar";
import { UpdateProviderComponent } from './components/updateProvider/updateProvider.component';
import { AddShoppingListComponent } from './components/shopping/addShoppingList/addShoppingList.component';
import {GuestRestaurantAttendancesComponent} from "./components/guest/guestRestaurantAttendances/guestRestaurantAttendances.component";
import {GuestHistoryComponent} from "./components/guest/guestHistory/guestHistory.component";


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AuthenticationComponent,

    // Losmi
    RegisterSMComponent,
    RegisterRMComponent,
    RegisterEmployeeComponent,
    AddWorkerComponent,
    ChangePassword,
    AddRestaurantComponent,
    UpdateRestaurantComponent,
    AddProviderComponent,
    AddShoppingListComponent,
    UpdateProviderComponent,

    // Djura
    UpdateUser,
    ChangePassword,
    MakeOrder,
    OrderDishes,
    OrderDrinks,
    TableDisplay,
    EmployeeWorkShedule,
    CalendarComponent,
    AddWorkScheduleComponent,

    // Ogi
    GuestRegisterComponent,
    GuestUpdateComponent,
    GuestFriendsComponent,
    GuestRequestsComponent,
    GuestPeopleComponent,
    GuestReservationWizardComponent,
    GuestRestaurantsComponent,
    GuestInvitationsComponent,
    GuestPreorderComponent,
    GuestTablesComponent,
    GuestReservationSummaryComponent,
    GuestRestaurantAttendancesComponent,
    GuestReservationUpdateComponent,
    GuestHistoryComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    WizardModule,
    MyDatePickerModule,
    Ng2TableModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDktm4_Qkq5wDW1sX9PY9UKHHGwXmCxnj0'
    }),
    routing,
    NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
