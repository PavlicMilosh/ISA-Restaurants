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
var ng2_archwizard_1 = require("ng2-archwizard");
var mydatepicker_1 = require("mydatepicker");
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var app_component_1 = require("./app.component");
var addRestaurant_component_1 = require("./components/addRestaurant/addRestaurant.component");
var registerSM_component_1 = require("./components/registerSM/registerSM.component");
var app_routing_1 = require("./app.routing");
var authentication_component_1 = require("./components/authentication/authentication.component");
var updateRestaurant_component_1 = require("./components/updateRestaurant/updateRestaurant.component");
var updateUser_component_1 = require("./components/updateUser/updateUser.component");
var navbar_component_1 = require("./components/navbar/navbar.component");
var registerRM_component_1 = require("./components/registerRM/registerRM.component");
var registerEmployee_component_1 = require("./components/registerEmployee/registerEmployee.component");
var addWorker_component_1 = require("./components/addWorker/addWorker.component");
var changePassword_component_1 = require("./components/changePassword/changePassword.component");
var addWorkSchedule_component_1 = require("./components/addWorkSchedule/addWorkSchedule.component");
var addProvider_component_1 = require("./components/addProvider/addProvider.component");
var guestRegister_component_1 = require("./components/guest/guestRegister/guestRegister.component");
var guestUpdate_component_1 = require("./components/guest/guestUpdate/guestUpdate.component");
var guestFriends_component_1 = require("./components/guest/guestFriends/guestFriends.component");
var guestPeople_component_1 = require("./components/guest/guestPeople/guestPeople.component");
var guestRequests_component_1 = require("./components/guest/guestRequests/guestRequests.component");
var guestReservationWizard_component_1 = require("./components/guest/guestReservationWizard/guestReservationWizard.component");
var guestRestaurants_component_1 = require("./components/guest/guestRestaurants/guestRestaurants.component");
var guestInvitations_component_1 = require("./components/guest/guestInvitations/guestInvitations.component");
var guestPreorder_component_1 = require("./components/guest/guestPreorder/guestPreorder.component");
var guestTables_component_1 = require("./components/guest/guestTables/guestTables.component");
var guestReservationSummary_component_1 = require("./components/guest/guestReservationSummary/guestReservationSummary.component");
var makeOrder_component_1 = require("./components/makeOrder/makeOrder.component");
var orderDishes_component_1 = require("./components/orderDishes/orderDishes.component");
var orderDrinks_component_1 = require("./components/orderDrinks/orderDrinks.component");
var tableDisplay_component_1 = require("./components/tableDisplay/tableDisplay.component");
var employeeWorkShedule_component_1 = require("./components/employeeWorkShedule/employeeWorkShedule.component");
var calendar_1 = require("ap-angular2-fullcalendar/src/calendar/calendar");
var updateProvider_component_1 = require("./components/updateProvider/updateProvider.component");
var addShoppingList_component_1 = require("./components/shopping/addShoppingList/addShoppingList.component");
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
            authentication_component_1.AuthenticationComponent,
            // Losmi
            registerSM_component_1.RegisterSMComponent,
            registerRM_component_1.RegisterRMComponent,
            registerEmployee_component_1.RegisterEmployeeComponent,
            addWorker_component_1.AddWorkerComponent,
            changePassword_component_1.ChangePassword,
            addRestaurant_component_1.AddRestaurantComponent,
            updateRestaurant_component_1.UpdateRestaurantComponent,
            addProvider_component_1.AddProviderComponent,
            addShoppingList_component_1.AddShoppingListComponent,
            updateProvider_component_1.UpdateProviderComponent,
            // Djura
            updateUser_component_1.UpdateUser,
            changePassword_component_1.ChangePassword,
            makeOrder_component_1.MakeOrder,
            orderDishes_component_1.OrderDishes,
            orderDrinks_component_1.OrderDrinks,
            tableDisplay_component_1.TableDisplay,
            employeeWorkShedule_component_1.EmployeeWorkShedule,
            calendar_1.CalendarComponent,
            addWorkSchedule_component_1.AddWorkScheduleComponent,
            // Ogi
            guestRegister_component_1.GuestRegisterComponent,
            guestUpdate_component_1.GuestUpdateComponent,
            guestFriends_component_1.GuestFriendsComponent,
            guestRequests_component_1.GuestRequestsComponent,
            guestPeople_component_1.GuestPeopleComponent,
            guestReservationWizard_component_1.GuestReservationWizardComponent,
            guestRestaurants_component_1.GuestRestaurantsComponent,
            guestInvitations_component_1.GuestInvitationsComponent,
            guestPreorder_component_1.GuestPreorderComponent,
            guestTables_component_1.GuestTablesComponent,
            guestReservationSummary_component_1.GuestReservationSummaryComponent
        ],
        imports: [
            platform_browser_1.BrowserModule,
            forms_1.FormsModule,
            http_1.HttpModule,
            ng2_archwizard_1.WizardModule,
            mydatepicker_1.MyDatePickerModule,
            app_routing_1.routing,
            ng_bootstrap_1.NgbModule.forRoot()
        ],
        providers: [],
        bootstrap: [app_component_1.AppComponent]
    })
], AppModule);
exports.AppModule = AppModule;
