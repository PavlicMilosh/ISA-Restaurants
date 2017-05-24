"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
require("rxjs/add/operator/map");
var RestaurantService = (function () {
    function RestaurantService(http) {
        this.http = http;
    }
    RestaurantService.prototype.postRestaurant = function (restaurantName, restaurantDescription) {
        var restaurant = {
            name: restaurantName,
            description: restaurantDescription
        };
        var param = JSON.stringify(restaurant);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/restaurants/", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.getByManager = function (managerId) {
        return this.http.get("http://localhost:8080/restaurants/findByManagerId/" + managerId)
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.updateRestaurant = function (restaurant) {
        var param = JSON.stringify(restaurant);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.put("http://localhost:8080/restaurants/" + restaurant.id, param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.getRestaurants = function () {
        return this.http.get("http://localhost:8080/restaurants")
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.addRM = function (restaurantId, email, password, firstName, lastName) {
        var manager = {
            id: null,
            email: email,
            password: password,
            firstName: firstName,
            lastName: lastName
        };
        var param = JSON.stringify(manager);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/restaurants/" + restaurantId + "/addRM", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.getWorkersByRMId = function (managerId) {
        return this.http.get("http://localhost:8080/restaurants/getWorkersByRMId/" + managerId)
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.searchRestaurants = function (searchParams) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.put("http://localhost:8080/restaurants/searchRestaurants", searchParams, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    return RestaurantService;
}());
RestaurantService = __decorate([
    core_1.Injectable()
], RestaurantService);
exports.RestaurantService = RestaurantService;
