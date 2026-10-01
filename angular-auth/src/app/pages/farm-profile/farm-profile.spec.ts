import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmProfile } from './farm-profile';

describe('FarmProfile', () => {
  let component: FarmProfile;
  let fixture: ComponentFixture<FarmProfile>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FarmProfile],
    }).compileComponents();

    fixture = TestBed.createComponent(FarmProfile);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
