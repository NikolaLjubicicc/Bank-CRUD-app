import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { KorisnikUsluge } from 'src/app/models/kornisnik-usluge';
import { KorisnikUslugeService } from 'src/app/services/korisnik-usluge.service';

@Component({
  selector: 'app-korisnik-usluge-dialog',
  templateUrl: './korisnik-usluge-dialog.component.html',
  styleUrls: ['./korisnik-usluge-dialog.component.css']
})
export class KorisnikUslugeDialogComponent {

  flag !:number 
  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<KorisnikUsluge>,
    @Inject (MAT_DIALOG_DATA) public data: KorisnikUsluge,
    public service:KorisnikUslugeService
  ){}

  public addKorisnikUsluge(){
    this.service.addKorisnikUsluge(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspesno dodat korisnik usluge sa maticnim brojem: ${this.data.maticniBroj}`,`U redu`,{duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspenso dodavanje`, `Zatvori`, {duration:1000});
    }

  }
  public updateKorisnikUsluge(){
    this.service.updateKorisnikUsluge(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspesno azuriran korisnik usluge sa maticnim brojem: ${this.data.maticniBroj}`,`U redu`,{duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspenso azuriranje`, `Zatvori`, {duration:1000});
    }
  }
  public deleteKorisnikUsluge(){
    this.service.deleteKorisnikUsluge(this.data.id).subscribe(
      (data) => {
        this.snackBar.open(`${data}`,`U redu`,{duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspenso brisanje`, `Zatvori`, {duration:1000});
    }
  }
  public cancel(){
    this.dialogRef.close();
    this.snackBar.open(`Odustali ste od izmena`,`Zatvori`, {duration:2500});
  }
}
