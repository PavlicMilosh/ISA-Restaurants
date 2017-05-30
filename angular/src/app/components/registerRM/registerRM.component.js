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
var RegisterRMComponent = (function () {
    function RegisterRMComponent(restaurantService) {
        var _this = this;
        this.restaurantService = restaurantService;
        this.cbText = "selectRestaurant";
        this.restaurantService.getRestaurants().subscribe(function (data) {
            _this.restaurants = data;
            console.log(_this.restaurants);
        });
    }
    RegisterRMComponent.prototype.selectRestaurant = function (restaurant) {
        this.restaurant = restaurant;
        this.cbText = restaurant.name;
    };
    RegisterRMComponent.prototype.addRM = function () {
        this.restaurantService.addRM(this.restaurant.id, this.email, this.password, this.firstName, this.lastName).subscribe(function (data) { return console.log(data); });
    };
    return RegisterRMComponent;
}());
RegisterRMComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'registerRM',
        templateUrl: './registerRM.component.html',
        styleUrls: ['./registerRM.component.css'],
        providers: [users_service_1.UserService, restaurants_service_1.RestaurantService]
    })
], RegisterRMComponent);
exports.RegisterRMComponent = RegisterRMComponent;
