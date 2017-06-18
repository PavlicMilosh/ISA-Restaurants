import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingListProviderComponent } from './shoppingListProvider.component';

describe('ShoppingListProviderComponent', () => {
  let component: ShoppingListProviderComponent;
  let fixture: ComponentFixture<ShoppingListProviderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShoppingListProviderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingListProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
