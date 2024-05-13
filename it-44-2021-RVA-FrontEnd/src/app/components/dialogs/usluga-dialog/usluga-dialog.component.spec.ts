import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UslugaDialogComponent } from './usluga-dialog.component';

describe('UslugaDialogComponent', () => {
  let component: UslugaDialogComponent;
  let fixture: ComponentFixture<UslugaDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UslugaDialogComponent]
    });
    fixture = TestBed.createComponent(UslugaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
