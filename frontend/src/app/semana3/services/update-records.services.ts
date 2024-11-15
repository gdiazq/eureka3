import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { environments } from "../../../environments/environments";
import { Proyect, Customer } from "../interfaces/semana3-interfaces";
import { Observable } from "rxjs";

@Injectable({providedIn: 'root'})

export class UpdateRecordsService {

    private baseUrl: string = environments.baseUrl;

    constructor( private http: HttpClient ) {}

    // Obtener todos los registros
    getAllProyects(): Observable<Proyect[]> {
        return this.http.get<Proyect[]>(`${this.baseUrl}/project`);
    }

    getAllCustomers(): Observable<Customer[]> {
        return this.http.get<Customer[]>(`${this.baseUrl}/customer`);
    }

    getProyectById(id: number): Observable<Proyect> {
        return this.http.get<Proyect>(`${this.baseUrl}/project/${id}`);
    }

    getCustomerById(id: number): Observable<Customer> {
        return this.http.get<Customer>(`${this.baseUrl}/customer/${id}`);
    }

    updateRecord(id: number, proyect: Proyect): Observable<Proyect[]> {
        return this.http.put<Proyect[]>(`${this.baseUrl}/project/${id}`, proyect);
    }

}