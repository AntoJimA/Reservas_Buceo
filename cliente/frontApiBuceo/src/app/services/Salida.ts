export class Salida{
    id:number;
    fecha:string;
    hora:string;
    capacidad:number;
    plazasDisponibles:number;

    constructor(id:number,fecha:string,hora:string,capacidad:number,plazasDisponiles:number){
        this.id=id;
        this.fecha=fecha;
        this.hora=hora;
        this.capacidad=capacidad;
        this.plazasDisponibles=capacidad;
    }
}