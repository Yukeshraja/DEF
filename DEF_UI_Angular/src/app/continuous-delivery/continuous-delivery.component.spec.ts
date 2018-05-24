import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContinuousDeliveryComponent } from './continuous-delivery.component';

describe('ContinuousDeliveryComponent', () => {
  let component: ContinuousDeliveryComponent;
  let fixture: ComponentFixture<ContinuousDeliveryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContinuousDeliveryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContinuousDeliveryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
