"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var order_service_1 = require("../../services/order.service");
var restaurants_service_1 = require("../../services/restaurants.service");
var users_service_1 = require("../../services/users.service");
var MakeOrder = (function () {
    function MakeOrder(orderService, userService, restaurantService) {
        var _this = this;
        this.orderService = orderService;
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.orderItems = [];
        this.orderDishes = [];
        this.orderDrinks = [];
        this.dish1 = { id: null, name: "dish1", description: "my dish1", price: 0 };
        this.dish2 = { id: null, name: "dish2", description: "my dish2", price: 0 };
        this.dish3 = { id: null, name: "dish3", description: "my dish3", price: 0 };
        this.drink1 = { id: null, name: "drink1", description: "my drink1", price: 0 };
        this.drink2 = { id: null, name: "drink2", description: "my drink2", price: 0 };
        this.drink3 = { id: null, name: "drink3", description: "my drink3", price: 0 };
        this.dishes = [this.dish1, this.dish2, this.dish3];
        this.drinks = [this.drink1, this.drink2, this.drink3];
        this.table1 = { id: null, top: 100, left: 100, angle: 50 };
        this.restaurantOrders = { id: null, dishes: [], drinks: [], orders: [], restaurantId: 3 };
        this.restaurant = { id: 1, name: "stefan", description: "stefan", dishes: this.dishes,
            drinks: this.drinks, tables: [], managers: [], bartenders: [], cooks: [], waiters: [],
            schedule: [], regions: [] };
        this.countDrinks = [];
        this.countDishes = [];
        this.dishType = { id: null, restaurant: this.restaurant, name: "kuvana jela" };
        this.userService.getRestaurant().subscribe(function (data) { return _this.restaurant = data; }, function (error) { return alert(error); });
    }
    MakeOrder.prototype.init = function () {
        var _this = this;
        this.orderService.addDishType(this.dishType).subscribe(function (data) { return _this.dishType = data; }, function (error) { return alert(error); }, function () { return _this.init1(); });
    };
    MakeOrder.prototype.init1 = function () {
        var _this = this;
        this.userService.addWorker("stefan", "stefan", "stefan@gmail.com", "12345", 33, 50, "Waiter").subscribe(function (data) { return _this.barman = data; }, function (error) { return alert(error); });
    };
    MakeOrder.prototype.makeOrder = function () {
        var _this = this;
        this.orderService.makeOrder(this.orderItems, false, 0, this.restaurant.id).subscribe(function (data) { return _this.order = data; }, function (error) { return alert(error); }, function () { return _this.removeItems(); });
    };
    MakeOrder.prototype.removeItems = function () {
        this.orderItems = [];
    };
    MakeOrder.prototype.addDish = function (dish, index) {
        console.log(this.countDishes[index]);
        this.orderDishes.push(dish);
        this.orderItems.push({ dish: dish, drink: null, isDish: true, number: this.countDishes[index], preparing: false, finished: false });
    };
    MakeOrder.prototype.addDrink = function (drink, index) {
        console.log(this.countDrinks[index]);
        this.orderDrinks.push(drink);
        this.orderItems.push({ dish: null, drink: drink, isDish: false, number: this.countDrinks[index], preparing: false, finished: false });
    };
    MakeOrder.prototype.removeItem = function (index) {
        this.orderItems.splice(index, 1);
    };
    return MakeOrder;
}());
MakeOrder = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'makeOrder',
        templateUrl: './makeOrder.component.html',
        styleUrls: ['./makeOrder.component.css'],
        providers: [order_service_1.OrderService, restaurants_service_1.RestaurantService, users_service_1.UserService]
    })
], MakeOrder);
exports.MakeOrder = MakeOrder;
