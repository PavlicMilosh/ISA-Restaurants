"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var router_1 = require("@angular/router");
var registerSM_component_1 = require("./components/registerSM/registerSM.component");
var registerRM_component_1 = require("./components/registerRM/registerRM.component");
var addRestaurant_component_1 = require("./components/addRestaurant/addRestaurant.component");
var updateRestaurant_component_1 = require("./components/updateRestaurant/updateRestaurant.component");
var guestRegister_component_1 = require("./components/guest/guestRegister/guestRegister.component");
var guestUpdate_component_1 = require("./components/guest/guestUpdate/guestUpdate.component");
var guestFriends_component_1 = require("./components/guest/guestFriends/guestFriends.component");
var guestRequests_component_1 = require("./components/guest/guestRequests/guestRequests.component");
var guestPeople_component_1 = require("./components/guest/guestPeople/guestPeople.component");
var guestReservationWizard_component_1 = require("./components/guest/guestReservationWizard/guestReservationWizard.component");
var updateUser_component_1 = require("./components/updateUser/updateUser.component");
var changePassword_component_1 = require("./components/changePassword/changePassword.component");
var addWorker_component_1 = require("./components/addWorker/addWorker.component");
var addWorkSchedule_component_1 = require("./components/addWorkSchedule/addWorkSchedule.component");
var addProvider_component_1 = require("./components/addProvider/addProvider.component");
var authentication_component_1 = require("./components/authentication/authentication.component");
var makeOrder_component_1 = require("./components/makeOrder/makeOrder.component");
var orderDishes_component_1 = require("./components/orderDishes/orderDishes.component");
var orderDrinks_component_1 = require("./components/orderDrinks/orderDrinks.component");
var tableDisplay_component_1 = require("./components/tableDisplay/tableDisplay.component");
var employeeWorkShedule_component_1 = require("./components/employeeWorkShedule/employeeWorkShedule.component");
var updateProvider_component_1 = require("./components/updateProvider/updateProvider.component");
var addShoppingList_component_1 = require("./components/shopping/addShoppingList/addShoppingList.component");
var appRoutes = [
    {
        path: '',
        component: authentication_component_1.AuthenticationComponent
    },
    {
        path: 'auth',
        component: authentication_component_1.AuthenticationComponent
    },
    // RESTAURANTS PART
    {
        path: 'registerSM',
        component: registerSM_component_1.RegisterSMComponent
    },
    {
        path: 'registerRM',
        component: registerRM_component_1.RegisterRMComponent
    },
    {
        path: 'addRestaurant',
        component: addRestaurant_component_1.AddRestaurantComponent
    },
    {
        path: 'updateRestaurant',
        component: updateRestaurant_component_1.UpdateRestaurantComponent
    },
    {
        path: 'addProvider',
        component: addProvider_component_1.AddProviderComponent
    },
    {
        path: 'addWorker',
        component: addWorker_component_1.AddWorkerComponent
    },
    {
        path: 'addWorkSchedule',
        component: addWorkSchedule_component_1.AddWorkScheduleComponent
    },
    {
        path: 'updateProvider',
        component: updateProvider_component_1.UpdateProviderComponent
    },
    // GUEST PART
    {
        path: 'guest/register',
        component: guestRegister_component_1.GuestRegisterComponent
    },
    {
        path: 'guest/update',
        component: guestUpdate_component_1.GuestUpdateComponent
    },
    {
        path: 'guest/friends',
        component: guestFriends_component_1.GuestFriendsComponent
    },
    {
        path: 'guest/requests',
        component: guestRequests_component_1.GuestRequestsComponent
    },
    {
        path: 'guest/people',
        component: guestPeople_component_1.GuestPeopleComponent
    },
    {
        path: 'guest/reservations',
        component: guestReservationWizard_component_1.GuestReservationWizardComponent
    },
    {
        path: 'updateUser',
        component: updateUser_component_1.UpdateUser
    },
    {
        path: 'changePassword',
        component: changePassword_component_1.ChangePassword
    },
    {
        path: 'makeOrder',
        component: makeOrder_component_1.MakeOrder
    },
    {
        path: 'orderDishes',
        component: orderDishes_component_1.OrderDishes
    },
    {
        path: 'orderDrinks',
        component: orderDrinks_component_1.OrderDrinks
    },
    {
        path: 'tableDisplay',
        component: tableDisplay_component_1.TableDisplay
    },
    {
        path: 'employeeWorkShedule',
        component: employeeWorkShedule_component_1.EmployeeWorkShedule
    },
    //shopping
    {
        path: 'addShoppingList',
        component: addShoppingList_component_1.AddShoppingListComponent
    }
];
exports.routing = router_1.RouterModule.forRoot(appRoutes);
