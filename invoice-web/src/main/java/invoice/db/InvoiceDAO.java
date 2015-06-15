package invoice.db;

import invoice.model.Invoice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class InvoiceDAO {

	private final EntityManagerFactory emf;

	public InvoiceDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public Invoice getInvoice(Long id) {
		EntityManager em = emf.createEntityManager();
		try {
			return getInvoiceFromDb(em, id);
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	public void createInvoice(Invoice invoice) {
		EntityManager em = emf.createEntityManager();
		try {
			createInvocieInDb(em, invoice);
		} finally {
			em.close();
		}
	}

	public void deleteInvoice(Long id) {
		EntityManager em = emf.createEntityManager();
		try {
			Invoice invoice = em.find(Invoice.class, id);
			if (invoice != null) {
				deleteInvoiceFromDb(em, invoice);
			}
		} catch (Exception e) {

		} finally {
			em.close();
		}
	}

	private void deleteInvoiceFromDb(EntityManager em, Invoice invoice) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.remove(invoice);
			et.commit();
		} finally {
			if (et.isActive()) {
				et.rollback();
			}
		}
	}

	private void createInvocieInDb(EntityManager em, Invoice invoice) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(invoice);
			et.commit();
		} finally {
			if (et.isActive()) {
				et.rollback();
			}
		}
	}

	private Invoice getInvoiceFromDb(EntityManager em, Long id) {
		Query q = em.createNamedQuery("findInvoiceById");
		q.setParameter("id", id);
		Invoice invoice = (Invoice) q.getSingleResult();
		return invoice;
	}
}
