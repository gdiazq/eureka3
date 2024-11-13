import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { environments } from "../../../environments/environments";
import { Proyect } from "../interfaces/semana3-interfaces";
import { Observable } from "rxjs";

@Injectable({providedIn: 'root'})

export class UpdateRecordsService {

    private baseUrl: string = environments.baseUrl;

    constructor( private http: HttpClient ) {}

    // Obtener todos los registros
    getAllRecords(): Observable<Proyect[]> {
        return this.http.get<Proyect[]>(`${this.baseUrl}/project`);
    }

    getRecordById(id: number): Observable<Proyect> {
        return this.http.get<Proyect>(`${this.baseUrl}/project/${id}`);
    }

    updateRecords(id: number, proyect: Proyect): Observable<Proyect> {
        return this.http.put<Proyect>(`${this.baseUrl}/project/${id}`, proyect);
    }

}