import {Component, OnInit, HostListener, Output, Input, EventEmitter, OnChanges, SimpleChanges} from '@angular/core';
import { GuestService } from "../../../services/guest.service";
import { RestaurantService } from "../../../services/restaurants.service"

import 'fabric';
import {IMyDateModel} from "mydatepicker";

declare let fabric;

@Component({
  moduleId: module.id,
  selector: 'guestTables',
  templateUrl: 'guestTables.component.html',
  styleUrls: ['guestTables.component.css', '../../style/formStyle.css'],
  providers: [RestaurantService]
})


export class GuestTablesComponent implements OnChanges, OnInit {

  @Input() restaurant: Restaurant;
  @Output() notifyTables: EventEmitter<Reservation> = new EventEmitter<Reservation>();
  private ret: Reservation;
  private tables: RestaurantTable[];
  private regions: Region[];
  private canvas;
  private myDatePickerOptions: any;

  constructor(private restaurantService: RestaurantService, private guestService: GuestService)
  {
    this.tables = [];
    this.regions = [];
    this.ret = {startDate: null, startTime: null, duration: null, tables: [], restaurant: null};
  }


  ngOnInit()
  {
    this.canvas = new fabric.Canvas('guestTablesCanvas');
    this.canvas.setDimensions({width:900, height:900});

    let today = new Date();
    let dd = today.getDate();
    let mm = today.getMonth()+1; //January is 0!
    let yyyy = today.getFullYear();

    this.myDatePickerOptions =
      {
        dateFormat: "dd/mm/yyyy",
        sunHighlight: true,
        satHighlight: true,
        markCurrentDay: true,
        disableUntil: {year: yyyy, month: mm, day: dd},
        editableDateField: false,
        openSelectorOnInputClick: true,
        disableSince: {year: yyyy+1, month: mm, day: dd}
      }
  }

  onDateChanged(event: IMyDateModel)
  {
    this.ret.startDate = event.formatted;
    this.notifyTables.emit(this.ret);
  }


  ngOnChanges(changes: SimpleChanges)
  {
    if (changes['restaurant'] != null)
    {
      this.restaurant = changes['restaurant'].currentValue;
      this.ret.restaurant = this.restaurant;
      this.ret.tables = [];
    }

    if (this.canvas != null)
      this.canvas.clear();

  }


  initTables()
  {
    this.getTables();
    this.canvas.clear();
    for (let i = 0; i < this.tables.length; i++)
    {
      let rect = new fabric.Rect(
        {
          id: this.tables[i].id,
          width: 50,
          height: 50,
          left: this.tables[i].left,
          top: this.tables[i].top,
          fill: this.tables[i].regionColor,
          stroke: '#ff0017',
          strokeWidth: 0,
          lockMovementX: true,
          lockMovementY: true,
          lockUniScaling: true,
          lockRotation: true,
          hasControls: false,
          selected: false
        }
      );

      if (this.tables[i].occupied)
      {
        rect.stroke = 'black';
        rect.fill = 'gray';
        rect.strokeWidth = 5;
      }

      let text = new fabric.Text(String(this.tables[i].seats),
        {
          fontFamily: 'Comic Sans',
          fontSize: 18,
          left: this.tables[i].left,
          top: this.tables[i].top,
          lockMovementX: true,
          lockMovementY: true,
          lockUniScaling: true,
          lockRotation: true,
          hasControls: false
        }
      );

      if (!this.tables[i].occupied)
      {
        let self = this;
        rect.on('mousedown', function(e)
        {
          e.target.strokeWidth = 5 - e.target.strokeWidth;

          if (e.target.strokeWidth == 5)
          {
            for (let j = 0; j < self.tables.length; j++)
            {
              if (e.target.id == self.tables[j].id)
              {
                self.ret.tables.push(self.tables[j]);
                break;
              }
            }
          }
          else
          {
            for (let j = 0; j < self.ret.tables.length; j++)
            {
              if (e.target.id == self.ret.tables[j].id)
              {
                self.ret.tables.splice(j, 1);
                break;
              }
            }
          }

          self.notifyTables.emit(self.ret);
        });
      }
      this.canvas.add(rect);
      this.canvas.add(text);


    }
  }


  getTables()
  {
    this.guestService.getTables(this.ret).subscribe
    (
      data => this.tables = data,
      error => alert(error)
    );
  }


  getRegions()
  {
    this.restaurantService.getRegions(this.restaurant.id).subscribe
    (
      data => this.regions = data,
      error => alert(error)
    );
  }
}


interface Restaurant
{
  id: number;
  name: string;
  description: string;
}


interface Reservation
{
  startTime: string;
  duration: number;
  startDate: string;
  restaurant: Restaurant;
  tables: RestaurantTable[];
}


interface RestaurantTable
{
  id: number;
  top: number;
  left: number;
  angle: number;
  occupied: boolean;
  regionId: number;
  regionColor: string;
  seats: number;
}


interface Region
{
  name: string;
  color: string;
  id: number;
}
