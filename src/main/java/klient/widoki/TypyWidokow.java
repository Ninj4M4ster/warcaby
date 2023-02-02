package klient.widoki;

/**
 * Enumerator reprezentujacy dostepne widoki.
 */
public enum TypyWidokow {
  /** Widok menu glownego, w ktorym zaprosic mozemy jednego z dostepnych graczy do pokoju. */
  WIDOK_GRACZY_ONLINE,

  /** Widok pokoju, w ktorym mozna wybrac jedne z dostepnych zasad,
   * komunikowac sie z drugim graczem oraz rozpoczac rozgrywke */
  WIDOK_POKOJU,

  /** Widok gry, dajacy mozliwosc przeprowadzenia rozgrywki w warcaby */
  WIDOK_GRY,

  /** Widok ogladania przebiegu zapisanej w bazie danych gry */
  WIDOK_OGLADANIA_GRY
}
