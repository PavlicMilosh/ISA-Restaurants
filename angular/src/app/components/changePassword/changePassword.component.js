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
var ChangePassword = (function () {
    function ChangePassword(userService) {
        var _this = this;
        this.userService = userService;
        this.userService.getUser("test123@live.com", "12345", "TestName", "TestLastName").subscribe(function (data) { return _this.userDTO = data; }, function (error) { return alert(error); });
    }
    ChangePassword.prototype.change = function () {
        var _this = this;
        if (this.oldPassword == this.userDTO.password) {
            console.log(this.oldPassword);
            if (this.newPassword1 == this.newPassword2) {
                this.userService.changePassword(this.userDTO.id, this.userDTO.email, this.newPassword1, this.userDTO.firstName, this.userDTO.lastName).subscribe(function (data) { return _this.userDTO = data; }, function (error) { return alert(error); }, function () { return alert("Successfully!"); });
            }
            else {
                alert("Different passwords!");
            }
        }
        else {
            alert("Incorrect password!");
        }
    };
    return ChangePassword;
}());
ChangePassword = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'changePassword',
        templateUrl: './changePassword.component.html',
        styleUrls: ['./changePassword.component.css'],
        providers: [users_service_1.UserService]
    })
], ChangePassword);
exports.ChangePassword = ChangePassword;
