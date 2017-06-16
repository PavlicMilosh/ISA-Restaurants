"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var users_service_1 = require("../../services/users.service");
var restaurants_service_1 = require("../../services/restaurants.service");
var AddWorkScheduleComponent = (function () {
    function AddWorkScheduleComponent(userService, restaurantService) {
        var _this = this;
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.days = ["Every Day", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
        this.selectedWorker =
            {
                id: -1000,
                email: "",
                password: "",
                firstName: "",
                lastName: "",
                authorities: ""
            };
        this.restaurantService.getWorkersByRMId().subscribe(function (data) { return _this.workers = data; });
        this.restaurantService.getRegionsByRMId().subscribe(function (data) { return _this.regions = data; });
        this.workers = [];
        this.schedule =
            {
                id: null,
                startTime: null,
                endTime: null,
                day: null,
                regionId: null
            };
    }
    AddWorkScheduleComponent.prototype.ngOnInit = function () {
    };
    AddWorkScheduleComponent.prototype.selectWorker = function (worker) {
        this.selectedWorker = worker;
    };
    AddWorkScheduleComponent.prototype.selectRegion = function (region) {
        this.schedule.regionId = region.id;
    };
    AddWorkScheduleComponent.prototype.addSchedule = function () {
        this.schedule.day = 1;
        console.log(this.schedule);
        this.userService.addSchedule(this.schedule, this.selectedWorker.id)
            .subscribe(function (data) { return console.log(data); });
    };
    return AddWorkScheduleComponent;
}());
AddWorkScheduleComponent = __decorate([
    core_1.Component({
        selector: 'app-add-work-schedule',
        templateUrl: './addWorkSchedule.component.html',
        styleUrls: ['./addWorkSchedule.component.css'],
        providers: [users_service_1.UserService, restaurants_service_1.RestaurantService]
    })
], AddWorkScheduleComponent);
exports.AddWorkScheduleComponent = AddWorkScheduleComponent;
