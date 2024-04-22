import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilijalaComponent } from './filijala.component';

describe('FilijalaComponent', () => {
  let component: FilijalaComponent;
  let fixture: ComponentFixture<FilijalaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FilijalaComponent]
    });
    fixture = TestBed.createComponent(FilijalaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
