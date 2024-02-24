package calculadoras;

import java.util.Scanner;

public class embargo {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		double smi = 1080;
		double salario = in.nextDouble();
		double deuda = in.nextDouble();

		System.out.println("Embargo mensual: " + calcularEmbargo(salario, smi));
		calcularTiempo(calcularEmbargo(salario, smi), deuda);

	}

	public static double calcularEmbargo(double salario, double smi) {
		double embargo = 0;

		if (salario > smi * 2) {
			embargo += smi * 0.3;
		} else {
			embargo = (salario - smi) * 0.3;
		}

		if (salario > smi * 3) {
			embargo += smi * 0.5;
		} else {
			embargo = (salario - smi * 2) * 0.5;
		}

		if (salario > smi * 4) {
			embargo += smi * 0.6;
		} else {
			embargo = (salario - smi * 3) * 0.6;
		}

		if (salario > smi * 5) {
			embargo += smi * 0.75;
		} else {
			embargo = (salario - smi * 4) * 0.75;
		}

		if (salario > smi * 6) {
			embargo += (salario - smi * 5) * 0.9;
		}
		return embargo;

	}

	public static void calcularTiempo(double embargo, double deuda) {

		int contador = 0;
		double ultimoMes = 0;
		double total = 0;

		while (total < deuda) {
			total += embargo;
			contador++;
		}
		if (total != deuda) {
			ultimoMes = total - deuda;
		}

		System.out.println("meses: " + contador);
		System.out.println("ultimo mes: " + ultimoMes);
	}

}
