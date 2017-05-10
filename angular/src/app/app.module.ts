import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';

import { AddRestaurantComponent } from "./components/addRestaurant/addRestaurant.component";
import { RegisterSMComponent } from "./components/registerSM/registerSM.component";


import { routing } from "./app.routing";
import { UpdateRestaurantComponent } from "./components/updateRestaurant/updateRestaurant.component";

import { UpdateUser } from "./components/updateUser/updateUser.component";
import { NavbarComponent } from './components/navbar/navbar.component';

import { RegisterRMComponent } from './components/registerRM/registerRM.component';
import { RegisterEmployeeComponent } from './components/registerEmployee/registerEmployee.component';
import { ChangePassword } from './components/changePassword/changePassword.component';

import { RegisterGuestComponent } from "./components/registerGuest/registerGuest.component";
import { UpdateGuestComponent } from "./components/updateGuest/updateGuest.component";
import { GuestPageComponent } from './components/guestMainPage/guest-page.component';
import { GuestFriendsPageComponent } from './components/guestFriendsPage/guestFriendsPage.component';
import { GuestRestaurantsPageComponent } from './components/guestRestaurantsPage/guest-restaurants-page.component';
import { GuestRequestPageComponent } from "./components/guestRequestPage/guestRequestPage.component";
import { GuestPeoplePageComponent } from "./components/guestPeoplePage/guestPeoplePage.component";


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,

    // Losmi
    RegisterSMComponent,
    RegisterRMComponent,
    RegisterEmployeeComponent,
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
