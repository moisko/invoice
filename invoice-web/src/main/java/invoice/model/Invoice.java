package invoice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "findInvoiceById", query = "SELECT i FROM Invoice i WHERE i.id = :id")
@Table(name = "INVOICE")
public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private InvoiceDetails details;
	private String number;
	private Date date;
	private String recipient;
	private String city;
	private String street;
	private String accountablePerson;
	private String invoiceNumberId;
	private String vat;
	private String provider;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INVOICE_ID", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(optional = true, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "INVOICE_DETAILS_ID", nullable = true)
	public InvoiceDetails getDetails() {
		return details;
	}

	public void setDetails(InvoiceDetails details) {
		this.details = details;
	}

	@Column(name = "INVOICE_NUMBER", nullable = false)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "RECIPIENT", nullable = false)
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	@Column(name = "CITY", nullable = false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "STREET", nullable = false)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "ACCOUNTABLE_PERSON", nullable = false)
	public String getAccountablePerson() {
		return accountablePerson;
	}

	public void setAccountablePerson(String accountablePerson) {
		this.accountablePerson = accountablePerson;
	}

	@Column(name = "INVOICE_NUMBER_ID", nullable = false)
	public String getInvoiceNumberId() {
		return invoiceNumberId;
	}

	public void setInvoiceNumberId(String invoiceNumberId) {
		this.invoiceNumberId = invoiceNumberId;
	}

	@Column(name = "VAT", nullable = false)
	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	@Column(name = "PROVIDER", nullable = false)
	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

}
