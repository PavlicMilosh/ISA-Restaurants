"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var http_1 = require("@angular/http");
var core_1 = require("@angular/core");
var logged_utils_1 = require("../utils/logged.utils");
var ShoppingService = (function () {
    function ShoppingService(http) {
        this.http = http;
    }
    ShoppingService.prototype.addList = function (shoppingList) {
        var param = JSON.stringify(shoppingList);
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        var userId = logged_utils_1.LoggedUtils.getId();
        return this.http.post("http://localhost:8080/shopping/" + userId + "/addShoppingList", param, { headers: headers });
    };
    return ShoppingService;
}());
ShoppingService = __decorate([
    core_1.Injectable()
], ShoppingService);
exports.ShoppingService = ShoppingService;
