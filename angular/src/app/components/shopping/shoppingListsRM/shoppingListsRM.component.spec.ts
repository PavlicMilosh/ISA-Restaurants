import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingListsRMComponent } from './shoppingListsRM.component';

describe('ShoppingListsRMComponent', () => {
  let component: ShoppingListsRMComponent;
  let fixture: ComponentFixture<ShoppingListsRMComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShoppingListsRMComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingListsRMComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
