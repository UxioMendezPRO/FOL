/**
 * Esta clase calcula la representación de un comité de empresa
 */
package calculadoras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Elecciones {

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    System.out.println("Representantes");
    int representantes = in.nextInt();

    // Técnicos y administrativos
    System.out.println("Total Técnicos y Administrativos");
    int electoresTyA = in.nextInt();
    System.out.println("Votos válidos");
    int votosTyA = in.nextInt();
    System.out.println("En blanco");
    int blancoTyA = in.nextInt();
    System.out.println("Nulos");
    int nulosTyA = in.nextInt();

    // calculo del 5%
    double minimoTyA = Math.round((votosTyA + blancoTyA) * 0.05);

    System.out.println("Total candidaturas");
    int total = in.nextInt();
    HashMap<String, Integer> candidaturasTyA = new HashMap<>();
    for (int i = 1; i <= total; i++) {
      System.out.println("Sindicato " + i);
      String sindicato = in.next();
      System.out.println("Votos " + sindicato);
      int votos = in.nextInt();
      if (votos >= minimoTyA) {
        candidaturasTyA.put(sindicato, votos);
      }
    }

    // Especialistas y no cualificados
    System.out.println("Total Especialistas y no cualificados");
    int electoresEnC = in.nextInt();
    System.out.println("Votos válidos");
    int votosEnC = in.nextInt();
    System.out.println("En blanco");
    int blancoEnC = in.nextInt();
    System.out.println("Nulos");
    int nulosEnC = in.nextInt();

    // calculo del 5%
    double minimoEnC = Math.round((votosEnC + blancoEnC) * 0.05);

    System.out.println("Total candidaturas");
    total = in.nextInt();
    HashMap<String, Integer> candidaturasEnC = new HashMap<>();
    for (int i = 1; i <= total; i++) {
      System.out.println("Sindicato " + i);
      String sindicato = in.next();
      System.out.println("Votos " + sindicato);
      int votos = in.nextInt();
      if (votos >= minimoEnC) {
        candidaturasEnC.put(sindicato, votos);
      }
    }

    // Representantes por colegio
    int representantesTyA = representantes * electoresTyA / (electoresEnC + electoresTyA);
    int representantesEnC = representantes * electoresEnC / (electoresEnC + electoresTyA);

    // Resultados Técnicos y Administrativos
    int suma = 0;
    for (String sindicato : candidaturasTyA.keySet()) {
      suma += candidaturasTyA.get(sindicato);
    }
    int votosNecesarios = suma / representantesTyA;

    ArrayList<Integer> resultadosTyA = new ArrayList<>();

    for (String sindicato : candidaturasTyA.keySet()) {
      resultadosTyA.add(candidaturasTyA.get(sindicato) / votosNecesarios);
    }

    // Resultados Especialistas no Cualificaos

    suma = 0;
    for (String sindicato : candidaturasEnC.keySet()) {
      suma += candidaturasEnC.get(sindicato);
    }
    votosNecesarios = suma / representantesEnC;

    ArrayList<Integer> resultadosEnC = new ArrayList<>();

    for (String sindicato : candidaturasEnC.keySet()) {
      resultadosEnC.add(candidaturasEnC.get(sindicato) / votosNecesarios);
    }

    // Imprimir resultados
    System.out.println("Resultados Técnicos y Administrativos");
    for (int i = 0; i < resultadosTyA.size(); i++) {
      System.out.println(resultadosTyA.get(i));
    }
    System.out.println("Resultados Especialistas no Cualificados");
    for (int i = 0; i < resultadosEnC.size(); i++) {
      System.out.println(resultadosEnC.get(i));
    }

  }

}
