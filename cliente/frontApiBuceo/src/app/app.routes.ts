import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { RegisterComponent } from './register/register.component';

export const routes: Routes = [{path:'',redirectTo:'/login',pathMatch:'full'},{path: 'login',component: LoginComponent},{path: 'menu',component: MenuComponent},{path: 'register',component: RegisterComponent}];
