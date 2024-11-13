import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Observable } from "rxjs";

import { environments } from "../../../environments/environments";
import { Proyect } from "../interfaces/semana3-interfaces";


@Injectable({providedIn: 'root'})

export class AddRecordsService {

    private baseUrl: string = environments.baseUrl;

    constructor( private http: HttpClient ) {}

    addRecords( proyect: Proyect): Observable<Proyect[]> {
        return this.http.post<Proyect[]>(`${this.baseUrl}/project`, proyect);
    }


}