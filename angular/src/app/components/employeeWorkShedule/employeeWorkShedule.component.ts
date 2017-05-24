/**
 * Created by djuro on 5/15/2017.
 */
import { Component } from '@angular/core';
import { UserService } from "../../services/users.service";
import * as $ from 'jquery';

@Component({
  selector: 'employeeWorkShedule',
  templateUrl: './employeeWorkShedule.component.html',
  styleUrls: ['./employeeWorkShedule.component.css'],
  providers: [UserService]
})
export class EmployeeWorkShedule{

  events:SheduleElement[]=[];

  date1:Date=new Date(2017,5,15,9,0);
  date2:Date=new Date(2017,5,15,16,0);
  date3:Date=new Date(2017,5,16,16,0);
  date4:Date=new Date(2017,5,16,22,0);
  date5:Date=new Date(2017,5,17,9,0);
  date6:Date=new Date(2017,5,17,16,0);


  start:string;
  end:string;

  month:string;
  day:string;
  hour:string;
  minutes:string;


  workShedule1:WorkShedule={startTime:this.date1,endTime:this.date2,day:1,restaurant:null,worker:null};
  workShedule2:WorkShedule={startTime:this.date3,endTime:this.date4,day:2,restaurant:null,worker:null};
  workShedule3:WorkShedule={startTime:this.date5,endTime:this.date6,day:3,restaurant:null,worker:null};
  items:WorkShedule[]=[this.workShedule1, this.workShedule2, this.workShedule3];


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

    /*
     this.userService.getSchedule(1,"waiter").subscribe
     (
     (data:WorkShedule[]) => this.items = data,
     error => alert(error)
     );
    */
  }

  ngOnInit()
  {
    let newEvents=[];
    for(var i=1; i<this.items.length; i++)
    {
      if(this.items[i].startTime.getUTCHours()<10) this.hour='0'+this.items[i].startTime.getUTCHours().toString(); else this.hour=this.items[i].startTime.getUTCHours().toString();

      if(this.items[i].startTime.getUTCMinutes()<10) this.minutes='0'+this.items[i].startTime.getUTCMinutes().toString(); else this.minutes=this.items[i].startTime.getUTCMinutes().toString();

      this.start='2017-05-0'+(this.items[i].day-1)+'T'+this.hour+':'+this.minutes;

      if(this.items[i].endTime.getUTCHours()<10) this.hour='0'+this.items[i].endTime.getUTCHours().toString(); else this.hour=this.items[i].endTime.getUTCHours().toString();

      if(this.items[i].endTime.getUTCMinutes()<10) this.minutes='0'+this.items[i].endTime.getUTCMinutes().toString(); else this.minutes=this.items[i].endTime.getUTCMinutes().toString();

      this.end='2017-05-0'+(this.items[i].day-1)+'T'+this.hour+':'+this.minutes;

      console.log(this.start);
      console.log(this.end);

      newEvents.push({id:i,title:'work',start:this.start,
        end:this.end});
    }

    this.calendarOptions.events = newEvents;
    $('#myCalendar').fullCalendar('renderEvents', newEvents, true);
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

interface WorkShedule
{
  startTime:Date;
  endTime:Date;
  day:number;
  worker:UserDTO;
  restaurant:Restaurant;
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




