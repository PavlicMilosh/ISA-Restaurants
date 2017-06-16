"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var authentication_service_1 = require("../../services/authentication.service");
var AuthenticationComponent = (function () {
    function AuthenticationComponent(autheticationService) {
        this.autheticationService = autheticationService;
        this.email = "";
        this.password = "";
    }
    AuthenticationComponent.prototype.authenticate = function () {
        this.autheticationService.authenticateUser(this.email, this.password).subscribe(function (data) { return localStorage.setItem("loggedUser", JSON.stringify(data)); }, function (error) { return alert("Incorrect username and/or password"); }, function () { return console.log(JSON.parse(localStorage.getItem("loggedUser"))); });
    };
    return AuthenticationComponent;
}());
AuthenticationComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'registerRM',
        templateUrl: './authentication.component.html',
        styleUrls: ['./authentication.component.css', '../style/formStyle.css'],
        providers: [authentication_service_1.AuthenticationService]
    })
], AuthenticationComponent);
exports.AuthenticationComponent = AuthenticationComponent;
