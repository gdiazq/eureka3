import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

//Este archivo es el encargado de manejar las rutas de la aplicación

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./semana3/semana3.module').then(m => m.Semana3Module)
  },
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
