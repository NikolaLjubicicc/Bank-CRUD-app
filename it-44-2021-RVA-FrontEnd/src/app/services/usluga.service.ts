import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { KORISNIKUSLUGE_URL, USLUGA_URL } from '../constants';
import { Usluga } from '../models/usluga';

@Injectable({
  providedIn: 'root'
})
export class UslugaService {

  constructor(private httpClient:HttpClient) { }
  
  public getAllUslugas():Observable<any>{
    return this.httpClient.get(`${USLUGA_URL}`);
  }

  public getUslugeByKorisnikUsluge(korisnikuslugeId:number):Observable <any>{
    return this.httpClient.get(`${USLUGA_URL}/korisnikusluge/${korisnikuslugeId}`);
  }

  public addUsluga(usluga:Usluga):Observable<any>{
    return this.httpClient.post(`${USLUGA_URL}`,usluga);
  }
  
  public updateUsluga(usluga:Usluga):Observable<any>{
    console.log("OVO SAD PRINTAMO   " + usluga.korisnikusluge.ime);
    console.log("OVO SAD PRINTAMO " + JSON.stringify(usluga));
    return this.httpClient.put(`${USLUGA_URL}/id/${usluga.id}`,usluga);
  }

  public deleteUsluga(uslugaId:number):Observable<any>{
    return this.httpClient.delete(`${USLUGA_URL}/id/${uslugaId}`,{responseType:"text"});
  }
}
