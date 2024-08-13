import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';
import { importProvidersFrom } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, provideHttpClient, withInterceptors } from '@angular/common/http';
import { RegistroService } from './app/services/registro.service'; // Importa el servicio
import { MenuService } from './app/services/menu.service';
import { LoginService } from './app/services/login.service';
import { JwtInterceptor } from './app/services/jwt-interceptor.service';
import { ErrorInterceptor } from './app/services/error-interceptor.service';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    importProvidersFrom(FormsModule, HttpClientModule), // Asegúrate de importar HttpClientModule
    RegistroService, // Proveedor del servicio
    provideHttpClient(
      withInterceptors([JwtInterceptor, ErrorInterceptor]) // Asegúrate de importar ErrorInterceptor
    ),
    
    MenuService,
    LoginService
  ],
}).catch(err => console.error(err));