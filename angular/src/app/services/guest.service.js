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
        return this.http.post("http://localhost:8080/guest/register/", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.updateGuest = function (guestId, email, pass, firstName, lastName) {
        var guest = {
            email: email,
            password: pass,
            firstName: firstName,
            lastName: lastName,
        };
        var param = JSON.stringify(guest);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.put("http://localhost:8080/guest/" + guestId + "/update", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.getAllGuests = function (guestId) {
        var headers = new http_1.Headers();
        return this.http.get("http://localhost:8080/guest/" + guestId + "/getAllGuests", { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.getFriends = function (guestId) {
        var headers = new http_1.Headers();
        return this.http.get("http://localhost:8080/guest/" + guestId + "/getFriends", { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.getFriendRequests = function (guestId) {
        var headers = new http_1.Headers();
        return this.http.get("http://localhost:8080/guest/" + guestId + "/getFriendRequests", { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.sendFriendRequest = function (guestId, toWhomId) {
        var headers = new http_1.Headers();
        return this.http.post("http://localhost:8080/guest/" + guestId + "/sendFriendRequest/" + toWhomId, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.acceptFriendRequest = function (guestId, fromWhomId) {
        var headers = new http_1.Headers();
        return this.http.put("http://localhost:8080//guest/" + guestId + "/acceptFriendRequest/" + fromWhomId, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.declineFriendRequest = function (guestId, fromWhomId) {
        var headers = new http_1.Headers();
        return this.http.put("http://localhost:8080//guest/" + guestId + "/declineFriendRequest/" + fromWhomId, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.unfriend = function (guestId, friendId) {
        console.log(guestId + " UNFRIENDED " + friendId);
        var headers = new http_1.Headers();
        return this.http.put("http://localhost:8080/guest/" + guestId + "/unfriendUser/" + friendId, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.sendInvitation = function (toWhom) {
        console.log("INVITATION NOT YET IMPLEMENTED!");
    };
    GuestService.prototype.searchAllGuests = function (guestId, searchParams) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.put("http://localhost:8080/guest/" + guestId + "/searchForFriends", searchParams, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.searchFriends = function (guestId, searchParams) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.put("http://localhost:8080/guest/" + guestId + "/searchMyFriends", searchParams, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    GuestService.prototype.sendReservation = function (guestId, reservation) {
        var param = JSON.stringify(reservation);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.post("http://localhost:8080/guest/" + guestId + "/sendReservation", param, { headers: headers })
            .map(function (res) { return res.json(); });
    };
    return GuestService;
}());
GuestService = __decorate([
    core_1.Injectable()
], GuestService);
exports.GuestService = GuestService;
