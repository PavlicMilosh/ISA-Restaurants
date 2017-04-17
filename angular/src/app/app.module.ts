import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { RegisterSMComponent } from "./components/registerSM/registerSM.component";
import { AddRestaurantComponent } from "./components/addRestaurant/addRestaurant.component";
import { routing } from "./app.routing";

@NgModule({
  declarations: [
    AppComponent,
    AddRestaurantComponent,
    RegisterSMComponent
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
