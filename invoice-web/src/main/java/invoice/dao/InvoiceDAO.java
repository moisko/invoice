package invoice.dao;

import invoice.model.Invoice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
		} finally {
			em.close();
		}
	}

	public void persistInvoice(Invoice invoice) {
		EntityManager em = emf.createEntityManager();
		try {
			persistInvocieInDb(em, invoice);
		} finally {
			em.close();
		}
	}

	public void deleteInvoice(Long id) {
		EntityManager em = emf.createEntityManager();
		try {
			Invoice invoice = em.find(Invoice.class, id);
			deleteInvoiceFromDb(em, invoice);
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

	private void persistInvocieInDb(EntityManager em, Invoice invoice) {
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
