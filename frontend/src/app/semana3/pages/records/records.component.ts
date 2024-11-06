import { Component, OnInit } from '@angular/core';
import { Customer } from '../../interfaces/semana3-interfaces';
import { RecordsService } from '../../services/records.services';

@Component({
  selector: 'app-records-pages',
  templateUrl: './records.component.html'
})
export class RecordsComponent implements OnInit {

  constructor( private recordsService: RecordsService) { }

  records: Customer[] = [];

  ngOnInit(): void {
      this.showRecords();
  }

  showRecords(): void {
    this.recordsService.getRecords().subscribe(
      (data: Customer[]) => {
        this.records = data;
      },
      (error) => {
        console.log("Error al conseguir los registros", error);
      }
    );
  }

  addRecords(): void {

  }

  updateRecords(): void {

  }

  deleteRecords(): void {

  }

}
