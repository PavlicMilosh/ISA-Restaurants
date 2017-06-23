import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Subject } from "rxjs";
import {IMyDateModel} from "mydatepicker";
import {DatePipe} from "@angular/common";
import {ReportService} from "../../services/report.service";

@Component({
  selector: 'app-restaurant-report',
  templateUrl: './restaurantReport.component.html',
  styleUrls: ['./restaurantReport.component.css'],
  providers: [DatePipe, ReportService]
})
export class RestaurantReportComponent implements OnInit {

  private incomeDaysSubject: Subject<any>;
  private incomeWeeksSubject: Subject<any>;
  private visitsDaysSubject: Subject<any>;
  private visitsWeeksSubject: Subject<any>;
  private waiterProfitDaysSubject: Subject<any>;
  private waiterProfitWeeksSubject: Subject<any>;

  // on load data
  private restaurantMark: number;
  private cooks: Cook[];
  private waiters: Waiter[];
  private dishes: Dish[];

  // inputs
  private incomeDate: string;
  private visitsDate: string;
  private waitersDate: string;
  private waitersName: string;
  private cooksName: string;
  private dishName: string;

  // outputs

  private selectedDish: Dish;
  private selectedDishStr: string;

  private selectedWaiter: Waiter;
  private selectedWaiterStr: string;

  private selectedCook: Cook;
  private selectedCookStr: string;

  // on request data
  private visits: Data[];
  private restaurantProfit: Data[];
  private waiterProfit: Data[];

  private myDatePickerOptions: any;


  constructor(private datePipe: DatePipe, private reportService: ReportService)
  {
    this.cooks = [];
    this.waiters =[];
    this.dishes = [];

    this.reportService.getRestaurantMeanMark().subscribe
    (
      data => this.restaurantMark = data,
      error => alert(error)
    );

    this.reportService.getDishesWithMeanMark().subscribe
    (
      data => this.dishes = data,
      error => alert(error)
    );

    this.reportService.getWaitersWithMeanMark().subscribe
    (
      data => this.waiters = data,
      error => alert(error)
    );

    this.reportService.getCooksWithMeanMark().subscribe
    (
      data => this.cooks = data,
      error => alert(error)
    );
  }


  ngOnInit() {

    this.myDatePickerOptions =
      {
        dateFormat: "dd/mm/yyyy",
        sunHighlight: true,
        satHighlight: true,
        markCurrentDay: true,
        editableDateField: false,
        openSelectorOnInputClick: true,
      };
  }


  onIncomeDateChanged(event: IMyDateModel)
  {
    let jsdate = event.jsdate;
    this.incomeDate = this.datePipe.transform(jsdate, "yyyy-MM-dd HH:mm");
  }

  onVisitsDateChanged(event: IMyDateModel)
  {
    let jsdate = event.jsdate;
    this.visitsDate = this.datePipe.transform(jsdate, "yyyy-MM-dd HH:mm");
  }

  onWaiterDateChanged(event: IMyDateModel)
  {
    let jsdate = event.jsdate;
    this.waitersDate = this.datePipe.transform(jsdate, "yyyy-MM-dd HH:mm");
  }


  getIncomeData()
  {
    this.reportService.getIncomeData(this.incomeDate).subscribe
    (
      data =>
      {
        console.log(data);
      },
      error => alert(error)

    );
  }


  getVisitsData()
  {
    this.reportService.getVisitsData(this.visitsDate).subscribe
    (
      data =>
      {
        console.log(data);
      },
      error => alert(error)
    );
  }


  getWaitersData()
  {
    this.reportService.getWaitersData(this.waitersDate, this.selectedWaiter.id).subscribe
    (
      data =>
      {
        console.log(data);
      },
      error => alert(error)
    );
  }


  selectWaiter()
  {
  }


  selectCook() {

  }


  selectDish() {

  }

  getReportData() {
    this.restaurantMark = 5;
    this.cooks = [];
    this.waiters = [];
    this.dishes = [];
  }
}
//actualDate.toDateString() === dateToCheck.toDateString()


interface ReportData
{
  restaurantId: number;
  restaurantMeanMark: number;
  waiters: Waiter[];
  dishes: Dish[];
  cooks: Cook[];
}


// koristi se za restaurant visits, income i waiter data, day je neki dan (ex. 12/03/2012)
// a value moze biti broj poseta, prihod za restoran ili waiter-a
interface Data
{
  day: Date;
  value: number;
}


interface Waiter
{
  id: number;
  firstName: string;
  lastName: string;
  meanMark: number;
}


interface Dish
{
  id: number;
  name: string;
  description: string;
  meanMark: number;
  cooksMark: number;
}


interface Cook
{
  id: number;
  firstName: string;
  lastName: string;
  meanMark: number;
  dishes: Dish[];
}
