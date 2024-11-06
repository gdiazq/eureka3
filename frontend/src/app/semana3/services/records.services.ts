import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Observable } from "rxjs";

import { environments } from "../../../environments/environments";
import { Customer } from "../interfaces/semana3-interfaces";

//Este es el servicio que se encarga de hacer las peticiones al backend

@Injectable({providedIn: 'root'})

export class RecordsService {

    private baseUrl: string = environments.baseUrl;

    constructor( private http: HttpClient) {}

    getRecords(): Observable<Customer[]> {
        return this.http.get<Customer[]>(`${this.baseUrl}/customer`);
    }

}