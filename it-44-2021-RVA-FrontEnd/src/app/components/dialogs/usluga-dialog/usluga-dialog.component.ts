import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Filijala } from 'src/app/models/filijala';
import { KorisnikUsluge } from 'src/app/models/kornisnik-usluge';
import { Usluga } from 'src/app/models/usluga';
import { FilijalaService } from 'src/app/services/filijala.service';
import { KorisnikUslugeService } from 'src/app/services/korisnik-usluge.service';
import { UslugaService } from 'src/app/services/usluga.service';

@Component({
  selector: 'app-usluga-dialog',
  templateUrl: './usluga-dialog.component.html',
  styleUrls: ['./usluga-dialog.component.css']
})

export class UslugaDialogComponent implements OnInit {
  flag !: number;
  filijale !: Filijala[];
  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Usluga>,
    @Inject (MAT_DIALOG_DATA) public data: Usluga,
    public uslugaService:UslugaService,
    public filijalaService:FilijalaService
  ){}
  ngOnInit(): void {
  
    
    this.filijalaService.getAllFilijalas().subscribe(
      (data) => {
        this.filijale = data;
      }
    )
  }
  public compare(a:any,b:any){
    return a.id == b.id;
  }
  public addUsluga(): void {
    this.uslugaService.addUsluga(this.data).subscribe((data) => {
      this.snackBar.open(`Uspenso dodata usluga: ${this.data.naziv}`,`U redu`,{duration:2500})
    }),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspenso dodavanje`, `Zatvori`, {duration:1000});
    }}
    public updateUsluga(): void{
      this.uslugaService.updateUsluga(this.data).subscribe(
        (data) => {
          this.snackBar.open(`Uspesno azurirana usluga sa nazivom: ${this.data.naziv}`,`U redu`,{duration:2500});
        }
      ),
      (error:Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open(`Neuspenso azuriranje`, `Zatvori`, {duration:1000});
      }
    }
    public deleteUsluga(){
      this.uslugaService.deleteUsluga(this.data.id).subscribe(
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
