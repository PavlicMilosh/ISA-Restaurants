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
var logged_utils_1 = require("../../../utils/logged.utils");
var GuestPeopleComponent = (function () {
    function GuestPeopleComponent(guestService) {
        var _this = this;
        this.guestService = guestService;
        this.people = [];
        if (logged_utils_1.LoggedUtils.isEmpty())
            this.myId = -10;
        else
            this.myId = logged_utils_1.LoggedUtils.getId();
        this.guestService.getAllGuests().subscribe(function (data) {
            _this.people = data;
        });
    }
    GuestPeopleComponent.prototype.ngOnInit = function () {
        this.myId = logged_utils_1.LoggedUtils.getId();
    };
    GuestPeopleComponent.prototype.sendFriendRequest = function (toWhomId) {
        var _this = this;
        this.guestService.sendFriendRequest(toWhomId).subscribe(function (data) {
            for (var i = 0; i < _this.people.length; i++)
                if (_this.people[i].id == toWhomId) {
                    _this.people[i].friendshipStatus = 'PENDING';
                    _this.people[i].lastActionUserId = 1; // generalizovati id
                }
        });
    };
    GuestPeopleComponent.prototype.declineRequest = function (fromWhomId) {
        var _this = this;
        this.guestService.declineFriendRequest(fromWhomId).subscribe(function (data) {
            for (var i = 0; i < _this.people.length; i++)
                if (_this.people[i].id == fromWhomId) {
                    _this.people[i].friendshipStatus = 'DECLINED';
                    _this.people[i].lastActionUserId = 1; // generalizovati id
                }
            console.log(data);
        });
    };
    GuestPeopleComponent.prototype.acceptRequest = function (fromWhomId) {
        var _this = this;
        this.guestService.acceptFriendRequest(fromWhomId).subscribe(function (data) {
            for (var i = 0; i < _this.people.length; i++)
                if (_this.people[i].id == fromWhomId) {
                    _this.people[i].friendshipStatus = 'ACCEPTED';
                    _this.people[i].lastActionUserId = 1; // ovde treba isto generalizovati id
                }
            console.log(data);
        });
    };
    GuestPeopleComponent.prototype.unfriend = function (whoId) {
        var _this = this;
        this.guestService.unfriend(whoId).subscribe(function (data) {
            for (var i = 0; i < _this.people.length; i++)
                if (_this.people[i].id == whoId) {
                    _this.people[i].friendshipStatus = 'UNFRIENDED';
                    _this.people[i].lastActionUserId = 1; // ovde treba isto generalizovati id
                }
            console.log(data);
        });
    };
    GuestPeopleComponent.prototype.searchAllGuests = function () {
        var _this = this;
        if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length) {
            this.guestService.getAllGuests().subscribe(function (data) { return _this.people = data; });
        }
        this.guestService.searchAllGuests(this.searchParams).subscribe(function (data) { return _this.people = data; });
    };
    return GuestPeopleComponent;
}());
GuestPeopleComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'guestPeople',
        templateUrl: 'guestPeople.component.html',
        styleUrls: ['guestPeople.component.css', '../../style/tableStyle.css'],
        providers: [guest_service_1.GuestService]
    })
], GuestPeopleComponent);
exports.GuestPeopleComponent = GuestPeopleComponent;
