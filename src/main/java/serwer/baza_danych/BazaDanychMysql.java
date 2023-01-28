package serwer.baza_danych;

import org.hibernate.SessionFactory;
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
}
