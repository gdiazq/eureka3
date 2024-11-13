import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { Semana3RoutingModule } from './semana3-routing.module';
import { SharedModule } from '../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';

import { HomeComponent } from './pages/home/home.component';
import { AboutComponent } from './pages/about/about.component';
import { GetRecordsComponent } from './pages/records/get-records/get-records.component';
import { AddRecordsComponent } from './pages/records/add-records/add-records.component';
import { DeleteRecordsComponent } from './pages/records/delete-records/delete-records.component';
import { UpdateRecordsComponent } from './pages/records/update-records/update-records.component';


@NgModule({
  declarations: [
    HomeComponent,
    AboutComponent,
    GetRecordsComponent,
    AddRecordsComponent,
    DeleteRecordsComponent,
    UpdateRecordsComponent
  ],
  imports: [
    CommonModule,
    Semana3RoutingModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule
  ],
  exports: []
})
export class Semana3Module { }
