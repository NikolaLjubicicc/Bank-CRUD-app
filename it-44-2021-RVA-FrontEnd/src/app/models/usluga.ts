import { Filijala } from "./filijala";
import { KorisnikUsluge } from "./kornisnik-usluge";

export class Usluga{
    id!:number;
    naziv!:string;
    opisUsluge!:string;
    datumUgovora!:Date;
    provizija!:number;
    filijala!:Filijala;
    korisnikusluge!:KorisnikUsluge;
}