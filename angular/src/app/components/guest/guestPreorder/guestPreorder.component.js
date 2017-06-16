"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var guest_service_1 = require("../../../services/guest.service");
var GuestPreorderComponent = (function () {
    function GuestPreorderComponent() {
        this.notifyDrinks = new core_1.EventEmitter();
        this.notifyDishes = new core_1.EventEmitter();
        this.drinkOrders = [];
        this.dishOrders = [];
    }
    GuestPreorderComponent.prototype.ngOnChanges = function (changes) {
        if (changes['restaurant'] != null)
            this.restaurant = changes['restaurant'].currentValue;
        this.drinkOrders = [];
        this.dishOrders = [];
    };
    GuestPreorderComponent.prototype.addDrink = function (id) {
        for (var i = 0; i < this.restaurant.drinks.length; i++) {
            if (this.restaurant.drinks[i].id == id) {
                var inTheList = false;
                for (var j = 0; j < this.drinkOrders.length; j++) {
                    if (this.drinkOrders[j].id == id) {
                        this.drinkOrders[j].quantity++;
                        inTheList = true;
                        break;
                    }
                }
                if (!inTheList) {
                    var d = this.restaurant.drinks[i];
                    d.quantity = 1;
                    this.drinkOrders.push(d);
                }
                break;
            }
        }
        this.notifyDrinks.emit(this.drinkOrders);
    };
    GuestPreorderComponent.prototype.addDish = function (id) {
        for (var i = 0; i < this.restaurant.dishes.length; i++) {
            if (this.restaurant.dishes[i].id == id) {
                var inTheList = false;
                for (var j = 0; j < this.dishOrders.length; j++) {
                    if (this.dishOrders[j].id == id) {
                        this.dishOrders[j].quantity++;
                        inTheList = true;
                        break;
                    }
                }
                if (!inTheList) {
                    var d = this.restaurant.dishes[i];
                    d.quantity = 1;
                    this.dishOrders.push(d);
                }
                break;
            }
        }
        this.notifyDishes.emit(this.dishOrders);
    };
    GuestPreorderComponent.prototype.removeDrink = function (id) {
        for (var i = 0; i < this.drinkOrders.length; i++) {
            if (this.drinkOrders[i].id == id) {
                this.drinkOrders[i].quantity = 0;
                this.drinkOrders.splice(i, 1);
                break;
            }
        }
        this.notifyDrinks.emit(this.drinkOrders);
    };
    GuestPreorderComponent.prototype.removeDish = function (id) {
        for (var i = 0; i < this.dishOrders.length; i++) {
            if (this.dishOrders[i].id == id) {
                this.dishOrders[i].quantity = 0;
                this.dishOrders.splice(i, 1);
                break;
            }
        }
        this.notifyDishes.emit(this.dishOrders);
    };
    return GuestPreorderComponent;
}());
__decorate([
    core_1.Input()
], GuestPreorderComponent.prototype, "restaurant", void 0);
__decorate([
    core_1.Output()
], GuestPreorderComponent.prototype, "notifyDrinks", void 0);
__decorate([
    core_1.Output()
], GuestPreorderComponent.prototype, "notifyDishes", void 0);
GuestPreorderComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'guestPreorder',
        templateUrl: 'guestPreorder.component.html',
        styleUrls: ['guestPreorder.component.css', '../../style/tableStyle.css'],
        providers: [guest_service_1.GuestService]
    })
], GuestPreorderComponent);
exports.GuestPreorderComponent = GuestPreorderComponent;
