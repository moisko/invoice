package invocie.db;

import static org.junit.Assert.assertNotNull;
import invoice.dao.InvoiceDAO;
import invoice.gson.DatetimeDeserializer;
import invoice.gson.DatetimeSerializer;
import invoice.model.Invoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class InvoiceDAOTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private static final String PERSISTENCE_UNIT_NAME = "InvoiceTest";

	private static EntityManagerFactory emf;
	private static InvoiceDAO invoiceDAO;

	@BeforeClass
	public static void beforeClass() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		invoiceDAO = new InvoiceDAO(emf);
	}

	@Test
	public void testCRUDOperations() throws Exception {
		invoiceDAO
				.persistInvoice(fromJsonFile("src/test/resources/invoice.json"));
		Invoice invoice = invoiceDAO.getInvoice(1L);
		assertNotNull(invoice);
		invoiceDAO.deleteInvoice(1L);
	}

	@Test
	public void testGetUnexistingInvoiceRecord() throws Exception {
		exception.expect(NoResultException.class);
		invoiceDAO.getInvoice(2L);
	}

	@Test
	public void testDeleteUnexistingInvoice() throws Exception {
		exception.expect(IllegalArgumentException.class);
		invoiceDAO.deleteInvoice(3L);
	}

	@Test
	public void testDeserializeInvoiceWithInvalidDate() throws Exception {
		exception.expect(NumberFormatException.class);
		fromJsonFile("src/test/resources/invoice_with_invalid_date_format.json");
	}

	@AfterClass
	public static void afterClass() {
		emf.close();
	}

	private Invoice fromJsonFile(String path) throws JsonSyntaxException,
			JsonIOException, FileNotFoundException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DatetimeDeserializer());
		gsonBuilder.registerTypeAdapter(Date.class, new DatetimeSerializer());
		Gson gson = gsonBuilder.create();
		return gson.fromJson(new FileReader(new File(path)), Invoice.class);
	}
}
