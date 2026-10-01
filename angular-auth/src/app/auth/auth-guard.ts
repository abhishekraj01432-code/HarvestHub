import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = () => {

  const router = inject(Router);

  const isLoggedIn = localStorage.getItem('isLoggedIn');

  console.log('Login Status:', isLoggedIn);

  if (isLoggedIn === 'true') {
    return true;
  }

  router.navigate(['/login']);

  return false;
};
