import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterRMComponent } from './registerRM.component';

describe('RegisterRMComponent', () => {
  let component: RegisterRMComponent;
  let fixture: ComponentFixture<RegisterRMComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterRMComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterRMComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
