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
var address_utils_1 = require("../utils/address.utils");
var GuestService = (function () {
    function GuestService(http) {
        this.http = http;
    }
    GuestService.prototype.registerGuest = function (email, pass, firstName, lastName) {
        var guest = {
            email: email,
            password: pass,
            firstName: firstName,
            lastName: lastName,
        };
        var param = JSON.stringify(guest);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.post(address_utils_1.AddressUtils.backendAddress() + "/guest/register", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.updateGuest = function (email, pass, firstName, lastName) {
        var guest = {
            email: email,
            password: pass,
            firstName: firstName,
            lastName: lastName,
        };
        var guestId = logged_utils_1.LoggedUtils.getId();
        var param = JSON.stringify(guest);
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.put(address_utils_1.AddressUtils.backendAddress() + "/" + guestId + "/update", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.getAllGuests = function () {
        var guestId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get(address_utils_1.AddressUtils.backendAddress() + "/guest/" + guestId + "/getAllGuests", { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.getFriends = function () {
        var guestId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get(address_utils_1.AddressUtils.backendAddress() + "/guest/" + guestId + "/getFriends", { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.getFriendRequests = function () {
        var guestId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get(address_utils_1.AddressUtils.backendAddress() + "/guest/" + guestId + "/getFriendRequests", { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.sendFriendRequest = function (toWhomId) {
        var guestId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.post(address_utils_1.AddressUtils.backendAddress() + "/guest/" + guestId + "/sendFriendRequest/" + toWhomId, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.acceptFriendRequest = function (fromWhomId) {
        var guestId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.put(address_utils_1.AddressUtils.backendAddress() + "/guest/" + guestId + "/acceptFriendRequest/" + fromWhomId, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.declineFriendRequest = function (fromWhomId) {
        var guestId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.put(address_utils_1.AddressUtils.backendAddress() + "/guest/" + guestId + "/declineFriendRequest/" + fromWhomId, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.unfriend = function (friendId) {
        var guestId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.put(address_utils_1.AddressUtils.backendAddress() + "/guest/" + guestId + "/unfriendUser/" + friendId, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.searchAllGuests = function (searchParams) {
        var guestId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.put(address_utils_1.AddressUtils.backendAddress() + "/guest/" + guestId + "/searchForFriends", searchParams, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.searchFriends = function (searchParams) {
        var guestId = logged_utils_1.LoggedUtils.getId();
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.put(address_utils_1.AddressUtils.backendAddress() + "/guest/" + guestId + "/searchMyFriends", searchParams, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.sendReservation = function (reservation) {
        var guestId = logged_utils_1.LoggedUtils.getId();
        var param = JSON.stringify(reservation);
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        headers.append('Content-Type', 'application/json');
        return this.http.post(address_utils_1.AddressUtils.backendAddress() + "/guest/" + guestId + "/sendReservation", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.getTables = function (reservation) {
        var headers = new http_1.Headers();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.put(address_utils_1.AddressUtils.backendAddress() + "/guest/" + logged_utils_1.LoggedUtils.getId() + "/getTables", reservation, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.getRestaurants = function () {
        var headers = new http_1.Headers();
        var guestId = logged_utils_1.LoggedUtils.getId();
        headers.append("X-Auth-Token", logged_utils_1.LoggedUtils.getToken());
        return this.http.get(address_utils_1.AddressUtils.backendAddress() + "/guest/" + guestId + "/getRestaurants", { headers: headers })
            .map(function (res) { return res.json(); });
    };
    return GuestService;
}());
GuestService = __decorate([
    core_1.Injectable()
], GuestService);
exports.GuestService = GuestService;
