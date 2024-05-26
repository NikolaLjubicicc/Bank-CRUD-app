insert into banka(id,naziv,kontakt,pib)
values(nextval('banka_seq'),'Erste','erste@kontakt.com',123),
	  (nextval('banka_seq'),'Intesa','intesa@kontakt.com',1234),
	  (nextval('banka_seq'),'Otp','otp@kontakt.com',12345),
	  (nextval('banka_seq'),'Addiko','addiko@kontakt.com',123456);
	  
insert into korisnik_usluge(id,ime,prezime,maticni_broj)
values(nextval('korisnik_usluge_seq'),'Nikola','Ljubicic','2007002698512'),
	  (nextval('korisnik_usluge_seq'),'Mirko','Plavsic','2617002598562'),
	  (nextval('korisnik_usluge_seq'),'Matija','Stankovic','1702015688562'),
	  (nextval('korisnik_usluge_seq'),'Milica','Pavlovic','0407012694583');

insert into filijala(id,banka,adresa,broj_pultova,poseduje_sef) 
values(nextval('filijala_seq'),1,'Cirpanova 12',4,true),
      (nextval('filijala_seq'),2,'Branislava Nusica 2',6,true), 
      (nextval('filijala_seq'),3,'Doza Djerdja 21',4,false),
      (nextval('filijala_seq'),4,'Gogoljeva 12',2,false);
      
insert into usluga(id,filijala,korisnikusluge,naziv,datum_ugovora,opis_usluge,provizija) 
values (nextval('usluga_seq'),4,4,'prenos sredstava',to_date('11.02.2024.','dd.mm.yyyy.'),'prenos sredstava na racun',15),
	  (nextval('usluga_seq'),4,4,'DVOJKA',to_date('11.02.2024.','dd.mm.yyyy.'),'prenos sredstava na racun',15),
      (nextval('usluga_seq'),3,3,'otvaranje racuna',to_date('01.03.2024.','dd.mm.yyyy.'),'otvaranje racuna klijentu',1),
      (nextval('usluga_seq'),2,2,'kredit',to_date('20.07.2024.','dd.mm.yyyy.'),'odobravanje kredita',50),
      (nextval('usluga_seq'),1,1,'dozvoljeni minus',to_date('24.09.2023.','dd.mm.yyyy.'),'odobravanje dozvoljenog minusa',55);