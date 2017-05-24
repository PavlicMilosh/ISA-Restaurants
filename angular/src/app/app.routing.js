"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var router_1 = require("@angular/router");
var registerSM_component_1 = require("./components/registerSM/registerSM.component");
var registerRM_component_1 = require("./components/registerRM/registerRM.component");
var registerEmployee_component_1 = require("./components/registerEmployee/registerEmployee.component");
var addRestaurant_component_1 = require("./components/addRestaurant/addRestaurant.component");
var updateRestaurant_component_1 = require("./components/updateRestaurant/updateRestaurant.component");
var registerGuest_component_1 = require("./components/guest/registerGuest/registerGuest.component");
var updateGuest_component_1 = require("./components/guest/updateGuest/updateGuest.component");
var guestFriendsPage_component_1 = require("./components/guest/guestFriendsPage/guestFriendsPage.component");
var guestRequestPage_component_1 = require("./components/guest/guestRequestPage/guestRequestPage.component");
var guestPeoplePage_component_1 = require("./components/guest/guestPeoplePage/guestPeoplePage.component");
var updateUser_component_1 = require("./components/updateUser/updateUser.component");
var changePassword_component_1 = require("./components/changePassword/changePassword.component");
var guestRestaurantsPage_component_1 = require("./components/guest/guestRestaurantsPage/guestRestaurantsPage.component");
var addWorker_component_1 = require("./components/addWorker/addWorker.component");
var addWorkSchedule_component_1 = require("./components/addWorkSchedule/addWorkSchedule.component");
var guestMainPage_component_1 = require("./components/guest/guestMainPage/guestMainPage.component");
var appRoutes = [
    {
        path: '',
        component: registerSM_component_1.RegisterSMComponent
    },
    {
        path: 'registerSM',
        component: registerSM_component_1.RegisterSMComponent
    },
    {
        path: 'registerRM',
        component: registerRM_component_1.RegisterRMComponent
    },
    {
        path: 'registerEmployee',
        component: registerEmployee_component_1.RegisterEmployeeComponent
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
        path: 'addWorker',
        component: addWorker_component_1.AddWorkerComponent
    },
    {
        path: 'addWorkSchedule',
        component: addWorkSchedule_component_1.AddWorkScheduleComponent
    },
    {
        path: 'registerGuest',
        component: registerGuest_component_1.RegisterGuestComponent
    },
    {
        path: 'updateGuest',
        component: updateGuest_component_1.UpdateGuestComponent
    },
    {
        path: 'mainPageGuest',
        component: guestMainPage_component_1.GuestMainPageComponent
    },
    {
        path: 'guestFriendsPage',
        component: guestFriendsPage_component_1.GuestFriendsPageComponent
    },
    {
        path: 'guestRequestsPage',
        component: guestRequestPage_component_1.GuestRequestPageComponent
    },
    {
        path: 'guestPeoplePage',
        component: guestPeoplePage_component_1.GuestPeoplePageComponent
    },
    {
        path: 'guestRestaurantsPage',
        component: guestRestaurantsPage_component_1.GuestRestaurantsPageComponent
    },
    {
        path: 'updateUser',
        component: updateUser_component_1.UpdateUser
    },
    {
        path: 'changePassword',
        component: changePassword_component_1.ChangePassword
    }
];
exports.routing = router_1.RouterModule.forRoot(appRoutes);
