"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var restaurants_service_1 = require("../../services/restaurants.service");
var AddRestaurantComponent = (function () {
    function AddRestaurantComponent(restaurantService) {
        this.restaurantService = restaurantService;
    }
    AddRestaurantComponent.prototype.ngOnInit = function () {
    };
    AddRestaurantComponent.prototype.addRestaurant = function () {
        var _this = this;
        this.restaurantService.postRestaurant(this.restaurantName, this.restaurantDescription).subscribe(function (data) { return _this.restaurant = data; }, function (error) { return alert(error); });
    };
    return AddRestaurantComponent;
}());
AddRestaurantComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'addRestaurant',
        templateUrl: './addRestaurant.component.html',
        styleUrls: ['./addRestaurant.component.css'],
        providers: [restaurants_service_1.RestaurantService]
    })
], AddRestaurantComponent);
exports.AddRestaurantComponent = AddRestaurantComponent;
