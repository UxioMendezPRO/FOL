/**
 * Esta clase calcula indemnizaciones por despido
 */

package calculadoras;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class finiquito {

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    // Tipo despido
    boolean objetivo = false, improcedente = false, temporal = false;
    System.out.println("Despido \n 1.Objetivo\n 2.Improcedente\n 3.Temporal");
    int menu = in.nextInt();
    switch (menu) {
      case 1:
        objetivo = true;
        break;
      case 2:
        improcedente = true;
        break;
      case 3:
        temporal = true;
        break;
      default:
        break;
    }

    System.out.println("Grupo");
    int grupo = in.nextInt();

    // Fecha del despido
    int diaDespido;
    System.out.println("Fecha despido yyyy/MM/dd");
    LocalDate fechaDespido = LocalDate.of(in.nextInt(), in.nextInt(), diaDespido = in.nextInt());

    // Antigüedad
    System.out.println("Antiguedad yyyy/MM/dd");
    LocalDate fechaInicio = LocalDate.of(in.nextInt(), in.nextInt(), in.nextInt());
    // Vaciones
    System.out.println("Vaciones disfrutadas:");
    int vacionesDisfrutadas = in.nextInt();

    // IRPF
    System.out.println("Porcentaje IRPF");
    double tipoIrpf = in.nextDouble();

    // Salario base
    System.out.println("Salario base");
    double sb = in.nextDouble();
    sb = nomina.calcularSB(sb, grupo, menu);

    // Complementos
    System.out.println("cuantos Complementos salariales");
    int pluses = in.nextInt();
    double complementos = nomina.calcularComplementos(pluses, grupo, 30);

    // Extrasalariales
    System.out.println("cuantos Complementos extrasalariales");
    pluses = in.nextInt();
    double dietas = nomina.calcularDietas(pluses);

    // Horas extra
    System.out.println("Horas extra");
    double horasExtra = in.nextDouble();

    // Pagas extra
    System.out.println("Paga extra");
    double extra = in.nextDouble();
    double prorrateo = nomina.calcularProrrata(extra, grupo, 30);

    nomina.calcularDevengo(sb, complementos, dietas, extra, horasExtra, prorrateo);

    nomina.calcularRetenciones(sb, complementos, prorrateo, tipoIrpf, horasExtra, dietas, extra, temporal, prorrateo);

    // Vacaciones
    int mesesTrabajados = Period.between(fechaInicio, fechaDespido).getMonths();
    int vacionesCorrespondientes = 30 / 12 * mesesTrabajados;
    double salarioDiario = (sb + complementos + prorrateo) * 12 / 365;
    double importeVacaciones = (vacionesCorrespondientes - vacionesDisfrutadas) * salarioDiario;
    System.out.println("Vacaciones " + importeVacaciones);

    // Indemnización
    double indemnizacion = 0;
    if (objetivo) {
      indemnizacion = 20 / 12 * mesesTrabajados * salarioDiario;
    }
    if (improcedente) {
      indemnizacion = 33 / 12 * mesesTrabajados * salarioDiario;
    }
    if (temporal) {
      indemnizacion = 12 / 12 * mesesTrabajados * salarioDiario;
    }
    System.out.println("Indemnizacion " + indemnizacion);

  }

}
