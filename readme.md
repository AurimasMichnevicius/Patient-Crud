CRUD Web Servisas, Pacientu registras.

Naudojama Spring Boot sistema.

Projektas sukompiliuojamas ir paleidžiamas "docker-compose up" komanda.

Patikrinimui naudojame postman
http://localhost/api/v1/

CREATE:
'  {
        "id": 1,
        "name": "Aurimas",
        "surname": "Sergantis",
        "personalCode": "20456410",
        "condition": "Lengva"
    }
	
	  {
        "id": 1,
        "name": "Kostas",
        "surname": "Mentalitas",
        "personalCode": "14456410",
        "condition": "Sunki"
    }
	  {
        "id": 1,
        "name": "Laurynas",
        "surname": "Pavardenis",
        "personalCode": "87456410",
        "condition": "Lengva"
    }
http://localhost/api/v1/patients/

READ:
(all)
http://localhost/api/v1/patients/

(id)
http://localhost/api/v1/patients/1

UPDATE:
'  {
        "id": 1,
        "name": "Aurimas",
        "surname": "Sergantis",
        "personalCode": "20456410",
        "condition": "Sunki"
    }
	http://localhost/api/v1/patients/1

DELETE:
http://localhost/api/v1/patients/1
