import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';
import { importProvidersFrom } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RegistroService } from './app/services/registro.service'; // Importa el servicio
import { JwtInterceptorService } from './app/services/jwt-interceptor.service';
import { ErrorInterceptorService } from './app/services/error-interceptor.service';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    importProvidersFrom(FormsModule, HttpClientModule), // Asegúrate de importar HttpClientModule
    RegistroService, // Proveedor del servicio
    JwtInterceptorService,
    ErrorInterceptorService
  ],
}).catch(err => console.error(err));