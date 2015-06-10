package invoice.db;

import invoice.model.Invoice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class InvoiceDAO {

	private final EntityManagerFactory emf;

	public InvoiceDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public Invoice getInvoice(Long id) {
		EntityManager em = emf.createEntityManager();
		try {
			return getInvoiceFromDb(em, id);
		} finally {
			em.close();
		}
	}

	private Invoice getInvoiceFromDb(EntityManager em, Long id) {
		EntityTransaction et = em.getTransaction();
		try {
			// TODO provide implementation
			return null;
		} finally {
			if (et.isActive()) {
				et.rollback();
			}
		}
	}
}
