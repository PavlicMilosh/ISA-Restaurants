var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
var RestaurantService = (function () {
    function RestaurantService(http) {
        this.http = http;
    }
    RestaurantService.prototype.postRestaurant = function (restaurantName, restaurantDescription) {
        var restaurant = {
            restaurantName: restaurantName,
            restaurantDescription: restaurantDescription
        };
        var param = JSON.stringify(restaurant);
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.post("/restaurants/add", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    return RestaurantService;
}());
RestaurantService = __decorate([
    Injectable(),
    __metadata("design:paramtypes", [Http])
], RestaurantService);
export { RestaurantService };
//# sourceMappingURL=restaurants.service.js.map