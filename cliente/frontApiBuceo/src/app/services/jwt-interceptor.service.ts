import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';

export const JwtInterceptor: HttpInterceptorFn = (req, next) => {
  
  const token = localStorage.getItem('token'); // O de donde obtengas el token

  if (token) {
    const clonedReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(clonedReq);
  }

  return next(req);
};

