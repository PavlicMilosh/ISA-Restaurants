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
require("fabric");
var GuestReservationSummaryComponent = (function () {
    function GuestReservationSummaryComponent(restaurantService) {
        this.restaurantService = restaurantService;
        this.reservation = { startTime: "", startDate: "", duration: 0,
            tables: [], invites: [], dishOrders: [], drinkOrders: [],
            restaurant: {
                id: null, name: null, description: null, dishes: [], drinks: []
            } };
        this.dishOrdersTotal = 0;
        this.drinkOrdersTotal = 0;
    }
    GuestReservationSummaryComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.canvas = new fabric.Canvas('guestReservationSummaryCanvas');
        this.canvas.setDimensions({ width: 900, height: 900 });
        this.parentSubject.subscribe(function (event) {
            _this.reservation = event;
            if (_this.canvas != null)
                _this.canvas.clear();
            _this.getTables();
            _this.initTables();
        });
    };
    /*ngOnChanges(c: SimpleChanges)
    {
      this.reservation = c['reservation'].currentValue;
  
      for (let d of this.reservation.drinkOrders)
        this.drinkOrdersTotal += d.price * d.quantity;
  
      for (let d of this.reservation.dishOrders)
        this.dishOrdersTotal += d.price * d.quantity;
  
      if (this.canvas != null)
        this.canvas.clear();
  
      this.getTables();
      this.initTables();
    }*/
    GuestReservationSummaryComponent.prototype.initTables = function () {
        if (this.allTables != null) {
            for (var i = 0; i < this.allTables.length; i++) {
                var rect = new fabric.Rect({
                    id: this.allTables[i].id,
                    width: 50,
                    height: 50,
                    left: this.allTables[i].left,
                    top: this.allTables[i].top,
                    fill: 'gray',
                    opacity: 0.5,
                    strokeWidth: 0,
                    lockMovementX: true,
                    lockMovementY: true,
                    lockUniScaling: true,
                    lockRotation: true,
                    hasControls: false,
                    selected: false
                });
                for (var j = 0; j < this.reservation.tables.length; j++) {
                    if (this.reservation.tables[j].id == this.allTables[i].id) {
                        console.log(this.allTables[i]);
                        rect.fill = this.allTables[i].regionColor;
                        rect.opacity = 1;
                        break;
                    }
                }
                var text = new fabric.Text(String(this.allTables[i].seats), {
                    fontFamily: 'Comic Sans',
                    fontSize: 18,
                    left: this.allTables[i].left,
                    top: this.allTables[i].top,
                    lockMovementX: true,
                    lockMovementY: true,
                    lockUniScaling: true,
                    lockRotation: true,
                    hasControls: false
                });
                this.canvas.add(rect);
                this.canvas.add(text);
            }
        }
    };
    GuestReservationSummaryComponent.prototype.getTables = function () {
        var _this = this;
        if (this.reservation.restaurant.id != null) {
            this.restaurantService.getTables(this.reservation.restaurant.id).subscribe(function (data) { _this.allTables = data; console.log(_this.allTables); }, function (error) { return alert(error); });
        }
    };
    return GuestReservationSummaryComponent;
}());
__decorate([
    core_1.Input()
], GuestReservationSummaryComponent.prototype, "parentSubject", void 0);
GuestReservationSummaryComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'guestReservationSummary',
        templateUrl: 'guestReservationSummary.component.html',
        styleUrls: ['guestReservationSummary.component.css', '../../style/tableStyle.css'],
        providers: [guest_service_1.GuestService]
    })
], GuestReservationSummaryComponent);
exports.GuestReservationSummaryComponent = GuestReservationSummaryComponent;
