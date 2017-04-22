var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component } from '@angular/core';
import { RestaurantService } from "../../services/restaurants.service";
var AddRestaurantComponent = (function () {
    function AddRestaurantComponent(restaurantService) {
        this.restaurantService = restaurantService;
    }
    AddRestaurantComponent.prototype.addRestaurant = function () {
        var _this = this;
        this.restaurantService.postRestaurant(this.restaurantName, this.restaurantDescription).subscribe(function (data) { return _this.restaurant = data; }, function (error) { return alert(error); });
    };
    return AddRestaurantComponent;
}());
AddRestaurantComponent = __decorate([
    Component({
        moduleId: module.id,
        selector: 'addRestaurant',
        templateUrl: './addRestaurant.component.html',
        styleUrls: ['./addRestaurant.component.css'],
        providers: [RestaurantService]
    }),
    __metadata("design:paramtypes", [RestaurantService])
], AddRestaurantComponent);
export { AddRestaurantComponent };
//# sourceMappingURL=addRestaurant.component.js.map