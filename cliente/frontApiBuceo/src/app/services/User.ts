export class User {
    nombre: string;
    apellido: string;
    email: string;
    nivelBuceo: string;
    username: string;
    password: string;
    role: string;

    constructor(
        nombre: string,
        apellido: string,
        email: string,
        nivelBuceo: string,
        username: string,
        password: string,
        role: string
    ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nivelBuceo = nivelBuceo;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}