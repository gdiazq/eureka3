import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Proyect } from '../../../interfaces/semana3-interfaces';

import { UpdateRecordsService } from '../../../services/update-records.services';
import { ValidatorService } from '../../../services/validator.service';

@Component({
    selector: 'app-update-records',
    templateUrl: './update-records.component.html'
})

export class UpdateRecordsComponent implements OnInit {

    public updateForm: FormGroup = this.fb.group({
        id: ['', [Validators.required, Validators.min(1)]],
        nombreProyecto: ['', [Validators.required, Validators.minLength(4), Validators.pattern(this.validatorService.nameProyectPattern)]],
        nombreCliente: ['', [Validators.required, Validators.minLength(4), Validators.pattern(this.validatorService.nameCustomerPattern)]],
        casa_matriz: ['', [Validators.required, Validators.minLength(4), Validators.pattern(this.validatorService.casaMatrizPattern)]],
    });

    public records: Proyect[] = [];
    public recordUpdated: boolean = false;
    public selectedProject: Proyect | null = null;

    constructor(
        private fb: FormBuilder,
        private updateRecordsService: UpdateRecordsService,
        private validatorService: ValidatorService) 
    {}
    
    isFieldRequired(field: string): boolean {
        return this.validatorService.isFieldRequired(this.updateForm, field);
    }
    
    isFieldMinLength(field: string): boolean {
        return this.validatorService.isFieldMinLength(this.updateForm, field);
    }

    isFieldPattern(field: string): boolean {
        return this.validatorService.isFieldPattern(this.updateForm, field);
    }

    ngOnInit(): void {
        this.loadRecords();
    }

    loadRecords(): void {
        this.updateRecordsService.getAllRecords().subscribe(
            (data: Proyect[]) => {
                this.records = data;
            },
            (error) => {
                console.log("Error al conseguir los registros", error);
            }
        );
    }

    loadRecordData(): void {
        const selectedId = this.updateForm.value.id;
        if (selectedId) {
            this.updateRecordsService.getRecordById(selectedId).subscribe(
                (data: Proyect) => {
                    this.selectedProject = data;
                    this.updateForm.patchValue({
                        nombreProyecto: data.nombre,
                        nombreCliente: data.clientes[0]?.nombre,
                        casa_matriz: data.clientes[0]?.casa_matriz,
                    });
                },
                (error) => {
                    console.log("Error al conseguir el registro", error);
                }
            );
        }
    }

    updateRecord(): void {
        if (this.updateForm.invalid) return; 

        const id = this.updateForm.value.id;
        const updatedProject: Proyect = {
            id: id,
            nombre: this.updateForm.value.nombreProyecto,
            clientes: [
                {
                    nombre: this.updateForm.value.nombreCliente,
                    casa_matriz: this.updateForm.value.casa_matriz,
                },
            ],
        };

        this.updateRecordsService.updateRecords(id, updatedProject).subscribe(
            () => {
                this.recordUpdated = true;
                this.loadRecords();
                this.updateForm.reset();
            },
            (error) => {
                console.log("Error al actualizar el registro", error);
                this.recordUpdated = false;
            }
        );
    }

} 