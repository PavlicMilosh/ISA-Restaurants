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
var GuestFriendsComponent = (function () {
    function GuestFriendsComponent(guestService) {
        var _this = this;
        this.guestService = guestService;
        this.people = [];
        this.searchParams = "";
        this.guestService.getFriends().subscribe(function (data) { return _this.people = data; }, function (error) { return alert(error); });
    }
    GuestFriendsComponent.prototype.ngOnInit = function () { };
    GuestFriendsComponent.prototype.unfriend = function (whoId) {
        var _this = this;
        this.guestService.unfriend(whoId).subscribe(function (data) {
            for (var i = 0; i < _this.people.length; i++)
                if (_this.people[i].id == whoId) {
                    _this.people.splice(i, 1);
                }
            console.log(data);
        });
    };
    GuestFriendsComponent.prototype.searchFriends = function () {
        var _this = this;
        if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length) {
            this.guestService.getFriends().subscribe(function (data) { return _this.people = data; });
        }
        this.guestService.searchFriends(this.searchParams).subscribe(function (data) { return _this.people = data; }, function (error) { return alert(error); });
    };
    return GuestFriendsComponent;
}());
GuestFriendsComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'guestFriends',
        templateUrl: 'guestFriends.component.html',
        styleUrls: ['guestFriends.component.css', '../../style/tableStyle.css'],
        providers: [guest_service_1.GuestService]
    })
], GuestFriendsComponent);
exports.GuestFriendsComponent = GuestFriendsComponent;
