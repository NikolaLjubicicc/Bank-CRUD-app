import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KorisnikUslugeComponent } from './korisnik-usluge.component';

describe('KorisnikUslugeComponent', () => {
  let component: KorisnikUslugeComponent;
  let fixture: ComponentFixture<KorisnikUslugeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [KorisnikUslugeComponent]
    });
    fixture = TestBed.createComponent(KorisnikUslugeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
