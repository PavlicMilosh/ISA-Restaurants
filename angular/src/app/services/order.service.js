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
var OrderService = (function () {
    function OrderService(http) {
        this.http = http;
    }
    OrderService.prototype.makeOrder = function (orderItems, finished, price, id) {
        var date = Date.now();
        var order = {
            orderItems: orderItems,
            finished: finished,
            price: price,
            orderTime: date
        };
        var param = JSON.stringify(order);
        var waiterId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/order/" + waiterId + "/add/" + id, param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    OrderService.prototype.finishedOrder = function (id) {
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.put("http://localhost:8080/order/" + id + "/finish", { headers: headers });
    };
    OrderService.prototype.getAllOrders = function () {
        var userId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get("http://localhost:8080/users/" + userId + "/getRestaurantOrders", { headers: headers })
            .map(function (res) { return res.json(); });
    };
    OrderService.prototype.preparingOrderItem = function (itemId) {
        return this.http.get("http://localhost:8080/order/" + itemId + "/preparing")
            .map(function (res) { return res.json(); });
    };
    OrderService.prototype.finishedOrderItem = function (itemId) {
        return this.http.get("http://localhost:8080/order/" + itemId + "/finished")
            .map(function (res) { return res.json(); });
    };
    OrderService.prototype.addDishType = function (dishType) {
        var param = JSON.stringify(dishType);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/restaurants/addDishType", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    return OrderService;
}());
OrderService = __decorate([
    core_1.Injectable()
], OrderService);
exports.OrderService = OrderService;
