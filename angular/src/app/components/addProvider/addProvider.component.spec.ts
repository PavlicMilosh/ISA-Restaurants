import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProviderComponent } from './addProvider.component';

describe('AddProviderComponent', () => {
  let component: AddProviderComponent;
  let fixture: ComponentFixture<AddProviderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddProviderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
