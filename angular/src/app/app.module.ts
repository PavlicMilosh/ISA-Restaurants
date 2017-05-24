import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

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

import { RegisterGuestComponent } from "./components/registerGuest/registerGuest.component";
import { UpdateGuestComponent } from "./components/guest/updateGuest/updateGuest.component";
import { GuestPageComponent } from './components/guest/guestMainPage/guest-page.component';
import { GuestFriendsPageComponent } from './components/guest/guestFriendsPage/guestFriendsPage.component';
import { GuestRestaurantsPageComponent } from './components/guest/guestRestaurantsPage/guestRestaurantsPage.component';
import { GuestRequestPageComponent } from "./components/guest/guestRequestPage/guestRequestPage.component";
import { GuestPeoplePageComponent } from "./components/guest/guestPeoplePage/guestPeoplePage.component";
import { AddWorkScheduleComponent } from './components/addWorkSchedule/addWorkSchedule.component';
import { AddProviderComponent } from './components/addProvider/addProvider.component';


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

    // Djura
    UpdateUser,
    ChangePassword,

    //Ogi
    RegisterGuestComponent,
    UpdateGuestComponent,
    GuestPageComponent,
    GuestFriendsPageComponent,
    GuestRequestPageComponent,
    GuestRestaurantsPageComponent,
    GuestPeoplePageComponent,
    AddWorkScheduleComponent,
    AddProviderComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
