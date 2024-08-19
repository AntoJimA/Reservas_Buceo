import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';

export const JwtInterceptor: HttpInterceptorFn = (req, next) => {
  
  const token = localStorage.getItem('token');
  
  // Excluir la solicitud de login de la inyección del token
  if (req.url.includes('/auth/login')) {
    return next(req);
  }

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
