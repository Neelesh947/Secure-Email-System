import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { manageUsersGuard } from './manage-users-guard';

describe('manageUsersGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => manageUsersGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
