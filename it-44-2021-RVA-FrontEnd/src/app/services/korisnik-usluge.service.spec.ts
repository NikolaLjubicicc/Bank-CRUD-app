import { TestBed } from '@angular/core/testing';

import { KorisnikUslugeService } from './korisnik-usluge.service';

describe('KorisnikUslugeService', () => {
  let service: KorisnikUslugeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KorisnikUslugeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
