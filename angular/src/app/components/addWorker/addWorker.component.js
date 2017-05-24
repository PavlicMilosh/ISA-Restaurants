"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var users_service_1 = require("../../services/users.service");
var AddWorkerComponent = (function () {
    function AddWorkerComponent(userService) {
        this.userService = userService;
        this.role = "Select Role";
    }
    AddWorkerComponent.prototype.ngOnInit = function () {
    };
    AddWorkerComponent.prototype.setRole = function (role) {
        this.role = role;
    };
    AddWorkerComponent.prototype.addWorker = function () {
        this.userService.addWorker(this.firstName, this.lastName, this.email, this.password, this.clothesNumber, this.footwearNumber, this.role)
            .subscribe(function (data) { return console.log(data); });
    };
    return AddWorkerComponent;
}());
AddWorkerComponent = __decorate([
    core_1.Component({
        selector: 'app-add-worker',
        templateUrl: './addWorker.component.html',
        styleUrls: ['./addWorker.component.css'],
        providers: [users_service_1.UserService]
    })
], AddWorkerComponent);
exports.AddWorkerComponent = AddWorkerComponent;
