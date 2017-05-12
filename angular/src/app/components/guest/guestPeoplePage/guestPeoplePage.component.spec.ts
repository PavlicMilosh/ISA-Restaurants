import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestRequestPageComponent } from './guestPeoplePage.component';

describe('GuestFriendsPageComponent', () => {
  let component: GuestRequestPageComponent;
  let fixture: ComponentFixture<GuestRequestPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuestRequestPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuestRequestPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
