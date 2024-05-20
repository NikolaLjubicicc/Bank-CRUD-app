import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Filijala } from 'src/app/models/filijala';
import { KorisnikUsluge } from 'src/app/models/kornisnik-usluge';
import { Usluga } from 'src/app/models/usluga';
import { UslugaService } from 'src/app/services/usluga.service';
import { UslugaDialogComponent } from '../../dialogs/usluga-dialog/usluga-dialog.component';


@Component({
  selector: 'app-usluga',
  templateUrl: './usluga.component.html',
  styleUrls: ['./usluga.component.css']
})
export class UslugaComponent implements OnInit,OnDestroy{
  displayedColumns = ['id','naziv','opisUsluge','datumUgovora','provizija','korisnikusluge','filijala','actions'];
  dataSource!: MatTableDataSource<Usluga>;
  subscription!: Subscription;

  constructor(private service:UslugaService, public dialog:MatDialog){
    
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }


public loadData(){
 this.subscription = this.service.getAllUslugas().subscribe(
    (data) => {
      //console.log(data);
      this.dataSource = new MatTableDataSource(data);
    }
  ),
  (error: Error) => {
    console.log(error.name + ' '+ error.message);
  }
 }

public openDialog(flag:number,id?:number,naziv?:string,datumUgovora?:Date,opisUsluge?:string,provizija?:number,korisnikusluge?:KorisnikUsluge,filijala?:Filijala){
  const dialogRef = this.dialog.open(UslugaDialogComponent, {data:{id,naziv,datumUgovora,opisUsluge,provizija,korisnikusluge,filijala}});
  dialogRef.componentInstance.flag = flag;
  dialogRef.afterClosed().subscribe(
    (result) => {
      if(result == 1){
        this.loadData();
      }
    }
  )
}
}
