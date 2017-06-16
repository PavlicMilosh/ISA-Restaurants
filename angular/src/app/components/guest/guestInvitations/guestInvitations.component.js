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
var GuestInvitationsComponent = (function () {
    function GuestInvitationsComponent(guestService) {
        var _this = this;
        this.guestService = guestService;
        this.notify = new core_1.EventEmitter();
        this.data = [];
        this.friends = [];
        this.invites = [];
        this.searchParams = "";
        this.guestService.getFriends().subscribe(function (data) {
            _this.data = data;
            for (var i = 0; i < _this.data.length; i++)
                _this.data[i].invited = false;
            _this.friends = JSON.parse(JSON.stringify(_this.data));
        }, function (error) { return alert(error); });
    }
    GuestInvitationsComponent.prototype.ngOnChanges = function (changes) {
        var _this = this;
        this.invites = [];
        this.friends = [];
        this.guestService.getFriends().subscribe(function (data) {
            _this.data = data;
            for (var i = 0; i < _this.data.length; i++)
                _this.data[i].invited = false;
            _this.friends = JSON.parse(JSON.stringify(_this.data));
        }, function (error) { return alert(error); });
    };
    GuestInvitationsComponent.prototype.ngOnInit = function () {
        this.friends = this.data;
    };
    GuestInvitationsComponent.prototype.remove = function (whoId) {
        for (var i = 0; i < this.data.length; i++) {
            console.log(this.data[i]);
            if (this.data[i].id == whoId) {
                console.log(this.data[i]);
                this.friends.push(this.data[i]);
                this.data[i].invited = true;
                break;
            }
        }
        for (var i = 0; i < this.invites.length; i++) {
            if (this.invites[i].id == whoId) {
                this.invites.splice(i, 1);
                break;
            }
        }
        this.notify.emit(this.invites);
    };
    GuestInvitationsComponent.prototype.invite = function (whoId) {
        for (var i = 0; i < this.data.length; i++) {
            if (this.data[i].id == whoId) {
                this.invites.push(this.data[i]);
                this.data[i].invited = true;
                break;
            }
        }
        for (var i = 0; i < this.friends.length; i++) {
            if (this.friends[i].id == whoId) {
                this.friends.splice(i, 1);
                break;
            }
        }
        this.notify.emit(this.invites);
    };
    GuestInvitationsComponent.prototype.searchFriends = function () {
        this.friends = [];
        if (this.searchParams == null || !this.searchParams.replace(/\s/g, '').length) {
            console.log("EMPTY PARAMS");
            console.log(this.data);
            for (var i = 0; i < this.data.length; i++)
                if (this.data[i].invited == false)
                    this.friends.push(this.data[i]);
        }
        else {
            for (var i = 0; i < this.data.length; i++) {
                console.log("----------------");
                console.log(this.data[i].firstName.toLowerCase().indexOf(this.searchParams.toString().toLowerCase()));
                console.log(this.data[i].lastName.toLowerCase().indexOf(this.searchParams.toString().toLowerCase()));
                console.log(this.data[i].email.toLowerCase().indexOf(this.searchParams.toString().toLowerCase()));
                console.log(!this.data[i].invited);
                console.log("----------------");
                if ((this.data[i].firstName.toLowerCase().indexOf(this.searchParams.toLowerCase()) != -1 ||
                    this.data[i].lastName.toLowerCase().indexOf(this.searchParams.toLowerCase()) != -1 ||
                    this.data[i].email.toLowerCase().indexOf(this.searchParams.toLowerCase()) != -1) &&
                    !this.data[i].invited) {
                    this.friends.push(this.data[i]);
                }
            }
        }
    };
    return GuestInvitationsComponent;
}());
__decorate([
    core_1.Input()
], GuestInvitationsComponent.prototype, "restaurant", void 0);
__decorate([
    core_1.Output()
], GuestInvitationsComponent.prototype, "notify", void 0);
GuestInvitationsComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'guestInvitations',
        templateUrl: 'guestInvitations.component.html',
        styleUrls: ['guestInvitations.component.css', '../../style/tableStyle.css'],
        providers: [guest_service_1.GuestService]
    })
], GuestInvitationsComponent);
exports.GuestInvitationsComponent = GuestInvitationsComponent;
