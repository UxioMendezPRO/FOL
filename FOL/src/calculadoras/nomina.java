package calculadoras;

import java.util.Scanner;

public class nomina {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

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
		if (grupo > 7) {
			sb = sb * diasMes;
		}

		// Complementos salariales
		double complementos = 0;
		System.out.println("cuantos Complementos salariales");
		int pluses = in.nextInt();
		for (int i = 0; i < pluses; i++) {
			System.out.println("importe plus");
			complementos += in.nextDouble();
		}
		if (grupo > 7) {
			complementos = complementos * diasMes;
		}

		// Extrasalariales
		double dietas = 0;
		System.out.println("cuantos Complementos extrasalariales");
		pluses = in.nextInt();
		for (int i = 0; i < pluses; i++) {
			System.out.println("importe plus");
			dietas += in.nextDouble();
		}

		// Horas extra
		System.out.println("Horas extra");
		double horasExtra = in.nextDouble();

		// Pagas extra
		System.out.println("Paga extra");
		double extra = in.nextDouble();
		if (grupo > 7) {
			extra = extra * 365;
		}

		// Prorrateo
		double prorrateo = extra * 2 / 12;
		boolean prorrateadas = false;
		boolean cobraExtra = false;
		System.out.println("Prorrateadas?\n" + "1. SI\n" + "2. NO");
		menu = in.nextInt();
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

		// Total devengado
		double totalDevengado = sb + complementos + dietas + horasExtra;
		if (prorrateadas) {
			totalDevengado += prorrateo;
		}
		if (cobraExtra) {
			totalDevengado += extra / 2;
		}

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

		// liquido a percibir
		double liquido = totalDevengado - contigenciasComunes - mei - desempleo - formacion - extrasCot - irpf;

		// Prints
		System.out.println("Total devengado" + totalDevengado);
		System.out.println("BCCC " + bccc);
		System.out.println("BCCP " + bccp);
		System.out.println("IRPF " + baseIrpf);
		System.out.println("CC " + contigenciasComunes);
		System.out.println("MEI " + mei);
		System.out.println("Desempleo " + desempleo);
		System.out.println("Formación " + formacion);
		System.out.println("Extras " + extrasCot);
		System.out.println(contigenciasComunes + mei + desempleo + formacion + extrasCot + irpf);
		System.out.println("Líquido a percibir " + liquido);

	}

}
