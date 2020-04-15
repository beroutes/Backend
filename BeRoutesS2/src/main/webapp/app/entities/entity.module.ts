import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'rutas',
        loadChildren: () => import('./rutas/rutas.module').then(m => m.BeRoutesRutasModule)
      },
      {
        path: 'categorias',
        loadChildren: () => import('./categorias/categorias.module').then(m => m.BeRoutesCategoriasModule)
      },
      {
        path: 'valoraciones',
        loadChildren: () => import('./valoraciones/valoraciones.module').then(m => m.BeRoutesValoracionesModule)
      },
      {
        path: 'seguidores',
        loadChildren: () => import('./seguidores/seguidores.module').then(m => m.BeRoutesSeguidoresModule)
      },
      {
        path: 'usuarios',
        loadChildren: () => import('./usuarios/usuarios.module').then(m => m.BeRoutesUsuariosModule)
      },
      {
        path: 'fotos',
        loadChildren: () => import('./fotos/fotos.module').then(m => m.BeRoutesFotosModule)
      },
      {
        path: 'ubicaciones',
        loadChildren: () => import('./ubicaciones/ubicaciones.module').then(m => m.BeRoutesUbicacionesModule)
      },
      {
        path: 'paises',
        loadChildren: () => import('./paises/paises.module').then(m => m.BeRoutesPaisesModule)
      },
      {
        path: 'ciudades',
        loadChildren: () => import('./ciudades/ciudades.module').then(m => m.BeRoutesCiudadesModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class BeRoutesEntityModule {}
