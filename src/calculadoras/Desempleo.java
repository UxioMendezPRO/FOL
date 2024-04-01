/**
 * Esta clase calcula prestaciones por desempleo
 */

package calculadoras;

import java.util.Scanner;

public class Desempleo {

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    System.out.println("Dias de paro correspondientes");
    int dias = in.nextInt();

    System.out.println("BCCC últimos 180 días");
    int bccc = in.nextInt();

    double br = bccc / 180;

    double seisMeses = br * 0.7 * 30;
    double restoMeses = 0;
    if (dias > 180) {
      dias -= 180;
      restoMeses = br * 0.5 * 30;
    }

    System.out.println("Primeros seis meses " + seisMeses);
    System.out.println("Resto " + restoMeses);

  }
}
