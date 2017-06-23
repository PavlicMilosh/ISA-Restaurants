import {Component, OnInit, ViewEncapsulation, Output, EventEmitter} from '@angular/core';
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

  // on load data
  private restaurantMark: number;
  private cooks: Cook[];
  private waiters: Waiter[];
  private dishes: Dish[];

  private weeksIncomeData = [];
  private daysIncomeData = [];
  private daysLabels = ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00',
    '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00'];

  // inputs
  private incomeDate: string;
  private visitsDate: string;
  private waitersDate: string;
  private waitersName: string;
  private cooksName: string;
  private dishName: string;

  // outputs
  private incomePerDayData;
  private incomePerHourData;
  private waiterIncomePerDayData;

  private visitsPerDayData;
  private visitsPerHourData;

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


  public line_ChartOptions = {
    title: 'Sales per day',
    curveType: 'function',
    legend: {
      position: 'bottom'
    }
  };


  constructor(private datePipe: DatePipe, private reportService: ReportService)
  {
    this.cooks = [];
    this.waiters =[];
    this.dishes = [];

    this.reportService.getRestaurantMeanMark().subscribe
    (
      data => {this.restaurantMark = data; console.log(data)},
      error => alert(error)
    );

    /*this.reportService.getDishesWithMeanMark().subscribe
    (
      data => {this.dishes = data; console.log(data)},
      error => alert(error)
    );*/

    this.reportService.getWaitersWithMeanMark().subscribe
    (
      data => {this.waiters = data; console.log(data)},
      error => alert(error)
    );

    this.reportService.getCooksWithMeanMark().subscribe
    (
      data => {this.cooks = data; console.log(data)},
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
    console.log(this.incomeDate);
  }

  onVisitsDateChanged(event: IMyDateModel)
  {
    let jsdate = event.jsdate;
    this.visitsDate = this.datePipe.transform(jsdate, "yyyy-MM-dd HH:mm");
    console.log(this.visitsDate);
  }

  onWaiterDateChanged(event: IMyDateModel)
  {
    let jsdate = event.jsdate;
    this.waitersDate = this.datePipe.transform(jsdate, "yyyy-MM-dd HH:mm");
    console.log(this.waitersDate);
  }


  getIncomeData()
  {
    let sendDate = {startDate: this.incomeDate};
    this.reportService.getIncomeData(sendDate).subscribe
    (
      data =>
      {
        /*
        public line_ChartData = [
        ['Year', 'Sales', 'Expenses'],
        ['2004', 1000, 400],
        ['2005', 1170, 460],
        ['2006', 660, 1120],
        ['2007', 1030, 540]];
        */
        this.restaurantProfit = data;

        let input = {time: "", value:0};
        let week = [];
        let day = [];
        // time: value
        week.push(['Day', 'Income']);
        for (let i = 0; i < this.restaurantProfit.length; i++)
        {
          input.time = this.restaurantProfit[i].time;
          input.value += this.restaurantProfit[i].value;
          if ((i+1)%24==0)
          {
            week.push([input.time, input.value]);
            input.value = 0;
          }
        }
        this.incomePerDayData = week;

      },
      error => alert(error)
    );


    //this.outputIncomeData.emit("Pera");


    // this.incomeWeeksDataSubject.emit(weeksData);
    // this.incomeDaysDataSubject.emit(daysData);
    // this.incomeWeeksLabelsSubject.emit(weeksLabels);
    // // this.incomeDaysDataSubject.next(daysData);
    // this.incomeWeeksLabelsSubject.next(weeksLabels);
    // this.incomeWeeksDataSubject.next(weeksData)

  }


  getVisitsData()
  {
    let sendDate = {startDate: this.visitsDate};
    this.reportService.getVisitsData(sendDate).subscribe
    (
      data =>
      {
        this.visits = data;

        let input = {time: "", value:0};
        let week = [];
        let day = [];
        // time: value
        week.push(['Day', 'Visits']);
        for (let i = 0; i < this.visits.length; i++)
        {
          input.time = this.visits[i].time;
          input.value += this.visits[i].value;
          if ((i+1)%24==0)
          {
            week.push([input.time, input.value]);
            input.value = 0;
          }
        }
        this.visitsPerDayData = week;

      },
      error => alert(error)
    );
  }


  getWaitersData()
  {
    let sendDate = {startDate: this.waitersDate};
    this.reportService.getWaitersData(sendDate, this.selectedWaiter.id).subscribe
    (
      data =>
      {
        this.waiterProfit = data;

        let input = {time: "", value:0};
        let week = [];
        let day = [];
        // time: value
        week.push(['Day', 'Visits']);
        for (let i = 0; i < this.waiterProfit.length; i++)
        {
          input.time = this.waiterProfit[i].time;
          input.value += this.waiterProfit[i].value;
          if ((i+1)%24==0)
          {
            week.push([input.time, input.value]);
            input.value = 0;
          }
        }
        this.waiterIncomePerDayData = week;

      },
      error => alert(error)
    );
  }


  getCookData()
  {

  }


  selectWaiter(w: any)
  {
    this.selectedWaiter = w;
  }


  selectCook(c: any)
  {
    this.selectedCook = c;
  }


  selectDish()
  {

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
  time: string;
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

interface DateDTO
{
  startDate: string;
}
