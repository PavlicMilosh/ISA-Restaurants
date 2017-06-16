"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var ShoppingList_1 = require("../ShoppingList");
var ShoppingItem_1 = require("../ShoppingItem");
var shopping_service_1 = require("../../../services/shopping.service");
var common_1 = require("@angular/common");
var AddShoppingListComponent = (function () {
    function AddShoppingListComponent(shoppingService, datePipe) {
        this.shoppingService = shoppingService;
        this.datePipe = datePipe;
    }
    AddShoppingListComponent.prototype.ngOnInit = function () {
        this.shoppingList = new ShoppingList_1.ShoppingList();
        this.shoppingList.addItem(new ShoppingItem_1.ShoppingItem("fadfsdfds", "fdssdfdsf"));
        this.shoppingList.addItem(new ShoppingItem_1.ShoppingItem("lkj", "jkl"));
    };
    AddShoppingListComponent.prototype.addItem = function () {
        this.shoppingList.addItem(new ShoppingItem_1.ShoppingItem("", ""));
    };
    AddShoppingListComponent.prototype.addList = function () {
        this.shoppingList.deadline = this.datePipe.transform(this.shoppingList.deadlineDate, "EEE, dd MMM yyyy HH:mm:ss zzz");
        this.shoppingService.addList(this.shoppingList).subscribe(function (data) { return console.log(data); });
    };
    return AddShoppingListComponent;
}());
AddShoppingListComponent = __decorate([
    core_1.Component({
        selector: 'app-add-shopping-list',
        templateUrl: './addShoppingList.component.html',
        styleUrls: ['./addShoppingList.component.css'],
        providers: [shopping_service_1.ShoppingService, common_1.DatePipe]
    })
], AddShoppingListComponent);
exports.AddShoppingListComponent = AddShoppingListComponent;
