import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

import { Proyect } from "../../../interfaces/semana3-interfaces";

import { DeleteRecordsService } from "../../../services/delete-records.services";
import { ValidatorService } from "../../../services/validator.service";

@Component({
    selector: 'app-delete-records',
    templateUrl: './delete-records.component.html'
})

export class DeleteRecordsComponent implements OnInit {

    public deleteForm: FormGroup;
    public proyects: Proyect[] = []; 
    public recordDeleted: boolean = false;

    constructor(
        private fb: FormBuilder,
        private deleteRecordsService: DeleteRecordsService,
        private validatorService: ValidatorService
    ) {
        this.deleteForm = this.fb.group({
            id: ['', [Validators.required, Validators.min(1)]]
        });
    }

    isFieldRequired(field: string): boolean {
        return this.validatorService.isFieldRequired(this.deleteForm, field);
    }

    loadRecords(): void {
        this.deleteRecordsService.getRecords().subscribe(
            (data: Proyect[]) => {
                this.proyects = data;
            },
            (error) => {
                console.log("Error al conseguir los registros", error);
            }
        );
    }

    ngOnInit(): void {
        this.loadRecords();
    }

    deleteRecord(): void {
        if (this.deleteForm.invalid) return;

        const id = this.deleteForm.value.id;
        this.deleteRecordsService.deleteRecordsById(id).subscribe(
            () => {
                this.recordDeleted = true;
                this.loadRecords();
            },
            (error) => {
                console.log("Error al eliminar el registro", error);
            }
        );

    }

}