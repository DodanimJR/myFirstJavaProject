package net.javajunior.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.javajunior.hibernate.model.Accounts;
import net.javajunior.hibernate.model.CurrentAccount;
import net.javajunior.hibernate.model.SavingAccount;
import net.javajunior.hibernate.util.HibernateUtil;


public class AccountsDao {
	
	public void saveAccount(Accounts account) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		
			transaction = session.beginTransaction();
		
			session.save(account);
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		
		
		
		}
		
	
	
	
	}
	public void saveAccount(SavingAccount account) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		
			transaction = session.beginTransaction();
		
			session.save(account);
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		
		
		
		}
		
	
	
	
	}
	public void saveAccount(CurrentAccount account) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		
			transaction = session.beginTransaction();
		
			session.save(account);
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		
		
		
		}
		
	
	
	
	}

	
	@SuppressWarnings("unchecked")
	public List<Accounts> getAllAccounts() {
		
		Transaction transaction = null;
		List<Accounts> cuentas= null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			cuentas = session.createQuery("from Accounts").list();
			transaction.commit();
			
		}catch(Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
		}
		return cuentas;
		
	}
	
}
