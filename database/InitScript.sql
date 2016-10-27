insert into Org(Name, Phone)
values('Hvidovre Kommune', '46282828'),('Hansen A/S', '66666666');

insert into building(FkOrgId,Name,Address, ConstructionYear, CurrentUse, Area, PreviousUse)
values(1,'Hvidovre Bibliotek','Hovedgaden 5', '1995-01-01', 'Bibliotek', 200, 'Lager'),
(2,'Hansen Seaside Apts','Havnevej 45-55', '2013-10-10', 'Lejligheder', 3500, '');

insert into role(Name)
values('Customer'), ('Employee'),('Admin');

insert into user(Name, Password, Phone, Email, FkRoleId,FkOrgId)
values('Henning Flemmingsen','Henny42!','37573717','hefle@hvikom.dk',1,1),
('Fritz Frandsen','Fr4pst4r!','62636564','frfr@hansenapts.dk',1,2);

insert into damagetype(Name)
values('Fugt'),('Råd og svamp'),('Skimmel'),('Brand'),('Andet');

insert into servicetype(Name)
values('Sundhedstjek'),('Skadetjek'),('Reperation'),('Grafitti fjernelse'),('Andet');