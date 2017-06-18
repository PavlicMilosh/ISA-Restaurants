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
var OrderDishes = (function () {
    function OrderDishes(orderService) {
        var _this = this;
        this.orderService = orderService;
        this.dish1 = { id: 1, name: "dish1", description: "my dish1", price: 0 };
        this.dish2 = { id: 2, name: "dish2", description: "my dish2", price: 0 };
        this.dish3 = { id: 3, name: "dish3", description: "my dish3", price: 0 };
        this.drink1 = { id: 1, name: "drink1", description: "my drink1", price: 0 };
        this.drink2 = { id: 2, name: "drink2", description: "my drink2", price: 0 };
        this.drink3 = { id: 3, name: "drink3", description: "my drink3", price: 0 };
        this.orderItem1 = { id: null, dish: this.dish1, drink: null, isDish: true, number: 2, preparing: false, finished: false };
        this.orderItem2 = { id: null, dish: null, drink: this.drink1, isDish: false, number: 2, preparing: false, finished: false };
        this.orderItem3 = { id: null, dish: this.dish2, drink: null, isDish: true, number: 2, preparing: false, finished: false };
        this.orderItem4 = { id: null, dish: this.dish3, drink: null, isDish: true, number: 2, preparing: false, finished: false };
        this.orderItem5 = { id: null, dish: null, drink: this.drink2, isDish: false, number: 2, preparing: false, finished: false };
        this.orderItem6 = { id: null, dish: null, drink: this.dish3, isDish: false, number: 2, preparing: false, finished: false };
        this.orderItems1 = [this.orderItem1, this.orderItem2, this.orderItem3];
        this.orderItems2 = [this.orderItem4, this.orderItem5, this.orderItem6];
        this.dishes = [this.dish1, this.dish2, this.dish3];
        this.drinks = [this.drink1, this.drink2, this.drink3];
        this.selectOrders = [];
        this.selectItems = [];
        this.orderService.getAllOrders().subscribe(function (data) { return _this.orders = data; }, function (error) { return alert(error); });
    }
    OrderDishes.prototype.selectOrder = function (order, item) {
        console.log(order + "     " + item);
        var idx1 = 0;
        for (var i = 0; i < this.orders.length; i++) {
            if (this.orders[i].id == order) {
                idx1 = i;
                break;
            }
        }
        this.orders[idx1].orderItems[item].preparing = true;
        //this.orderService.preparingOrderItem(this.orders[idx1].orderItems[item].id);
        this.selectItems.push(this.orders[idx1].orderItems[item]);
        this.orders[idx1].orderItems.splice(item, 1);
        console.log(this.orders.length);
    };
    OrderDishes.prototype.finishedItem = function (index) {
        //this.finishedItem(this.selectItems[index].id);
        this.selectItems.splice(index, 1);
    };
    return OrderDishes;
}());
OrderDishes = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'orderDishes',
        templateUrl: './orderDishes.component.html',
        styleUrls: ['./orderDishes.component.css'],
        providers: [order_service_1.OrderService]
    })
], OrderDishes);
exports.OrderDishes = OrderDishes;
