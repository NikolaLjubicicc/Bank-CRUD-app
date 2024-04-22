import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatExpansionModule} from '@angular/material/expansion';
import { BankaComponent } from './components/main/banka/banka.component';
import { FilijalaComponent } from './components/main/filijala/filijala.component';
import { UslugaComponent } from './components/main/usluga/usluga.component';
import { KorisnikUslugeComponent } from './components/main/korisnik-usluge/korisnik-usluge.component';
import { HomeComponent } from './components/utility/home/home.component';
import { AuthorComponent } from './components/utility/author/author.component';
import { AboutComponent } from './components/utility/about/about.component';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    BankaComponent,
    FilijalaComponent,
    UslugaComponent,
    KorisnikUslugeComponent,
    HomeComponent,
    AuthorComponent,
    AboutComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatGridListModule,
    MatExpansionModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
