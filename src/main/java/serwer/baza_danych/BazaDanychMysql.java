package serwer.baza_danych;

import entities.Gra;
import entities.StanPlanszy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BazaDanychMysql extends BazaDanych {
  private SessionFactory factory_;
  public BazaDanychMysql() {
    try {
      factory_ = new Configuration().configure(
          "konfiguracje_baz_danych/mysql/hibernate_serwer.cfg.xml").buildSessionFactory();
      this.ustawCzyPolaczono(true);
    } catch(Throwable ex) {
      System.err.println("Nie udalo sie polaczyc z baza danych");
    }
  }

  @Override
  public void wprowadzGre(Gra gra) {
    Transaction tx = null;

    try (Session sesja = factory_.openSession()) {
      tx = sesja.beginTransaction();
      sesja.persist(gra);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null)
        tx.rollback();
      System.out.println("Nie udalo sie wprowadzic gry do bazy danych.");
    }
  }

  @Override
  public void wprowadzRuch(StanPlanszy stanPlanszy) {
    Transaction tx = null;

    try (Session sesja = factory_.openSession()) {
      tx = sesja.beginTransaction();
      sesja.persist(stanPlanszy);
      tx.commit();
    } catch(HibernateException e) {
      if(tx != null)
        tx.rollback();
      System.out.println("Nie udalo sie wprowadzic ruchu do bazy danych");
    }
  }
}
