import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Banka } from 'src/app/models/banka';
import { Filijala } from 'src/app/models/filijala';
import { BankaService } from 'src/app/services/banka.service';
import { FilijalaService } from 'src/app/services/filijala.service';

@Component({
  selector: 'app-filijala-dialog',
  templateUrl: './filijala-dialog.component.html',
  styleUrls: ['./filijala-dialog.component.css']
})
export class FilijalaDialogComponent implements OnInit{
  public flag !: number;
  banke !: Banka[];
  constructor(public snackBar: MatSnackBar, 
    public dialogRef: MatDialogRef<Filijala>,
    @Inject (MAT_DIALOG_DATA) public data: Filijala,
    public filijalaService: FilijalaService,
    public bankaService :BankaService
  ){}
  ngOnInit(): void {
    this.bankaService.getAllBankas().subscribe(
      (data) => {
        this.banke = data;
      }
    )
  }
  public compare(a:any,b:any){
    return a.id == b.id;
  }
  public addFilijala(): void {
    this.filijalaService.addFilijala(this.data).subscribe((data) => {
      this.snackBar.open(`Uspenso dodata filijala: ${this.data.adresa}`,`U redu`,{duration:2500})
    }),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspenso dodavanje`, `Zatvori`, {duration:1000});
    }}
    public updateFilijala(): void{
      this.filijalaService.updateFilijala(this.data).subscribe(
        (data) => {
          this.snackBar.open(`Uspesno azurirana filijal na adresi: ${this.data.adresa}`,`U redu`,{duration:2500});
        }
      ),
      (error:Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open(`Neuspenso azuriranje`, `Zatvori`, {duration:1000});
      }
    }
    public deleteFilijala(){
      this.filijalaService.deleteFilijala(this.data.id).subscribe(
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

