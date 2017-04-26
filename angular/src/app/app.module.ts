import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AddRestaurantComponent } from "./components/addRestaurant/addRestaurant.component";
import { RegisterSMComponent } from "./components/registerSM/registerSM.component";
import { routing } from "./app.routing";
import { UpdateRestaurantComponent } from "./components/updateRestaurant/updateRestaurant.component";


@NgModule({
  declarations: [
    AppComponent,
    AddRestaurantComponent,
    RegisterSMComponent,
    UpdateRestaurantComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }