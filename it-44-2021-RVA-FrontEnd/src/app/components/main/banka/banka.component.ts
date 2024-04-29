import { Component, OnInit } from '@angular/core';
import { BankaService } from 'src/app/services/banka.service';

@Component({
  selector: 'app-banka',
  templateUrl: './banka.component.html',
  styleUrls: ['./banka.component.css']
})
export class BankaComponent implements OnInit{
  constructor(private service:BankaService){

  }

  ngOnInit(): void {
    this.loadData();
  }


public loadData(){
  this.service.getAllBankas().subscribe(
    (data) => {
      console.log(data);
    }
  ),
  (error: Error) => {
    console.log(error.name + ' '+ error.message);
  }
}
}
