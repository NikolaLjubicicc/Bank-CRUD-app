import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { KorisnikUsluge } from 'src/app/models/kornisnik-usluge';
import { KorisnikUslugeService } from 'src/app/services/korisnik-usluge.service';
import { KorisnikUslugeDialogComponent } from '../../dialogs/korisnik-usluge-dialog/korisnik-usluge-dialog.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

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
  @ViewChild(MatSort,{static:false}) sort!:MatSort;
  @ViewChild(MatPaginator,{static:false}) paginator!:MatPaginator;

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
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
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
public applyFilter(filter:any){
  filter = filter.target.value;
  filter = filter.trim();
  filter = filter .toLocaleLowerCase();
  this.dataSource.filter = filter;
 }

}
