import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-usluga-dialog',
  templateUrl: './usluga-dialog.component.html',
  styleUrls: ['./usluga-dialog.component.css']
})

export class UslugaDialogComponent implements OnInit {
  flag !: number;

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Usluga>,
    @Inject (MAT_DIALOG_DATA) public data: Usluga,
    public service:Usluga
    public dobavljacService:DobavljacService
  ){
    ngOnInit(): void{
      this.UslugaService.getAllUsluga().subscribe(
        (data) =>{
          this.usluga=data;
        }
      )
    }
    public compare(a:any,b:any){
      return a.id == b.id;
    }

  public add(){
    this.service.addUsluga(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspesno dodata banka sa nazivom: $(this.data.naziv)`,`U redu`,{duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspenso dodavanje`, `Zatvori`, {duration:1000});
    }

  }
  public update(){
    this.service.updateUsluga(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspesno azurirana banka sa nazivom: $(this.data.naziv)`,`U redu`,{duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspenso azuriranje`, `Zatvori`, {duration:1000});
    }
  }
  public delete(){
    this.service.deleteUsluga(this.data.id).subscribe(
      (data) => {
        this.snackBar.open(`$(data)`,`U redu`,{duration:2500});
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
