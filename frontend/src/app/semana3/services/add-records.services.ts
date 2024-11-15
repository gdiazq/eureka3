import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Observable } from "rxjs";

import { Proyect } from "../interfaces/semana3-interfaces";
import { Customer } from "../interfaces/semana3-interfaces";

import { environments } from "../../../environments/environments";



@Injectable({providedIn: 'root'})

export class AddRecordsService {

    private baseUrl: string = environments.baseUrl;

    constructor( private http: HttpClient ) {}

    getCustomers(): Observable<Customer[]> {
        return this.http.get<Customer[]>(`${this.baseUrl}/customer`);
    }

    getRecordById(id: number): Observable<Customer> {
        return this.http.get<Customer>(`${this.baseUrl}/customer/${id}`);
    }

    addProyect( proyect: Proyect): Observable<Proyect[]> {
        return this.http.post<Proyect[]>(`${this.baseUrl}/project`, proyect);
    }


}