import { TestBed } from '@angular/core/testing';

import { BankaService } from './banka.service';

describe('BankaService', () => {
  let service: BankaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BankaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
