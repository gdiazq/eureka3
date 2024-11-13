import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Observable } from "rxjs";

import { environments } from "../../../environments/environments";

import { Proyect } from "../interfaces/semana3-interfaces";

@Injectable({providedIn: 'root'})

export class DeleteRecordsService {

    private baseUrl: string = environments.baseUrl;

    constructor( private http: HttpClient ) {}

    getRecords(): Observable<Proyect[]> {
        return this.http.get<Proyect[]>(`${this.baseUrl}/project`);
    }

    deleteRecordsById( id: number ) : Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/project/${id}`)
    }    
}   