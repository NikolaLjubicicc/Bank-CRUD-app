import { TestBed } from '@angular/core/testing';

import { FilijalaService } from './filijala.service';

describe('FilijalaService', () => {
  let service: FilijalaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FilijalaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
