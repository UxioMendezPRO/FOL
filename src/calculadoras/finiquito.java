/**
 * Esta clase calcula indemnizaciones por despido
 */

package calculadoras;

import java.util.Scanner;

public class finiquito {

  public static void main(String[] args) {
    
    Scanner in = new Scanner(System.in);


    //Fecha del despido
    System.out.println("Día despido");
    int diaDespido = in.nextInt();

    //Vaciones 
    System.out.println("Vaciones disfrutadas:");
    int vaciones = in.nextInt();
    vaciones -= 30;

  


  }

}
