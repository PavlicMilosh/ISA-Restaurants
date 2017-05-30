/**
 * Created by djuro on 4/27/2017.
 */
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
var UpdateUser = (function () {
    function UpdateUser(userService) {
        var _this = this;
        this.userService = userService;
        this.userService.getUser("test123@live.com", "12345", "TestName", "TestLastName").subscribe(function (data) { return _this.userDTO = data; }, function (error) { return alert(error); }, function () { return _this.init(); });
    }
    UpdateUser.prototype.changeBarman = function () {
        var _this = this;
        this.userService.updateBarman(this.id, this.email, this.password, this.firstName, this.lastName).subscribe(function (data) { return _this.userDTO = data; }, function (error) { return alert(error); });
    };
    UpdateUser.prototype.init = function () {
        this.firstName = this.userDTO.firstName;
        this.lastName = this.userDTO.lastName;
        this.email = this.userDTO.email;
        this.id = this.userDTO.id;
        this.password = this.userDTO.password;
        console.log(this.userDTO.id);
        console.log(this.userDTO.password);
    };
    return UpdateUser;
}());
UpdateUser = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'updateUser',
        templateUrl: './updateUser.component.html',
        styleUrls: ['./updateUser.component.css'],
        providers: [users_service_1.UserService]
    })
], UpdateUser);
exports.UpdateUser = UpdateUser;
