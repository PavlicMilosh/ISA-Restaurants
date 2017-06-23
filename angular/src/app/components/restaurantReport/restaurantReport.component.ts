import { Component, OnInit } from '@angular/core';
import { Subject } from "rxjs";
import { CompleterData, CompleterService } from "ng2-completer";

@Component({
  selector: 'app-restaurant-report',
  templateUrl: './restaurantReport.component.html',
  styleUrls: ['./restaurantReport.component.css']
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
  private incomeDate;
  private visitsDate;
  private waitersDate;
  private waitersName;
  private cooksName;
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

  protected dataService: CompleterData;

  constructor(private completerService: CompleterService) {
    this.cooks = [];
    this.waiters =
      [
        {id: 1, firstName: "Petar", lastName: "Peric", meanMark: 8},
        {id: 1, firstName: "Joca", lastName: "Jovic", meanMark: 8},
        {id: 1, firstName: "Iva", lastName: "Ivanovic", meanMark: 8},
        {id: 1, firstName: "Nikola", lastName: "Nikolic", meanMark: 8}
      ];
    this.dishes = [];

    this.dataService = completerService.local(this.waiters, 'firstName,lastName', 'firstName,lastName');
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


  getIncomeData() {

  }


  getVisitsData() {

  }


  getWaitersData() {

  }


  selectWaiter()
  {
    let partsOfStr = this.selectedWaiterStr.split(' ');
    let firstName = partsOfStr[0];
    let lastName = partsOfStr[1];
    for (let w of this.waiters)
      if (w.firstName == firstName && w.lastName == lastName)
        this.selectedWaiter = w;
    console.log(this.selectedWaiter);
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
