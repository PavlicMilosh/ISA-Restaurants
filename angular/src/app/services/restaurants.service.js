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
var logged_utils_1 = require("../utils/logged.utils");
var address_utils_1 = require("../utils/address.utils");
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
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/restaurants/", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.getByManager = function () {
        var managerId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get("http://localhost:8080/restaurants/findByManagerId/" + managerId, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.updateRestaurant = function (restaurant) {
        console.log(restaurant);
        var param = JSON.stringify(restaurant);
        console.log(param);
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.put("http://localhost:8080/restaurants/" + restaurant.id, param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.getRestaurants = function () {
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get("http://localhost:8080/restaurants", { headers: headers })
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
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/restaurants/" + restaurantId + "/addRM", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.getWorkersByRMId = function () {
        var managerId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get("http://localhost:8080/restaurants/getWorkersByRMId/" + managerId, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.searchRestaurants = function (searchParams) {
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.put("http://localhost:8080/restaurants/searchRestaurants", searchParams, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.getRegions = function (restaurantId) {
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get("http://localhost:8080/restaurants/" + restaurantId + "/getRegions", { headers: headers })
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.getRegionsByRMId = function () {
        var userId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get("http://localhost:8080/restaurants/RM/" + userId + "/getRegions", { headers: headers })
            .map(function (res) { return res.json(); });
    };
    RestaurantService.prototype.getTables = function (restaurantId) {
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get(address_utils_1.AddressUtils.backendAddress() + "/restaurants/" + restaurantId + "/getTables", { headers: headers })
            .map(function (res) { return res.json(); });
    };
    return RestaurantService;
}());
RestaurantService = __decorate([
    core_1.Injectable()
], RestaurantService);
exports.RestaurantService = RestaurantService;
