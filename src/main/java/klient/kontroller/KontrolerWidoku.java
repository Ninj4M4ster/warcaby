package klient.kontroller;

import klient.model.Model;

public interface KontrolerWidoku {
  void przekazModel(Model model);
  void przekazGlownyKontroler(KontrolerAplikacji kontrolerGlowny);
}
