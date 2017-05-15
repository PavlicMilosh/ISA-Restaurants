import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddWorkScheduleComponent } from './addWorkSchedule.component';

describe('AddWorkScheduleComponent', () => {
  let component: AddWorkScheduleComponent;
  let fixture: ComponentFixture<AddWorkScheduleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddWorkScheduleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddWorkScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
