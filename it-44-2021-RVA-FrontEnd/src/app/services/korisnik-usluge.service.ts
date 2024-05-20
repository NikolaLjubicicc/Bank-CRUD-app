import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { KORISNIKUSLUGE_URL } from '../constants';
import { KorisnikUsluge } from '../models/kornisnik-usluge';

@Injectable({
  providedIn: 'root'
})
export class KorisnikUslugeService {

  constructor(private httpClient:HttpClient) { }
  
  public getAllKorisnikUsluge():Observable<any>{
    return this.httpClient.get(`${KORISNIKUSLUGE_URL}`);
  }

  public addKorisnikUsluge(korisnikusluge:KorisnikUsluge):Observable<any>{
    return this.httpClient.post(`${KORISNIKUSLUGE_URL}`,korisnikusluge);
  }
  
  public updateKorisnikUsluge(korisnikusluge:KorisnikUsluge):Observable<any>{
    return this.httpClient.put(`${KORISNIKUSLUGE_URL}/id/${korisnikusluge.id}`,korisnikusluge);
  }

  public deleteKorisnikUsluge(korisnikuslugeId:number):Observable<any>{
    return this.httpClient.delete(`${KORISNIKUSLUGE_URL}/id/${korisnikuslugeId}`,{responseType:"text"});
  }
}
