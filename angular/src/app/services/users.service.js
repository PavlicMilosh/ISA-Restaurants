"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
require("rxjs/add/operator/map");
var logged_utils_1 = require("../utils/logged.utils");
var UserService = (function () {
    function UserService(http) {
        this.http = http;
    }
    UserService.prototype.addSM = function (email, pass, firstName, lastName) {
        var systemManager = {
            email: email,
            password: pass,
            firstName: firstName,
            lastName: lastName,
        };
        var param = JSON.stringify(systemManager);
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/users/register/sysManager", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    UserService.prototype.addProvider = function (email, pass, firstName, lastName) {
        var provider = {
            email: email,
            password: pass,
            firstName: firstName,
            lastName: lastName,
        };
        var param = JSON.stringify(provider);
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/users/register/provider", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    UserService.prototype.updateProvider = function (provider) {
        console.log(provider);
        var param = JSON.stringify(provider);
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.put("http://localhost:8080/users/" + provider.id + "/updateProvider", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    UserService.prototype.updateBarman = function (id, email, pass, firstName, lastName) {
        var user = {
            id: id,
            email: email,
            password: pass,
            firstName: firstName,
            lastName: lastName,
        };
        var param = JSON.stringify(user);
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/users/update/barman", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    UserService.prototype.changePassword = function (id, email, pass, firstName, lastName) {
        var user = {
            id: id,
            email: email,
            password: pass,
            firstName: firstName,
            lastName: lastName,
        };
        var param = JSON.stringify(user);
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/users/update/barman", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    UserService.prototype.addWorker = function (firstName, lastName, email, password, clothesNumber, footwearNumber, role) {
        var worker = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password,
            clothesNumber: clothesNumber,
            footwearNumber: footwearNumber
        };
        var path = '';
        var id = logged_utils_1.LoggedUtils.getId();
        if (role == 'Waiter')
            path = "/addWaiter";
        else if (role == 'Cook')
            path = "/addCook";
        else
            path = "/addBartender";
        var param = JSON.stringify(worker);
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/restaurants/" + id + path, param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    UserService.prototype.getUser = function (email, pass, firstName, lastName) {
        var user = {
            email: email,
            password: pass,
            firstName: firstName,
            lastName: lastName,
        };
        var param = JSON.stringify(user);
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/users/register/barman", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    UserService.prototype.getById = function (userId) {
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get("http://localhost:8080/users/" + userId, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    UserService.prototype.addSchedule = function (oneSchedule, userId) {
        console.log(oneSchedule);
        var schedule = [];
        if (oneSchedule.day == 0) {
            for (var i = 0; i < 8; i++) {
                var s = {
                    id: oneSchedule.id,
                    startTime: oneSchedule.startTime,
                    endTime: oneSchedule.endTime,
                    day: i,
                    regionId: oneSchedule.regionId
                };
                schedule.push(s);
            }
        }
        else {
            schedule.push(oneSchedule);
        }
        console.log(schedule);
        var param = JSON.stringify(schedule);
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/users/" + userId + "/addSchedule", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    UserService.prototype.getSchedule = function () {
        var userId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get("http://localhost:8080/users/" + userId + "/getSchedule")
            .map(function (res) { return res.json(); });
    };
    UserService.prototype.getRestaurant = function () {
        var userId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get("http://localhost:8080/users/" + userId + "/getRestaurant", { headers: headers })
            .map(function (res) { return res.json(); });
    };
    return UserService;
}());
UserService = __decorate([
    core_1.Injectable()
], UserService);
exports.UserService = UserService;
