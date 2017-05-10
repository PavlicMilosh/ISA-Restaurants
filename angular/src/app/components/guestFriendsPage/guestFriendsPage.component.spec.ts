import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestFriendsPageComponent } from './guestFriendsPage.component';

describe('GuestFriendsPageComponent', () => {
  let component: GuestFriendsPageComponent;
  let fixture: ComponentFixture<GuestFriendsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuestFriendsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuestFriendsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
