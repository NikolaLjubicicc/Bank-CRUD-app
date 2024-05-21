import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { KorisnikUsluge } from 'src/app/models/kornisnik-usluge';
import { KorisnikUslugeService } from 'src/app/services/korisnik-usluge.service';
import { BankaDialogComponent } from '../../dialogs/banka-dialog/banka-dialog.component';
import { KorisnikUslugeDialogComponent } from '../../dialogs/korisnik-usluge-dialog/korisnik-usluge-dialog.component';

@Component({
  selector: 'app-korisnik-usluge',
  templateUrl: './korisnik-usluge.component.html',
  styleUrls: ['./korisnik-usluge.component.css']
})
export class KorisnikUslugeComponent implements OnInit,OnDestroy{
  displayedColumns = ['id','ime','prezime','maticniBroj','actions'];
  dataSource!: MatTableDataSource<KorisnikUsluge>;
  subscription!: Subscription;
  parentSelectedKorisnikUsluge !:KorisnikUsluge;
  constructor(private service:KorisnikUslugeService, public dialog:MatDialog){
    
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }


public loadData(){
 this.subscription = this.service.getAllKorisnikUsluge().subscribe(
    (data) => {
      this.dataSource = new MatTableDataSource(data);
    }
  ),
  (error: Error) => {
    console.log(error.name + ' '+ error.message);
  }
 }

public openDialog(flag:number,id?:number,ime?:string,prezime?:string,maticniBroj?:string){
  const dialogRef = this.dialog.open(KorisnikUslugeDialogComponent, {data:{id,ime,prezime,maticniBroj}});
  dialogRef.componentInstance.flag = flag;
  dialogRef.afterClosed().subscribe(
    (result) => {
      if(result == 1){
        this.loadData();
      }
    }
  )
}
public selectRow(row:KorisnikUsluge){
  this.parentSelectedKorisnikUsluge = row;
}

}
