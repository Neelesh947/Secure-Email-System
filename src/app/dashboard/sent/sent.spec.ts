import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Sent } from './sent';

describe('Sent', () => {
  let component: Sent;
  let fixture: ComponentFixture<Sent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Sent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Sent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
