package calculadoras;

import java.util.Scanner;

public class nomina {

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		// Grupo cotizacion y bases minimas y maximas
		System.out.println("Grupo");
		int grupo = in.nextInt();
		int diasMes = 0;
		if (grupo > 7) {
			System.out.println("Dias del mes");
			diasMes = in.nextInt();
		}
		double baseMin, baseMax = 4495.5;
		switch (grupo) {
			case 1:
				baseMin = 1759.5;
				break;
			case 2:
				baseMin = 1459.2;
				break;
			case 3:
				baseMin = 1269.3;
				break;
			case 4, 5, 6, 7:
				baseMin = 1260.0;
				break;
			case 8, 9, 10, 11:
				baseMin = diasMes * 42;
				baseMax = diasMes * 149.85;
				break;
			default:
				break;
		}

		// Tipo contrato
		System.out.println("1. temporal\n" + "2. Otro");
		int menu = in.nextInt();
		boolean temporal = false;
		if (menu == 1) {
			temporal = true;
		}
		// IRPF
		System.out.println("Porcentaje IRPF");
		double tipoIrpf = in.nextDouble();

		// Salario base
		System.out.println("Salario base");
		double sb = in.nextDouble();
		sb = calcularSB(sb, grupo, diasMes);

		// Complementos salariales
		System.out.println("cuantos Complementos salariales");
		int pluses = in.nextInt();
		double complementos = calcularComplementos(pluses, grupo, diasMes);

		// Extrasalariales
		System.out.println("cuantos Complementos extrasalariales");
		pluses = in.nextInt();
		double dietas = calcularDietas(pluses);

		// Horas extra
		System.out.println("Horas extra");
		double horasExtra = in.nextDouble();

		// Pagas extra
		System.out.println("Paga extra");
		double extra = in.nextDouble();
		if (grupo > 7) {
			extra = extra * 365;
		}
		double prorrateo = calcularProrrata(extra);

		double totalDevengado = calcularDevengo(sb, complementos, dietas, extra, horasExtra, prorrateo);
		double retenciones = calcularRetenciones(sb, complementos, prorrateo, tipoIrpf, horasExtra, dietas, extra, temporal, totalDevengado);
		System.out.println("Líquido a percibir: " + (totalDevengado - retenciones));

	}

	public static double calcularSB(double sb, int grupo, int diasMes) {

		if (grupo > 7) {
			return sb * diasMes;
		} else
			return sb;
	}

	public static double calcularComplementos(int pluses, int grupo, int diasMes) {
		double complementos = 0;

		for (int i = 0; i < pluses; i++) {
			System.out.println("importe plus");
			complementos += in.nextDouble();
		}
		if (grupo > 7) {
			complementos = complementos * diasMes;
		}
		return complementos;
	}

	public static double calcularDietas(int pluses) {
		double dietas = 0;
		for (int i = 0; i < pluses; i++) {
			System.out.println("importe plus");
			dietas += in.nextDouble();
		}
		return dietas;
	}

	public static double calcularProrrata(double extra) {

		return extra * 2 / 12;

	}

	public static double calcularDevengo(double sb, double complementos, double dietas, double extra, double horasExtra, double prorrateo) {
		boolean prorrateadas = false;
		boolean cobraExtra = false;
		System.out.println("Prorrateadas?\n" + "1. SI\n" + "2. NO");
		int menu = in.nextInt();
		if (menu == 1) {
			extra = extra * 2 / 12;
			prorrateadas = true;
		} else {
			System.out.println("cobra ese mes\n + \"1. SI\\n" + //
					"\" + \"2. NO\"");
			menu = in.nextInt();
			if (menu == 1) {
				cobraExtra = true;
			}
		}
		double totalDevengado = sb + complementos + dietas + horasExtra;
		if (prorrateadas) {
			totalDevengado += prorrateo;
		}
		if (cobraExtra) {
			totalDevengado += extra / 2;
		}
		System.out.println("Total Devengado: " + totalDevengado);
		return totalDevengado;
	}

	public static double calcularRetenciones(double sb, double complementos, double prorrateo, double tipoIrpf,
			double horasExtra, double dietas, double extra, boolean temporal, double totalDevengado) {
		// Bases de cotizacion
		double bccc = sb + complementos + prorrateo;
		double bccp = bccc + horasExtra;
		double baseIrpf = totalDevengado - dietas;

		// Calculos retenciones SS
		double contigenciasComunes = bccc * 0.047;
		double mei = bccc * 0.001;
		double desempleo;
		if (!temporal) {
			desempleo = bccp * 0.0155;
		} else {
			desempleo = bccp * 0.016;
		}
		double formacion = bccp * 0.001;
		double extrasCot = horasExtra * 0.047;
		

		// Retencion IRPF
		double irpf = baseIrpf * tipoIrpf / 100;

		// Prints
		System.out.println("BCCC " + bccc);
		System.out.println("BCCP " + bccp);
		System.out.println("IRPF " + baseIrpf);
		System.out.println("CC " + contigenciasComunes);
		System.out.println("MEI " + mei);
		System.out.println("Desempleo " + desempleo);
		System.out.println("Formación " + formacion);
		System.out.println("Extras " + extrasCot);
		System.out.println("Retencion SS " + (contigenciasComunes + mei + desempleo + formacion + extrasCot));
		System.out.println("Retención IRPF " + irpf);

		return contigenciasComunes + mei + desempleo + formacion + extrasCot + irpf;

	}

}
