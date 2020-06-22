package br.com.pauta.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class PautaUtil {
  public static String formatarData(LocalDateTime data) {
    return DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")
      .format(data);
  }
}
