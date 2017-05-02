import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AddRestaurantComponent } from "./components/addRestaurant/addRestaurant.component";
import { RegisterSMComponent } from "./components/registerSM/registerSM.component";

import { RegisterGuestComponent } from "./components/registerGuest/registerGuest.component";

import { routing } from "./app.routing";
import { UpdateRestaurantComponent } from "./components/updateRestaurant/updateRestaurant.component";
import { UpdateGuestComponent } from "./components/updateGuest/updateGuest.component";
import { UpdateUser } from "./components/updateUser/updateUser.component";
import { NavbarComponent } from './components/navbar/navbar.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RegisterRMComponent } from './components/registerRM/registerRM.component';
import { RegisterEmployeeComponent } from './components/registerEmployee/registerEmployee.component';

@NgModule({
  declarations: [
    AppComponent,
    AddRestaurantComponent,
    RegisterSMComponent,
    RegisterGuestComponent,
    UpdateGuestComponent,
    UpdateRestaurantComponent,
    UpdateUser,
    NavbarComponent,
    RegisterRMComponent,
    RegisterEmployeeComponent
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
