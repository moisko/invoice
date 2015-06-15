# invoice
data warehouse software

POST -> /invoice-web/invoice/create

Example:

http://www.boratino.com/invoice-web/invoice/create
```
{
	"number": "1234",
	"date": "1434309757",
	"recipient": "Boris",
	"city": "Sofia",
	"street": "Solunska",
	"accountablePerson": "Ceco",
	"invoiceNumberId": "1234",
	"vat": "20",
	"provider": "provider",
	"details": {
		"code": 12345,
		"name": "shoes",
		"quantity": 3,
		"size": 35,
		"currency": "BGN"
	}
}
```

GET -> /invoice-web/invoice/get/{id}

Example:

http://www.boratino.com/invoice-web/invoice/get/1

PUT -> /invoice-web/invoice/update/{id}

Example:

http://www.boratino.com/invoice-web/invoice/update/

NOT SUPPORTED!

DELETE -> /invoice-web/invoice/delete/{id}

Example:

http://www.boratino.com/invoice-web/invoice/delete/1

NOT SUPPORTED!
