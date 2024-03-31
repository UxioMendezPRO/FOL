package calculadoras;

import java.util.Scanner;

public class jubilacion {

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    // Meses cotizados
    System.out.println("Total meses cotizados");
    int meses = in.nextInt();
    
    System.out.println("Base cotización");
    double bccc = in.nextDouble();

    double baseCotizacion = bccc * 300 / 350;

    double porcentaje = 0;
    // Suma porcentajes en funcion del los meses cotizados  
    if (meses >180) {
      porcentaje += 50;
      meses -= 180;
    }
    if (meses > 106) {
      porcentaje += 106 * 0.21;
      meses -= 106;
    }
    if (meses > 146) {
      porcentaje += 146 * 0.19;
      meses -= 146; 
    }
    if (meses > 0) {
      porcentaje += meses *0.19;
    }
    // Calcula el total

    double pension = baseCotizacion * (porcentaje / 100);
    System.out.println(porcentaje);
    System.out.println(pension);
    in.close();
  }

}
