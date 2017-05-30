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
var GuestRequestPageComponent = (function () {
    function GuestRequestPageComponent(guestService) {
        var _this = this;
        this.guestService = guestService;
        this.requests = [];
        this.guestService.getFriendRequests(1).subscribe(function (data) {
            _this.requests = data;
            console.log(data);
        });
    }
    GuestRequestPageComponent.prototype.ngOnInit = function () {
    };
    GuestRequestPageComponent.prototype.declineRequest = function (fromWhomId) {
        var _this = this;
        this.guestService.declineFriendRequest(1, fromWhomId).subscribe(function (data) {
            for (var i = 0; i < _this.requests.length; i++)
                if (_this.requests[i].id == fromWhomId)
                    _this.requests.splice(i, 1);
            console.log(data);
        });
    };
    GuestRequestPageComponent.prototype.acceptRequest = function (fromWhomId) {
        var _this = this;
        this.guestService.acceptFriendRequest(1, fromWhomId).subscribe(function (data) {
            for (var i = 0; i < _this.requests.length; i++)
                if (_this.requests[i].id == fromWhomId)
                    _this.requests.splice(i, 1);
            console.log(data);
        });
    };
    return GuestRequestPageComponent;
}());
GuestRequestPageComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'guestFriendsPage',
        templateUrl: 'guestRequestPage.component.html',
        styleUrls: ['guestRequestPage.component.css', '../../style/tableStyle.css'],
        providers: [guest_service_1.GuestService]
    })
], GuestRequestPageComponent);
exports.GuestRequestPageComponent = GuestRequestPageComponent;
