import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetJobComponent } from './get-job.component';

describe('GetJobComponent', () => {
  let component: GetJobComponent;
  let fixture: ComponentFixture<GetJobComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetJobComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetJobComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
