import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';
import { Semana3RoutingModule } from './semana3-routing.module';
import { SharedModule } from '../shared/shared.module';

import { HomeComponent } from './pages/home/home.component';
import { AboutComponent } from './pages/about/about.component';
import { RecordsComponent } from './pages/records/records.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    HomeComponent,
    AboutComponent,
    RecordsComponent
  ],
  imports: [
    CommonModule,
    Semana3RoutingModule,
    HttpClientModule,
    SharedModule
  ],
  exports: []
})
export class Semana3Module { }
