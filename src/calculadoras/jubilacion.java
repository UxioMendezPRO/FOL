package calculadoras;

import java.util.Scanner;

public class jubilacion {

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    // Años y meses cotizados
    int añosCot = in.nextInt();
    int mesesCot = in.nextInt();

    int meses = añosCot * 12 + mesesCot;

    double bccc = in.nextDouble();

    double baseCotizacion = bccc * 300 / 350;

    double porcentaje = 50;
    // Suma porcentajes en funcion del los meses cotizados
    if (meses > 15 * 12) {
      porcentaje += 21;
    }
    if (meses > 15 * 12 + 106) {
      porcentaje += 19;
    }
    if (meses > 15 * 12 + 106 + 146) {
      porcentaje += meses - 15 * 12 + 106 + 146 * 0.19;
    }
    //Calcula el total

    double pension = baseCotizacion * porcentaje / 100;
    System.out.println(porcentaje);
    System.out.println(pension);
    in.close();
  }

}
