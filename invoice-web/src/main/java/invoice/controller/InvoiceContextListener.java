package invoice.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InvoiceContextListener implements ServletContextListener {

	private static final String PERSISTENCE_UNIT_NAME = "Invoice";

	private EntityManagerFactory emf;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		ServletContext sc = event.getServletContext();
		sc.setAttribute("emf", emf);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		emf.close();
	}

}
