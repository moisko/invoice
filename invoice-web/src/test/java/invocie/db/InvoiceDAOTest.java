package invocie.db;

import static org.junit.Assert.assertNotNull;
import invoice.db.InvoiceDAO;
import invoice.gson.DatetimeDeserializer;
import invoice.gson.DatetimeSerializer;
import invoice.model.Invoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class InvoiceDAOTest {

	private static final String PERSISTENCE_UNIT_NAME = "InvoiceTest";

	private static EntityManagerFactory emf;

	private static InvoiceDAO invoiceDAO;

	@BeforeClass
	public static void beforeClass() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		invoiceDAO = new InvoiceDAO(emf);
	}

	@Test
	public void testPersistAndGetInvoice() throws Exception {
		Invoice invoice = fromJsonFile("src/test/resources/invoice.json");
		invoiceDAO.persistInvoice(invoice);
		assertNotNull(invoiceDAO.getInvoice(1L));
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
