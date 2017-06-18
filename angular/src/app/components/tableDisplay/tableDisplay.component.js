"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Created by djuro on 5/15/2017.
 */
var core_1 = require("@angular/core");
var users_service_1 = require("./../../services/users.service");
require("fabric");
var TableDisplay = (function () {
    function TableDisplay(userService) {
        this.userService = userService;
        this.table1 = { id: 1, topC: 100, leftC: 100, angle: 50 };
        this.table2 = { id: 2, topC: 100, leftC: 170, angle: 50 };
        this.table3 = { id: 3, topC: 170, leftC: 100, angle: 50 };
        this.userTables = [this.table1, this.table2, this.table3];
    }
    TableDisplay.prototype.ngOnInit = function () {
        this.canvas = new fabric.Canvas('canvas');
        this.canvas.setDimensions({ width: 500, height: 600 });
        //this.canvas.enab
        for (var i = 0; i < this.userTables.length; i++) {
            var rect = new fabric.Rect({
                left: this.userTables[i].leftC,
                top: this.userTables[i].topC,
                fill: 'blue',
                width: 50,
                height: 50
            });
            this.canvas.add(rect);
        }
    };
    return TableDisplay;
}());
TableDisplay = __decorate([
    core_1.Component({
        selector: 'table-display',
        templateUrl: './tableDisplay.component.html',
        styleUrls: ['./tableDisplay.component.css'],
        providers: [users_service_1.UserService]
    })
], TableDisplay);
exports.TableDisplay = TableDisplay;
