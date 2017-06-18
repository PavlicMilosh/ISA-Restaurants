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
var logged_utils_1 = require("../../utils/logged.utils");
var UpdateProviderComponent = (function () {
    function UpdateProviderComponent(userService) {
        var _this = this;
        this.userService = userService;
        this.provider = {
            id: -100,
            firstName: "",
            lastName: "",
            email: "",
            password: ""
        };
        this.userService.getById(logged_utils_1.LoggedUtils.getId()).subscribe(function (data) { return _this.provider = data; });
    }
    UpdateProviderComponent.prototype.ngOnInit = function () {
    };
    UpdateProviderComponent.prototype.updateProvider = function () {
        var canUpdate = true;
        if (this.newPassword != "" && this.repeatPassword != "" && this.email != "" && this.firstName != "" && this.lastName != "") {
            if (this.newPassword == this.repeatPassword && this.oldPassword == this.provider.password) {
                this.provider.password = this.newPassword;
            }
            else {
                canUpdate = false;
            }
        }
        else {
            canUpdate = false;
        }
        if (canUpdate) {
            console.log(this.provider);
            this.userService.updateProvider(this.provider).subscribe(function (data) { return console.log(data); });
        }
    };
    return UpdateProviderComponent;
}());
UpdateProviderComponent = __decorate([
    core_1.Component({
        selector: 'app-update-provider',
        templateUrl: './updateProvider.component.html',
        styleUrls: ['./updateProvider.component.css'],
        providers: [users_service_1.UserService]
    })
], UpdateProviderComponent);
exports.UpdateProviderComponent = UpdateProviderComponent;
