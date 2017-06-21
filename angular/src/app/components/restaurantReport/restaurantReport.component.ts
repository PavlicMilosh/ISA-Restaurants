import { Component, OnInit } from '@angular/core';
import {Subject} from "rxjs";

@Component({
  selector: 'app-restaurant-report',
  templateUrl: './restaurantReport.component.html',
  styleUrls: ['./restaurantReport.component.css']
})
export class RestaurantReportComponent implements OnInit {

  private daysSubject: Subject<any>;
  private weeksSubject: Subject<any>;
  private myDatePickerOptions: any;

  constructor() { }

  ngOnInit()
  {
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

  load()
  {

  }

}
