"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var ShoppingList = (function () {
    function ShoppingList() {
        this.deadlineDate = new Date();
        this.items = [];
    }
    ShoppingList.prototype.addItem = function (item) {
        this.items.push(item);
    };
    return ShoppingList;
}());
exports.ShoppingList = ShoppingList;
