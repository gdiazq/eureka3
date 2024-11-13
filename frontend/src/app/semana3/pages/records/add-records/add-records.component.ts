import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Proyect } from '../../../interfaces/semana3-interfaces';

import { AddRecordsService } from '../../../services/add-records.services';
import { ValidatorService } from '../../../services/validator.service';

@Component({
    selector: 'app-add-records',
    templateUrl: './add-records.component.html'
})

export class AddRecordsComponent {

    public myForm: FormGroup = this.fb.group({
        nombreProyecto: ['', [ Validators.required, Validators.minLength(4), Validators.pattern( this.validatorService.nameProyectPattern) ]],
        nombreCliente: ['', [ Validators.required, Validators.minLength(4), Validators.pattern( this.validatorService.nameCustomerPattern) ]],
        casa_matriz: ['', [ Validators.required, Validators.minLength(4), Validators.pattern( this.validatorService.casaMatrizPattern) ]],
    });

    public addRecords: Proyect[] = [];
    public recordAdded: boolean = false;

    constructor( 
        private fb: FormBuilder,
        private addRecordsSevice: AddRecordsService,
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

    addRecord(proyect: Proyect): void {
        this.addRecordsSevice.addRecords(proyect).subscribe(
            (data: Proyect[]) => {
                this.addRecords = data;
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

        const formValues = this.myForm.value;
        const proyect: Proyect = {
            nombre: formValues.nombreProyecto,
            clientes: [
                {
                    nombre: formValues.nombreCliente,
                    casa_matriz: formValues.casa_matriz
                }
            ]
        };

        this.addRecord(proyect);
    }

}