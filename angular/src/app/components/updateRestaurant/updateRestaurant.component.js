"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var users_service_1 = require("../../services/users.service");
var restaurants_service_1 = require("../../services/restaurants.service");
require("fabric");
var UpdateRestaurantComponent = (function () {
    function UpdateRestaurantComponent(restaurantService) {
        this.restaurantService = restaurantService;
        this.restaurant =
            {
                id: null,
                name: "",
                description: "",
                dishes: [],
                drinks: [],
                tables: [],
                managers: [],
                regions: []
            };
        this.editingRegion =
            {
                id: null,
                name: "",
                tables: [],
                color: "#000000"
            };
        this.currentRegion =
            {
                id: null,
                name: "",
                tables: [],
                color: "#000000"
            };
        this.newDish();
        this.newDrink();
    }
    UpdateRestaurantComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.canvas = new fabric.Canvas('canvas');
        this.canvas.setDimensions({ width: 900, height: 900 });
        this.restaurantService.getByManager().subscribe(function (data) {
            _this.restaurant = data;
            console.log(_this.restaurant);
            for (var _i = 0, _a = _this.restaurant.regions; _i < _a.length; _i++) {
                var region = _a[_i];
                for (var _b = 0, _c = region.tables; _b < _c.length; _b++) {
                    var table = _c[_b];
                    var text = new fabric.Text(String(table.seats), {
                        fontFamily: 'Comic Sans',
                        fontSize: 18,
                        lockRotation: true
                    });
                    var rect = new fabric.Rect({
                        width: 50,
                        height: 50,
                        fill: region.color,
                        id: table.id
                    });
                    var group = new fabric.Group([rect, text], {
                        left: table.left,
                        top: table.top,
                        region: region,
                        lockRotation: true
                    });
                    _this.canvas.add(group);
                }
            }
        });
    };
    UpdateRestaurantComponent.prototype.addTable = function () {
        var group = { rect: null, text: null };
        var text = new fabric.Text(String(this.seats), {
            fontFamily: 'Comic Sans',
            fontSize: 18,
            lockRotation: true
        });
        var rect = new fabric.Rect({
            fill: this.currentRegion.color,
            width: 50,
            height: 50,
            id: null
        });
        group = new fabric.Group([rect, text], {
            left: 100,
            top: 100,
            region: this.currentRegion,
            lockRotation: true
        });
        this.canvas.add(group);
    };
    UpdateRestaurantComponent.prototype.removeTable = function () {
        var g = this.canvas.getActiveObject();
    };
    UpdateRestaurantComponent.prototype.updateRestaurant = function () {
        var _this = this;
        this.restaurant.tables = [];
        for (var _i = 0, _a = this.restaurant.regions; _i < _a.length; _i++) {
            var region = _a[_i];
            region.tables = [];
        }
        for (var _b = 0, _c = this.canvas.getObjects(); _b < _c.length; _b++) {
            var group = _c[_b];
            console.log(group);
            var t = {
                id: group.id,
                top: group.getTop(),
                left: group.getLeft(),
                angle: 0.0,
                seats: Number(group.item(1).text)
            };
            var region = group.region;
            region.tables.push(t);
            this.restaurant.tables.push(t);
        }
        console.log(this.restaurant);
        this.restaurantService.updateRestaurant(this.restaurant).subscribe(function (data) { return _this.restaurant = data; });
    };
    UpdateRestaurantComponent.prototype.selectRegion = function (region) {
        this.currentRegion = region;
    };
    UpdateRestaurantComponent.prototype.selectDrink = function (drink) {
        this.editingDrink = drink;
    };
    UpdateRestaurantComponent.prototype.selectDish = function (dish) {
        this.editingDish = dish;
    };
    UpdateRestaurantComponent.prototype.newDish = function () {
        this.editingDish =
            {
                id: null,
                name: "",
                description: "",
                price: 0,
            };
    };
    UpdateRestaurantComponent.prototype.newDrink = function () {
        this.editingDrink =
            {
                id: null,
                name: "",
                description: "",
                price: 0,
            };
    };
    UpdateRestaurantComponent.prototype.manageRestaurant = function () {
        this.restaurant.name = this.rnameEditing;
        this.restaurant.description = this.rdescEditing;
    };
    UpdateRestaurantComponent.prototype.manageDrink = function () {
        if (this.editingDrink.id != null) {
            for (var _i = 0, _a = this.restaurant.drinks; _i < _a.length; _i++) {
                var drink = _a[_i];
                if (drink.id == this.editingDrink.id) {
                    drink.name = this.editingDrink.name;
                    drink.description = this.editingDrink.description;
                    drink.price = this.editingDrink.price;
                }
            }
        }
        else {
            this.restaurant.drinks.push(this.editingDrink);
            this.editingDrink =
                {
                    id: null,
                    name: "",
                    description: "",
                    price: 0
                };
        }
    };
    UpdateRestaurantComponent.prototype.manageDish = function () {
        if (this.editingDish.id != null) {
            for (var _i = 0, _a = this.restaurant.dishes; _i < _a.length; _i++) {
                var dish = _a[_i];
                if (dish.id == this.editingDish.id) {
                    dish.name = this.editingDish.name;
                    dish.description = this.editingDish.description;
                    dish.price = this.editingDish.price;
                }
            }
        }
        else {
            this.restaurant.dishes.push(this.editingDish);
        }
        this.editingDish =
            {
                id: null,
                name: "",
                description: "",
                price: 0
            };
    };
    UpdateRestaurantComponent.prototype.addRegion = function () {
        this.restaurant.regions.push(this.editingRegion);
        this.editingRegion = { id: null, name: "", color: "#0000ff", tables: [] };
    };
    return UpdateRestaurantComponent;
}());
UpdateRestaurantComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'updateRestaurant',
        templateUrl: './updateRestaurant.component.html',
        styleUrls: ['./updateRestaurant.component.css', '../style/formStyle.css'],
        providers: [users_service_1.UserService, restaurants_service_1.RestaurantService]
    })
], UpdateRestaurantComponent);
exports.UpdateRestaurantComponent = UpdateRestaurantComponent;
