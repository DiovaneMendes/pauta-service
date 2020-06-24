package br.com.pauta.util;

import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class PautaUtil {
  public static String formatarData(LocalDateTime data) {
    if (ObjectUtils.isEmpty(data)) return null;
    return DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")
      .format(data);
  }
}