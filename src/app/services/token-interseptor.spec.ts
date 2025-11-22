import { TestBed } from '@angular/core/testing';

import { TokenInterseptor } from './token-interseptor';

describe('TokenInterseptor', () => {
  let service: TokenInterseptor;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TokenInterseptor);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
