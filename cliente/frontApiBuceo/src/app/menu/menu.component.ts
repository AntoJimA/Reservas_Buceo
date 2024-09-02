import { Component } from '@angular/core';
import { MenuService } from '../services/menu.service';
import { User } from '../services/User';
import { CommonModule } from '@angular/common';
import { TablaComponent } from '../tabla/tabla.component';
import { CrearSalidaComponent } from '../crear-salida/crear-salida.component';
@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, TablaComponent,CrearSalidaComponent],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {
  user!: User;
  constructor(private menuService:MenuService) {
   }

ngOnInit():void {
    this.getUsuario();
}
   
  getUsuario():void {
    this.menuService.getUsuario().subscribe((data: any) => {this.user = data;});
  }
}
