import { Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Filijala } from 'src/app/models/filijala';
import { KorisnikUsluge } from 'src/app/models/kornisnik-usluge';
import { Usluga } from 'src/app/models/usluga';
import { UslugaService } from 'src/app/services/usluga.service';
import { UslugaDialogComponent } from '../../dialogs/usluga-dialog/usluga-dialog.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';


@Component({
  selector: 'app-usluga',
  templateUrl: './usluga.component.html',
  styleUrls: ['./usluga.component.css']
})
export class UslugaComponent implements OnInit,OnDestroy,OnChanges{
  displayedColumns = ['id','naziv','opisUsluge','datumUgovora','provizija','filijala','actions'];
  dataSource!: MatTableDataSource<Usluga>;
  subscription!: Subscription;
  @Input() childSelectedKorisnikUsluge !: KorisnikUsluge;
  @ViewChild(MatSort,{static:false}) sort!:MatSort;
  @ViewChild(MatPaginator,{static:false}) paginator!:MatPaginator;
  constructor(private service:UslugaService,
     public dialog:MatDialog){
    
  }
  ngOnChanges(changes: SimpleChanges): void {
    this.loadData();
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }


public loadData(){
 this.subscription = this.service.getUslugeByKorisnikUsluge(this.childSelectedKorisnikUsluge.id).subscribe(
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

public openDialog(flag:number,id?:number,naziv?:string,datumUgovora?:Date,opisUsluge?:string,provizija?:number,filijala?:Filijala){
  const dialogRef = this.dialog.open(UslugaDialogComponent, {data:{id,naziv,opisUsluge,datumUgovora,provizija,filijala}});
  dialogRef.componentInstance.flag = flag;
  dialogRef.componentInstance.data.korisnikusluge = this.childSelectedKorisnikUsluge;
  dialogRef.afterClosed().subscribe(
    (result) => {
      if(result == 1){
        this.loadData();
      }
    }
  )
}
public applyFilter(filter:any){
  filter = filter.target.value;
  filter = filter.trim();
  filter = filter .toLocaleLowerCase();
  this.dataSource.filter = filter;
 }
}
