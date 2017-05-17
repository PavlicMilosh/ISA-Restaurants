import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestRestaurantsPageComponent } from './guestRestaurantsPage.component';

describe('GuestRestaurantsPageComponent', () => {
  let component: GuestRestaurantsPageComponent;
  let fixture: ComponentFixture<GuestRestaurantsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuestRestaurantsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuestRestaurantsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
