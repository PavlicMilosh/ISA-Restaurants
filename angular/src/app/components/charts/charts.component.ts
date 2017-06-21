import {Component, OnInit, Input} from '@angular/core';
import {Subject} from "rxjs";

@Component({
  selector: 'charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent implements OnInit
{
  @Input() daysSubject: Subject<any>;
  @Input() weekSubject: Subject<any>;

  //bar chart (week or more)
  public weeksOptions:any = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  public weeksLabels:string[] = ['Mon', 'Tue', '...', '...', '...', '11.3', '12.3'];
  public weeksType:string = 'bar';
  public weeksLegend:boolean = true;
  public weeksData:any[] = [
    {data: [65, 59, 80, 81, 56, 55, 40], label: 'Visits'}
  ];
  private myDatePickerOptions: any;
  private startDate: Object = { date: { year: 2018, month: 10, day: 9 } };

  //line chart (days)
  public daysData:Array<any> = [
    {data: [65, 59, 80, 81, 56, 55, 40, 65, 59, 80, 81, 56, 55, 40, 65, 59, 80, 81, 56, 55, 40, 35, 45, 80], label: 'Mon'},
    {data: [28, 48, 40, 19, 86, 27, 90, 28, 48, 40, 19, 86, 27, 90, 28, 48, 40, 19, 86, 27, 90, 10, 20, 30], label: 'Tue'},
    {data: [18, 48, 77, 9, 100, 27, 40, 18, 48, 77, 9, 100, 27, 40, 18, 48, 77, 9, 100, 27, 40, 5, 5, 5], label: 'Wed'}
  ];
  public daysLabels:Array<any> = ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00',
    '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00'];
  public daysOptions:any = {
    responsive: true
  };
  public daysColors:Array<any> = [
    { // dark blue
      backgroundColor: 'rgba(4, 0, 255, 0.2)',
      borderColor: 'rgba(4, 0, 255, 1)',
      pointBackgroundColor: 'rgba(4, 0, 255, 1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(4, 0, 255, 0.8)'
    },
    { // red
      backgroundColor: 'rgba(255, 0, 0, 0.2)',
      borderColor: 'rgba(255, 0, 0, 1)',
      pointBackgroundColor: 'rgba(255, 0, 0, 1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(255, 0, 0, 1)'
    },
    { // light blue
      backgroundColor: 'rgba(0, 250, 255, 0.2)',
      borderColor: 'rgba(0, 250, 255, 1)',
      pointBackgroundColor: 'rgba(0, 250, 255, 1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(0, 250, 255, 0.8)'
    },
    { // green
      backgroundColor: 'rgba(1, 119, 0, 0.2)',
      borderColor: 'rgba(1, 119, 0, 1)',
      pointBackgroundColor: 'rgba(1, 119, 0, 1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(1, 119, 0, 1)'
    },
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // orange
      backgroundColor: 'rgba(255, 119, 0, 0.2)',
      borderColor: 'rgba(255, 119, 0, 1)',
      pointBackgroundColor: 'rgba(255, 119, 0, 1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(255, 119, 0, 0.8)'
    },
    { // yellow
      backgroundColor: 'rgba(255, 255, 0, 0.2)',
      borderColor: 'rgba(255, 255, 0, 1)',
      pointBackgroundColor: 'rgba(255, 255, 0, 1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(255, 255, 0, 0.8)'
    }
  ];
  public daysLegend:boolean = true;
  public daysType:string = 'line';


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
    }
  }

}
