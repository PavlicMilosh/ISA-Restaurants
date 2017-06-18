"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var restaurants_service_1 = require("../../../services/restaurants.service");
require("fabric");
var GuestTablesComponent = (function () {
    function GuestTablesComponent(restaurantService, guestService) {
        this.restaurantService = restaurantService;
        this.guestService = guestService;
        this.notifyTables = new core_1.EventEmitter();
        this.tables = [];
        this.regions = [];
        this.ret = { startDate: null, startTime: null, duration: null, tables: [], restaurant: null };
    }
    GuestTablesComponent.prototype.ngOnInit = function () {
        this.canvas = new fabric.Canvas('guestTablesCanvas');
        this.canvas.setDimensions({ width: 900, height: 900 });
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1; //January is 0!
        var yyyy = today.getFullYear();
        this.myDatePickerOptions =
            {
                dateFormat: "dd/mm/yyyy",
                sunHighlight: true,
                satHighlight: true,
                markCurrentDay: true,
                disableUntil: { year: yyyy, month: mm, day: dd },
                editableDateField: false,
                openSelectorOnInputClick: true,
                disableSince: { year: yyyy + 1, month: mm, day: dd }
            };
    };
    GuestTablesComponent.prototype.onDateChanged = function (event) {
        this.ret.startDate = event.formatted;
        this.notifyTables.emit(this.ret);
    };
    GuestTablesComponent.prototype.ngOnChanges = function (changes) {
        if (changes['restaurant'] != null) {
            this.restaurant = changes['restaurant'].currentValue;
            this.ret.restaurant = this.restaurant;
            this.ret.tables = [];
        }
        if (this.canvas != null)
            this.canvas.clear();
    };
    GuestTablesComponent.prototype.initTables = function () {
        this.getTables();
        this.canvas.clear();
        var _loop_1 = function (i) {
            var rect = new fabric.Rect({
                id: this_1.tables[i].id,
                width: 50,
                height: 50,
                left: this_1.tables[i].left,
                top: this_1.tables[i].top,
                fill: this_1.tables[i].regionColor,
                stroke: '#ff0017',
                strokeWidth: 0,
                lockMovementX: true,
                lockMovementY: true,
                lockUniScaling: true,
                lockRotation: true,
                hasControls: false,
                selected: false
            });
            if (this_1.tables[i].occupied) {
                rect.stroke = 'black';
                rect.fill = 'gray';
                rect.strokeWidth = 5;
            }
            var text = new fabric.Text(String(this_1.tables[i].seats), {
                fontFamily: 'Comic Sans',
                fontSize: 18,
                left: this_1.tables[i].left,
                top: this_1.tables[i].top,
                lockMovementX: true,
                lockMovementY: true,
                lockUniScaling: true,
                lockRotation: true,
                hasControls: false
            });
            if (!this_1.tables[i].occupied) {
                var self_1 = this_1;
                rect.on('mousedown', function (e) {
                    e.target.strokeWidth = 5 - e.target.strokeWidth;
                    if (e.target.strokeWidth == 5) {
                        for (var j = 0; j < self_1.tables.length; j++) {
                            if (e.target.id == self_1.tables[j].id) {
                                self_1.ret.tables.push(self_1.tables[j]);
                                break;
                            }
                        }
                    }
                    else {
                        for (var j = 0; j < self_1.ret.tables.length; j++) {
                            if (e.target.id == self_1.ret.tables[j].id) {
                                self_1.ret.tables.splice(j, 1);
                                break;
                            }
                        }
                    }
                    self_1.notifyTables.emit(self_1.ret);
                });
            }
            this_1.canvas.add(rect);
            this_1.canvas.add(text);
        };
        var this_1 = this;
        for (var i = 0; i < this.tables.length; i++) {
            _loop_1(i);
        }
    };
    GuestTablesComponent.prototype.getTables = function () {
        var _this = this;
        this.guestService.getTables(this.ret).subscribe(function (data) { return _this.tables = data; }, function (error) { return alert(error); });
    };
    GuestTablesComponent.prototype.getRegions = function () {
        var _this = this;
        this.restaurantService.getRegions(this.restaurant.id).subscribe(function (data) { return _this.regions = data; }, function (error) { return alert(error); });
    };
    return GuestTablesComponent;
}());
__decorate([
    core_1.Input()
], GuestTablesComponent.prototype, "restaurant", void 0);
__decorate([
    core_1.Output()
], GuestTablesComponent.prototype, "notifyTables", void 0);
GuestTablesComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'guestTables',
        templateUrl: 'guestTables.component.html',
        styleUrls: ['guestTables.component.css', '../../style/formStyle.css'],
        providers: [restaurants_service_1.RestaurantService]
    })
], GuestTablesComponent);
exports.GuestTablesComponent = GuestTablesComponent;
