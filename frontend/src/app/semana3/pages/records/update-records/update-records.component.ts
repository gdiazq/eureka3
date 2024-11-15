import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Customer, Proyect } from '../../../interfaces/semana3-interfaces';

import { UpdateRecordsService } from '../../../services/update-records.services';
import { ValidatorService } from '../../../services/validator.service';

@Component({
    selector: 'app-update-records',
    templateUrl: './update-records.component.html'
})

export class UpdateRecordsComponent implements OnInit {

    public updateForm: FormGroup = this.fb.group({
        idProyecto: ['', [Validators.required, Validators.min(1)]],
        nombreProyecto: ['', [Validators.required, Validators.minLength(4), Validators.pattern(this.validatorService.nameProyectPattern)]],
        idCliente: ['', [Validators.required, Validators.min(1)]],
        nombreCliente: ['', [Validators.required, Validators.minLength(4), Validators.pattern(this.validatorService.nameCustomerPattern)]],
        casa_matriz: ['', [Validators.required, Validators.minLength(4), Validators.pattern(this.validatorService.casaMatrizPattern)]],
    });

    public customers: Customer[] = [];
    public records: Proyect[] = [];
    public filteredCustomers: Customer[] = [];
    public recordUpdated: boolean = false;
    public selectedProject: Proyect | null = null;
    public selectedCustomer: Customer | null = null;

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
        this.loadProyects();
        this.loadCustomers();
    }

    loadCustomers(): void {
        this.updateRecordsService.getAllCustomers().subscribe(
            (data: Customer[]) => {
                this.customers = data;
            },
            (error) => {
                console.log("Error al conseguir los clientes", error);
            }
        );
    }

    loadProyects(): void {
        this.updateRecordsService.getAllProyects().subscribe(
            (data: Proyect[]) => {
                this.records = data;
            },
            (error) => {
                console.log("Error al conseguir los registros", error);
            }
        );
    }

    loadRecordProyect(): void {
        const selectedId = this.updateForm.value.idProyecto;
        if (selectedId) {
            this.updateRecordsService.getProyectById(selectedId).subscribe(
                (data: Proyect) => {
                    this.selectedProject = data;
                    this.filteredCustomers = data.clientes;
                    this.updateForm.patchValue({
                        nombreProyecto: data.nombre
                    });
                },
                (error) => {
                    console.log("Error al conseguir el registro", error);
                }
            );
        }
    }

    loadRecordCustomer(): void {
        const selectedId = this.updateForm.value.idCliente;
        if (selectedId) {
            this.updateRecordsService.getCustomerById(selectedId).subscribe(
                (data: Customer) => {
                    this.selectedCustomer = data;
                    this.updateForm.patchValue({
                        idCliente: data.id,
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

    updateRecord(): void {
        if (this.updateForm.invalid) return; 

        const id = this.updateForm.value.idProyecto;
        const updatedProject: Proyect = {
            id: id,
            nombre: this.updateForm.value.nombreProyecto,
            clientes: [
                {
                    id: this.updateForm.value.idCliente,
                    nombre: this.updateForm.value.nombreCliente,
                    casa_matriz: this.updateForm.value.casa_matriz,
                },
            ],
        };

        this.updateRecordsService.updateRecord(id, updatedProject).subscribe(
            () => {
                this.recordUpdated = true;
                this.loadProyects();
                this.updateForm.reset();
            },
            (error) => {
                console.log("Error al actualizar el registro", error);
                this.recordUpdated = false;
            }
        );
    }

} 