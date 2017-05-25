import { Component, OnInit } from '@angular/core';
import { UserService } from "../../services/users.service";
import { RestaurantService } from "../../services/restaurants.service";

@Component({
  selector: 'app-add-work-schedule',
  templateUrl: './addWorkSchedule.component.html',
  styleUrls: ['./addWorkSchedule.component.css'],
  providers: [ UserService, RestaurantService ]
})
export class AddWorkScheduleComponent implements OnInit
{
  private workers: Worker[];
  private selectedWorker: Worker;
  private schedule: Schedule;
  private days = ["Every Day", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];

  constructor(private userService: UserService, private restaurantService: RestaurantService)
  {
    this.restaurantService.getWorkersByRMId().subscribe(
      data => this.workers = data
    );
    this.workers = [];
    this.schedule =
    {
      id: null,
      startTime: null,
      endTime: null,
      day: null
    }
  }

  ngOnInit() {
  }

  selectWorker(worker: Worker)
  {
    this.selectedWorker = worker;
  }

  addSchedule()
  {
    console.log(this.schedule);
    this.userService.addSchedule(this.schedule, this.selectedWorker.id)
      .subscribe(
        data => console.log(data)
      );
  }

}

interface Worker
{
  id: number;
  emai: string;
  password: string;
  firstName: string;
  lastName: string;
}

interface Schedule
{
  id: number;
  startTime: string;
  endTime: string;
  day: number;
}
