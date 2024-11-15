import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Proyect } from '../../../interfaces/semana3-interfaces';
import { Customer } from '../../../interfaces/semana3-interfaces';

import { AddRecordsService } from '../../../services/add-records.services';
import { ValidatorService } from '../../../services/validator.service';

@Component({
    selector: 'app-add-records',
    templateUrl: './add-records.component.html'
})

export class AddRecordsComponent implements OnInit {

    public myForm: FormGroup = this.fb.group({
        id: ['', [Validators.required, Validators.min(1)]],
        nombreProyecto: ['', [ Validators.required, Validators.minLength(4), Validators.pattern( this.validatorService.nameProyectPattern) ]],
        nombreCliente: ['', [ Validators.required ]],
        casa_matriz: ['', [ Validators.required, Validators.minLength(4) ]],
    });

    public proyects: Proyect[] = [];
    public customers: Customer[] = [];
    public recordAdded: boolean = false;
    public selectedCustomer: Customer | null = null;

    constructor( 
        private fb: FormBuilder,
        private addRecordsService: AddRecordsService,
        private validatorService: ValidatorService) 
    {}

    isFieldRequired(field: string): boolean {
        return this.validatorService.isFieldRequired(this.myForm, field);
    }
    
    isFieldMinLength(field: string): boolean {
        return this.validatorService.isFieldMinLength(this.myForm, field);
    }
    
    isFieldPattern(field: string): boolean {
        return this.validatorService.isFieldPattern(this.myForm, field);
    }

    ngOnInit(): void {
        this.loadRecords();
    }

    loadRecords(): void {
        this.addRecordsService.getCustomers().subscribe(
            (data: Customer[]) => {
                this.customers = data;
            },
            (error) => {
                console.log("Error al conseguir los registros", error);
            }
        );
    }

    loadRecordData(): void {
        const selectedId = this.myForm.value.id;
        if (selectedId) {
            this.addRecordsService.getRecordById(selectedId).subscribe(
                (data: Customer) => {
                    this.selectedCustomer = data;
                    this.myForm.patchValue({
                        nombreCliente: data.nombre,
                        casa_matriz: data.casa_matriz
                    });
                },
                (error) => {
                    console.log("Error al conseguir el registro", error);
                }
            );
        }
    }

    addRecord(proyect: Proyect): void {
        this.addRecordsService.addProyect(proyect).subscribe(
            (data: Proyect[]) => {
                this.proyects = data;
                this.recordAdded = true;
                this.myForm.reset();
            },
            (error) => {
                console.log("Error al añadir el registro", error);
                this.recordAdded = false;
                this.myForm.reset();
            }
        );  
    }

    onSubmit(): void {
        this.myForm.markAllAsTouched();

        if ( this.myForm.invalid ) {
            return;
        }

        const id = this.myForm.value.id;
        const formValues = this.myForm.value;
        const proyect: Proyect = {
            nombre: formValues.nombreProyecto,
            clientes: [
                {
                    id: id,
                    nombre: formValues.nombreCliente,
                    casa_matriz: formValues.casa_matriz
                }
            ]
        };

        this.addRecord(proyect);
    }

}