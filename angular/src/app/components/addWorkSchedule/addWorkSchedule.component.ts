import {Component, OnInit} from '@angular/core';
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
  private regions: Region[];

  constructor(private userService: UserService, private restaurantService: RestaurantService)
  {
    this.selectedWorker =
    {
      id: -1000,
      email: "",
      password: "",
      firstName: "",
      lastName: "",
      authorities: ""
    }
    this.restaurantService.getWorkersByRMId().subscribe(
      data => this.workers = data
    );
    this.restaurantService.getRegionsByRMId().subscribe(
      data => this.regions = data
    );
    this.workers = [];
    this.schedule =
    {
      id: null,
      startTime: null,
      endTime: null,
      day: null,
      regionId: null
    }
  }

  ngOnInit() {
  }

  selectWorker(worker: Worker)
  {
    this.selectedWorker = worker;
  }

  selectRegion(region: Region)
  {
    this.schedule.regionId = region.id;
  }

  addSchedule()
  {
    console.log(this.schedule);
    if(this.schedule.day == null)
      this.schedule.day = 0;
    this.userService.addSchedule(this.schedule, this.selectedWorker.id)
      .subscribe(
        data => console.log(data)
      );
  }

  onChange(event)
  {
    this.schedule.day = event.target.options.selectedIndex;
    console.log(this.schedule.day);
    console.log(event.target.options.selectedIndex);
  }

}

interface Worker
{
  id: number;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  authorities: string;
}

interface Schedule
{
  id: number;
  startTime: string;
  endTime: string;
  day: number;
  regionId: number;
}

interface Region
{
  name: string;
  color: string;
  id: number;
}
