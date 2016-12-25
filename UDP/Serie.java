import java.util.ArrayList;

public class Serie {

	public Serie() {

	}

	public String fibonacci(int n) {
		double respuesta = 0.0;
		do {
			int i;

			double fibo1 = 1;
			double fibo2 = 1;

			respuesta = 0;

			for (i = 2; i <= n; i++) {

				fibo2 = fibo1 + fibo2;
				fibo1 = fibo2 - fibo1;
				respuesta = fibo1;
			}
			long fibonacci = (long) respuesta;
			return "Para N = " + n + " terminos, la serie de Fibonacci es " + fibonacci;
		} while (n >= 2);

	}

	public String taylor(int n, int x) {
		double sumatoria=0.0,t = 0.0;
		for (int i = 0; i < n; i++) {
			//elevamos x a la i y dividimos por el factorial del número
			t = Math.pow(x, i) / factorial(i);
			System.out.println(t);
			sumatoria += t;
		}
		System.out.println("La serie de taylor calculada es: " + sumatoria);
		return "Para N = " + n + " terminos, X = 2 valor base, la serie de Taylor es " + sumatoria;
	}

	public static double factorial(int numero) {
		double resultado = 1;

		for (int i = 1; i <= numero; i++) {
			resultado *= i;
		}

		return (resultado);
	}
}
