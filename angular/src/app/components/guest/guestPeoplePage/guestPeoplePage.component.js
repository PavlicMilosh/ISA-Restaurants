"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var guest_service_1 = require("../../../services/guest.service");
var GuestPeoplePageComponent = (function () {
    function GuestPeoplePageComponent(guestService) {
        var _this = this;
        this.guestService = guestService;
        this.people = [];
        this.guestService.getAllGuests(1).subscribe(function (data) {
            _this.people = data;
        });
    }
    GuestPeoplePageComponent.prototype.ngOnInit = function () {
    };
    GuestPeoplePageComponent.prototype.sendFriendRequest = function (toWhomId) {
        var _this = this;
        this.guestService.sendFriendRequest(1, toWhomId).subscribe(function (data) {
            for (var i = 0; i < _this.people.length; i++)
                if (_this.people[i].id == toWhomId) {
                    _this.people[i].friendshipStatus = 'PENDING';
                    _this.people[i].lastActionUserId = 1; // generalizovati id
                }
        });
    };
    GuestPeoplePageComponent.prototype.declineRequest = function (fromWhomId) {
        var _this = this;
        this.guestService.declineFriendRequest(1, fromWhomId).subscribe(function (data) {
            for (var i = 0; i < _this.people.length; i++)
                if (_this.people[i].id == fromWhomId) {
                    _this.people[i].friendshipStatus = 'DECLINED';
                    _this.people[i].lastActionUserId = 1; // generalizovati id
                }
            console.log(data);
        });
    };
    GuestPeoplePageComponent.prototype.acceptRequest = function (fromWhomId) {
        var _this = this;
        this.guestService.acceptFriendRequest(1, fromWhomId).subscribe(function (data) {
            for (var i = 0; i < _this.people.length; i++)
                if (_this.people[i].id == fromWhomId) {
                    _this.people[i].friendshipStatus = 'ACCEPTED';
                    _this.people[i].lastActionUserId = 1; // ovde treba isto generalizovati id
                }
            console.log(data);
        });
    };
    GuestPeoplePageComponent.prototype.sendInvitation = function (toWhomId) {
        this.guestService.sendInvitation(toWhomId);
    };
    GuestPeoplePageComponent.prototype.unfriend = function (whoId) {
        var _this = this;
        this.guestService.unfriend(1, whoId).subscribe(function (data) {
            for (var i = 0; i < _this.people.length; i++)
                if (_this.people[i].id == whoId) {
                    _this.people[i].friendshipStatus = 'UNFRIENDED';
                    _this.people[i].lastActionUserId = 1; // ovde treba isto generalizovati id
                }
            console.log(data);
        });
    };
    GuestPeoplePageComponent.prototype.searchAllGuests = function () {
        var _this = this;
        if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length) {
            this.guestService.getAllGuests(1).subscribe(function (data) { return _this.people = data; });
        }
        this.guestService.searchAllGuests(1, this.searchParams).subscribe(function (data) { return _this.people = data; });
    };
    return GuestPeoplePageComponent;
}());
GuestPeoplePageComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'guestFriendsPage',
        templateUrl: 'guestPeoplePage.component.html',
        styleUrls: ['guestPeoplePage.component.css', '../../style/tableStyle.css'],
        providers: [guest_service_1.GuestService]
    })
], GuestPeoplePageComponent);
exports.GuestPeoplePageComponent = GuestPeoplePageComponent;
