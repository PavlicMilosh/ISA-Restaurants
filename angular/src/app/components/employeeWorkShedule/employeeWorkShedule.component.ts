/**
 * Created by djuro on 5/15/2017.
 */
import { Component, OnInit } from '@angular/core';
import { UserService } from "../../services/users.service";
import * as $ from 'jquery';

@Component({
  selector: 'employeeWorkShedule',
  templateUrl: './employeeWorkShedule.component.html',
  styleUrls: ['./employeeWorkShedule.component.css'],
  providers: [UserService]
})
export class EmployeeWorkShedule implements OnInit
{

  events:SheduleElement[]=[];

  date1:Date=new Date(2017,5,15,9,0);
  date2:Date=new Date(2017,5,15,16,0);
  date3:Date=new Date(2017,5,16,16,0);
  date4:Date=new Date(2017,5,16,22,0);
  date5:Date=new Date(2017,5,17,9,0);
  date6:Date=new Date(2017,5,17,16,0);


  start:string;
  end:string;

  currentStart:string;
  currentEnd:string;

  month:string;
  day:string;
  hour:string;
  minutes:string;


  name:string;

  currentDay:number;


  items:WorkShedule[];
  otherItems:WorkShedule[];
  calendarOptions = {
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
    events: [
    ]
  };

  constructor(private userService: UserService) {

  }

  ngOnInit() {
    this.userService.getAllSchedule().subscribe
    (
      (data: WorkShedule[]) => this.otherItems = data,
      error => alert(error),
      () => this.getOthersSchedules()
    );
  }

  getOthersSchedules()
  {
    this.userService.getSchedule().subscribe
    (
      (data: WorkShedule[]) => this.items = data,
      error => alert(error),
      () => this.initMySchedule()
    );
  }

  initMySchedule()
  {
    let newEvents=[];
    var o=this.items.length;
    //this.addOther();
    for(var i=0; i<this.items.length+this.otherItems.length; i++)
    {
      if(i<o)
      {
        this.currentStart=this.items[i].startTime;
        this.currentEnd=this.items[i].endTime;
        this.day=this.items[i].day.toString();
      }
      else
      {
        this.currentStart=this.otherItems[i-o].startTime;
        this.currentEnd=this.otherItems[i-o].endTime;
        this.day=this.otherItems[i-o].day.toString();
      }

      const [first, second] = this.currentStart.split(' ');
      const [hh,mm,ss] = second.split(':')
      this.getNumberOfDay(this.day)
      this.start='2017-05-0'+this.currentDay.toString()+'T'+hh+':'+mm;


      const [first1, second1] = this.currentEnd.split(' ');
      const [hh1,mm1,ss1] = second1.split(':')
      this.end='2017-05-0'+this.currentDay.toString()+'T'+hh1+':'+mm1;

      if(i<o)
      {
        newEvents.push({id:i,title:'work',start:this.start, end:this.end});
      }
      else
      {
        this.name=this.otherItems[i-o].worker.firstName+' '+this.otherItems[i-o].worker.lastName;
        newEvents.push({id:i,title:this.name,start:this.start, end:this.end, color:'green'});
      }
    }

    this.calendarOptions.events = newEvents;
    $('#myCalendar').fullCalendar('renderEvents', newEvents, true);
    $('#myCalendar').fullCalendar('refresh');
  }


  addOther()
  {
    for(var i=0;i<this.otherItems.length;i++)
    {
      this.items.push(this.otherItems[i]);
    }

  }


  getNumberOfDay(dayName:string)
  {
    this.currentDay=0;
    if(dayName=="MONDAY") this.currentDay=1;
    if(dayName=="TUESDAY") this.currentDay=2;
    if(dayName=="WEDNESDAY") this.currentDay=3;
    if(dayName=="THURSDAY") this.currentDay=4;
    if(dayName=="FRIDAY") this.currentDay=5;
    if(dayName=="SATURDAY") this.currentDay=6;
    if(dayName=="SUNDAY") this.currentDay=7;
  }
}


interface UserDTO
{
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
}

/*
 interface WorkShedule
 {
 id:number;
 startTime:Date;
 endTime:Date;
 day:number;
 worker:any;
 restaurant:any;
 }
 */

interface WorkShedule
{
  id:number;
  startTime:string;
  endTime:string;
  day:number;
  worker:User;
}

interface Restaurant
{
  id : number;
  name : string;
  description : string;
}

interface SheduleElement
{
  id: number;
  title: string;
  start: Date;
  end: Date;
}

interface Schedule
{
  id: number;
  startTime: string;
  endTime: string;
  day: number;
}

interface User
{
  firstName:string;
  lastName:string;
}





