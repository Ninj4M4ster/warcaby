package serwer.baza_danych;

import entities.Gra;
import entities.StanPlanszy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import org.hibernate.HibernateException;

/**
 * Klasa reprezentujaca polaczenie z baza danych MySql.
 */
public class BazaDanychMysql extends BazaDanych {
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Konstruktor. Tworzy obiekt obslugujacy interakcje z baza danych.
   */
  public BazaDanychMysql() {
    try {
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("serwer.warcaby");
      entityManager = factory.createEntityManager();
      this.ustawCzyPolaczono(true);
    } catch(Throwable ex) {
      System.err.println("Nie udalo sie polaczyc z baza danych");
      System.err.println(ex);
    }
  }

  /**
   * Metoda odpowiedzialna za dodanie rekordu gry do tabeli.
   *
   * @param gra Rekord gry do wprowadzenia.
   */
  @Override
  public void wprowadzGre(Gra gra) {
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(gra);
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null)
        transaction.rollback();
      System.out.println("Nie udalo sie wprowadzic gry do bazy danych.");
    }
  }

  /**
   * Metoda odpowiedzialna za wprowadzenie ruchu do bazy danych.
   *
   * @param stanPlanszy Rekord ruchu do wprowadzenia.
   */
  @Override
  public void wprowadzRuch(StanPlanszy stanPlanszy) {
    EntityTransaction transaction = null;

    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(stanPlanszy);
      transaction.commit();
    } catch(HibernateException e) {
      if(transaction != null)
        transaction.rollback();
      System.out.println("Nie udalo sie wprowadzic ruchu do bazy danych");
    }
  }
}
