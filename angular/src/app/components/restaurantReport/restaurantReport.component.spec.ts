import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantReportComponent } from './restaurantReport.component';

describe('RestaurantReportComponent', () => {
  let component: RestaurantReportComponent;
  let fixture: ComponentFixture<RestaurantReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RestaurantReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
