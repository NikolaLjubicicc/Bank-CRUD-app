import { ParseFlags } from '@angular/compiler';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Banka } from 'src/app/models/banka';
import { BankaService } from 'src/app/services/banka.service';
import { BankaDialogComponent } from '../../dialogs/banka-dialog/banka-dialog.component';

@Component({
  selector: 'app-banka',
  templateUrl: './banka.component.html',
  styleUrls: ['./banka.component.css']
})
export class BankaComponent implements OnInit, OnDestroy{
  displayedColumns = ['id','naziv','kontakt','pib','actions'];
  dataSource!: MatTableDataSource<Banka>;
  subscription!: Subscription;

  constructor(private service:BankaService, public dialog:MatDialog){
    
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }


public loadData(){
 this.subscription = this.service.getAllBankas().subscribe(
    (data) => {
      //console.log(data);
      this.dataSource = new MatTableDataSource(data);
    }
  ),
  (error: Error) => {
    console.log(error.name + ' '+ error.message);
  }
 }

public openDialog(flag:number,id?:number,naziv?:string,kontakt?:string,pib?:number){
  const dialogRef = this.dialog.open(BankaDialogComponent, {data:{id,naziv,kontakt,pib}});
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
