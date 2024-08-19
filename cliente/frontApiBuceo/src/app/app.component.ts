import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { RegisterComponent } from './register/register.component';
import { RegistroService } from './services/registro.service';
import { HttpClientModule } from '@angular/common/http'; 
import { TablaComponent } from './tabla/tabla.component';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, MenuComponent, RegisterComponent, HttpClientModule,TablaComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers:[RegistroService],
  template: '<router-outlet></router-outlet>'
})
export class AppComponent {
  title = 'frontApiBuceo';
}
