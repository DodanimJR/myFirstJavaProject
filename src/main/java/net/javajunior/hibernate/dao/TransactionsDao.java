package net.javajunior.hibernate.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import net.javajunior.hibernate.model.Transactions;
import net.javajunior.hibernate.util.HibernateUtil;

public class TransactionsDao {
	
	public void saveTransaction(Transactions transaccion) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		
			transaction = session.beginTransaction();
		
			session.save(transaccion);
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		
		}
		
	
	
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Transactions> getAllTransactions() {
		
		Transaction transaction = null;
		List<Transactions> transacciones= null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			transacciones = session.createQuery("from Transactions").list();
			transaction.commit();
			
		}catch(Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
		}
		return transacciones;
	}
		
	
		public List<Transactions> getTransactionByAccountN(int nocuenta) {
			Transaction transaction = null;
			List<Transactions> transactionsaccount = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				CriteriaBuilder cb = session.getCriteriaBuilder();
				CriteriaQuery<Transactions> cr = cb.createQuery(Transactions.class);
				Root<Transactions> root = cr.from(Transactions.class);
				cr.select(root);
			
				transaction = session.beginTransaction();
			
				Query<Transactions> query = session.createQuery(cr);
				transactionsaccount = query.getResultList();
				
				transaction.commit();
				
				
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			
			}
			return transactionsaccount;
		
	}
	
}
