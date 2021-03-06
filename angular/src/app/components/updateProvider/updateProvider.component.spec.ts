import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateProviderComponent } from './updateProvider.component';

describe('UpdateProviderComponent', () => {
  let component: UpdateProviderComponent;
  let fixture: ComponentFixture<UpdateProviderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateProviderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
