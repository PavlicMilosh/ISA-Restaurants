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
var GuestUpdateComponent = (function () {
    function GuestUpdateComponent(guestService) {
        this.guestService = guestService;
    }
    GuestUpdateComponent.prototype.updateGuest = function () {
        var _this = this;
        this.guestService.updateGuest(this.email, this.password, this.firstName, this.lastName).subscribe(function (data) { return _this.userDTO = data; }, function (error) { return alert(error); });
    };
    return GuestUpdateComponent;
}());
GuestUpdateComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'guestUpdate',
        templateUrl: 'guestUpdate.component.html',
        styleUrls: ['guestUpdate.component.css', '../../style/formStyle.css'],
        providers: [guest_service_1.GuestService]
    })
], GuestUpdateComponent);
exports.GuestUpdateComponent = GuestUpdateComponent;
