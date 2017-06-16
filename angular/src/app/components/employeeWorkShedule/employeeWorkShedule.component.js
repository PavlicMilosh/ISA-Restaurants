"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Created by djuro on 5/15/2017.
 */
var core_1 = require("@angular/core");
var users_service_1 = require("../../services/users.service");
var $ = require("jquery");
var EmployeeWorkShedule = (function () {
    function EmployeeWorkShedule(userService) {
        /*
         this.userService.getSchedule().subscribe
         (
         (data:WorkShedule[]) => this.items = data,
         error => alert(error)
         );
        */
        this.userService = userService;
        this.events = [];
        this.date1 = new Date(2017, 5, 15, 9, 0);
        this.date2 = new Date(2017, 5, 15, 16, 0);
        this.date3 = new Date(2017, 5, 16, 16, 0);
        this.date4 = new Date(2017, 5, 16, 22, 0);
        this.date5 = new Date(2017, 5, 17, 9, 0);
        this.date6 = new Date(2017, 5, 17, 16, 0);
        this.workShedule1 = { id: null, startTime: this.date1, endTime: this.date2, day: 1, restaurant: null, worker: null };
        this.workShedule2 = { id: null, startTime: this.date3, endTime: this.date4, day: 2, restaurant: null, worker: null };
        this.workShedule3 = { id: null, startTime: this.date5, endTime: this.date6, day: 3, restaurant: null, worker: null };
        this.items = [this.workShedule1, this.workShedule2, this.workShedule3];
        this.calendarOptions = {
            height: 'auto',
            contentHeight: 'auto',
            fixedWeekCount: false,
            editable: false,
            eventLimit: true,
            defaultView: 'agendaWeek',
            slotDuration: '01:00:00',
            firstDay: 1,
            header: {
                left: '',
                right: ''
            },
            columnFormat: 'dddd',
            defaultDate: '2017-05-03',
            events: []
        };
    }
    EmployeeWorkShedule.prototype.ngOnInit = function () {
        var newEvents = [];
        for (var i = 1; i < this.items.length; i++) {
            if (this.items[i].startTime.getUTCHours() < 10)
                this.hour = '0' + this.items[i].startTime.getUTCHours().toString();
            else
                this.hour = this.items[i].startTime.getUTCHours().toString();
            if (this.items[i].startTime.getUTCMinutes() < 10)
                this.minutes = '0' + this.items[i].startTime.getUTCMinutes().toString();
            else
                this.minutes = this.items[i].startTime.getUTCMinutes().toString();
            this.start = '2017-05-0' + (this.items[i].day - 1) + 'T' + this.hour + ':' + this.minutes;
            if (this.items[i].endTime.getUTCHours() < 10)
                this.hour = '0' + this.items[i].endTime.getUTCHours().toString();
            else
                this.hour = this.items[i].endTime.getUTCHours().toString();
            if (this.items[i].endTime.getUTCMinutes() < 10)
                this.minutes = '0' + this.items[i].endTime.getUTCMinutes().toString();
            else
                this.minutes = this.items[i].endTime.getUTCMinutes().toString();
            this.end = '2017-05-0' + (this.items[i].day - 1) + 'T' + this.hour + ':' + this.minutes;
            console.log(this.start);
            console.log(this.end);
            newEvents.push({ id: i, title: 'work', start: this.start,
                end: this.end });
        }
        this.calendarOptions.events = newEvents;
        $('#myCalendar').fullCalendar('renderEvents', newEvents, true);
    };
    return EmployeeWorkShedule;
}());
EmployeeWorkShedule = __decorate([
    core_1.Component({
        selector: 'employeeWorkShedule',
        templateUrl: './employeeWorkShedule.component.html',
        styleUrls: ['./employeeWorkShedule.component.css'],
        providers: [users_service_1.UserService]
    })
], EmployeeWorkShedule);
exports.EmployeeWorkShedule = EmployeeWorkShedule;
