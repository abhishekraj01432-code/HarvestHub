import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CropPlan } from './crop-plan';

describe('CropPlan', () => {
  let component: CropPlan;
  let fixture: ComponentFixture<CropPlan>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CropPlan],
    }).compileComponents();

    fixture = TestBed.createComponent(CropPlan);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
