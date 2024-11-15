//Este es el archivo de las interfaces
export interface Proyect {
    id?: number;
    nombre: string;
    clientes: Customer[];
}

export interface Customer {
    id?: number;
    nombre: string;
    casa_matriz?: string;
}

