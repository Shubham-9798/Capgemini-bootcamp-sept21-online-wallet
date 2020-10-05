import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayPassbookComponent } from './display-passbook.component';

describe('DisplayPassbookComponent', () => {
  let component: DisplayPassbookComponent;
  let fixture: ComponentFixture<DisplayPassbookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayPassbookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayPassbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
