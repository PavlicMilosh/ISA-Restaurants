"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var platform_browser_1 = require("@angular/platform-browser");
var core_1 = require("@angular/core");
var forms_1 = require("@angular/forms");
var http_1 = require("@angular/http");
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var app_component_1 = require("./app.component");
var addRestaurant_component_1 = require("./components/addRestaurant/addRestaurant.component");
var registerSM_component_1 = require("./components/registerSM/registerSM.component");
var app_routing_1 = require("./app.routing");
var updateRestaurant_component_1 = require("./components/updateRestaurant/updateRestaurant.component");
var updateUser_component_1 = require("./components/updateUser/updateUser.component");
var navbar_component_1 = require("./components/navbar/navbar.component");
var registerRM_component_1 = require("./components/registerRM/registerRM.component");
var registerEmployee_component_1 = require("./components/registerEmployee/registerEmployee.component");
var addWorker_component_1 = require("./components/addWorker/addWorker.component");
var changePassword_component_1 = require("./components/changePassword/changePassword.component");
var addWorkSchedule_component_1 = require("./components/addWorkSchedule/addWorkSchedule.component");
var registerGuest_component_1 = require("./components/guest/registerGuest/registerGuest.component");
var updateGuest_component_1 = require("./components/guest/updateGuest/updateGuest.component");
var guestFriendsPage_component_1 = require("./components/guest/guestFriendsPage/guestFriendsPage.component");
var guestRestaurantsPage_component_1 = require("./components/guest/guestRestaurantsPage/guestRestaurantsPage.component");
var guestRequestPage_component_1 = require("./components/guest/guestRequestPage/guestRequestPage.component");
var guestPeoplePage_component_1 = require("./components/guest/guestPeoplePage/guestPeoplePage.component");
var guestMainPage_component_1 = require("./components/guest/guestMainPage/guestMainPage.component");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    core_1.NgModule({
        declarations: [
            app_component_1.AppComponent,
            navbar_component_1.NavbarComponent,
            // Losmi
            registerSM_component_1.RegisterSMComponent,
            registerRM_component_1.RegisterRMComponent,
            registerEmployee_component_1.RegisterEmployeeComponent,
            addWorker_component_1.AddWorkerComponent,
            changePassword_component_1.ChangePassword,
            addRestaurant_component_1.AddRestaurantComponent,
            updateRestaurant_component_1.UpdateRestaurantComponent,
            // Djura
            updateUser_component_1.UpdateUser,
            changePassword_component_1.ChangePassword,
            //Ogi
            registerGuest_component_1.RegisterGuestComponent,
            updateGuest_component_1.UpdateGuestComponent,
            guestFriendsPage_component_1.GuestFriendsPageComponent,
            guestMainPage_component_1.GuestMainPageComponent,
            guestRequestPage_component_1.GuestRequestPageComponent,
            guestRestaurantsPage_component_1.GuestRestaurantsPageComponent,
            guestPeoplePage_component_1.GuestPeoplePageComponent,
            addWorkSchedule_component_1.AddWorkScheduleComponent
        ],
        imports: [
            platform_browser_1.BrowserModule,
            forms_1.FormsModule,
            http_1.HttpModule,
            app_routing_1.routing,
            ng_bootstrap_1.NgbModule.forRoot()
        ],
        providers: [],
        bootstrap: [app_component_1.AppComponent]
    })
], AppModule);
exports.AppModule = AppModule;
