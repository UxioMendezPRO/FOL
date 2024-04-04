/**
 * Calculadora de prestaciones por incapacidad
 */

package calculadoras;

import java.util.Scanner;

public class Baja {

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    System.out.println("Tipo de incapacidad\n 1. Comun \n 2.Profesional");
    boolean comun = true;
    switch (in.nextInt()) {
      case 1:
        comun = true;
        break;
      case 2:
        comun = false;
      default:
        break;
    }

    System.out.println("Dias de baja");
    int dias = in.nextInt();

    System.out.println("Base de cotización del último mes");
    int base = in.nextInt();

    double prestación = 0;
    // Comun
    if (comun) {
      double cot = base / 30;
      if (dias > 3) {
        dias -= 3;
      }
      if (dias < 17) {
        prestación += dias * cot * 0.6;
      } else {
        prestación += 17 * cot * 0.6;
        dias -= 17;
        prestación += dias * cot * 0.75;

      }

      // Profesional
    } else {
      System.out.println("Horas extras mes");
      int extrasMes = in.nextInt();
      System.out.println("Horas extras año");
      int extrasAño = in.nextInt();
      double cot = (base - extrasMes) / 30 + extrasAño / 365;
      prestación = dias * cot * 0.75;

    }

    System.out.println(prestación);
    in.close();
  }
}