USE sundebygninger;

insert into Org(Name, Phone)
values('Hvidovre Kommune', '46282828'),('Hansen A/S', '66666666');

insert into Building(FkOrgId,Name,Address, ConstructionYear, CurrentUse, Area, PreviousUse)
values(1,'Hvidovre Bibliotek','Hovedgaden 5', '1995-01-01', 'Bibliotek', 200, 'Lager'),
(2,'Hansen Seaside Apts','Havnevej 45-55', '2013-10-10', 'Lejligheder', 3500, '');

insert into Role(Name)
values('Customer'), ('Employee'),('Admin');

insert into User(Name, Password, Phone, Email, FkRoleId,FkOrgId)
values('Henning Flemmingsen','Henny42!','37573717','hefle@hvikom.dk',1,1),
('Fritz Frandsen','Fr4pst4r!','62636564','frfr@hansenapts.dk',1,2),
('User Usersen','password','88888888','user@employee.dk',1,1),
('Service Servicesen','password','88888888','service@employee.dk',2,null),
('Admin Adminsen','password','88888888','admin@employee.dk',3,null);

insert into DamageType(Name)
values('Fugt'),('Råd og svamp'),('Skimmel'),('Brand'),('Andet');

insert into ServiceType(Name)
values('Sundhedstjek'),('Skadetjek'),('Reperation'),('Grafitti fjernelse'),('Andet');

insert into FileType(Name)
values('Dokument'),('Billede');
