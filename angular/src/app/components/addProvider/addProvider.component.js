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
var AddProviderComponent = (function () {
    function AddProviderComponent(userService) {
        this.userService = userService;
    }
    AddProviderComponent.prototype.ngOnInit = function () {
    };
    AddProviderComponent.prototype.addProvider = function () {
        var _this = this;
        this.userService.addProvider(this.email, this.password, this.firstName, this.lastName).subscribe(function (data) { return _this.userDTO = data; }, function (error) { return alert(error); });
    };
    return AddProviderComponent;
}());
AddProviderComponent = __decorate([
    core_1.Component({
        selector: 'app-add-provider',
        templateUrl: './addProvider.component.html',
        styleUrls: ['./addProvider.component.css'],
        providers: [users_service_1.UserService]
    })
], AddProviderComponent);
exports.AddProviderComponent = AddProviderComponent;
