import { Component, OnInit } from "@angular/core";

import { Proyect } from '../../../interfaces/semana3-interfaces';

import { GetRecordsService } from "../../../services/get-records.services";

@Component({
    selector: 'app-get-records',
    templateUrl: './get-records.component.html',
    styleUrls: ['./get-records.component.css']
})

export class GetRecordsComponent implements OnInit {

    constructor( private getRecordsService: GetRecordsService) { }

    getRecords: Proyect[] = [];
  
    ngOnInit(): void {
        this.showRecords();
    }
  
    showRecords(): void {
      this.getRecordsService.getRecords().subscribe(
        (data: Proyect[]) => {
          this.getRecords = data;
        },
        (error) => {
          console.log("Error al conseguir los registros", error);
        }
      );
    }
}