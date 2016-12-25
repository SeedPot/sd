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
			long fibonacci = (long)respuesta;
			return "Para N = " + n  + " terminos, la serie de Fibonacci es " + fibonacci;
		} while (n >= 2);

	}

	public long taylor(int n){
		double respuesta = 0.0;

		long taylor = (long)respuesta;
		return return "Para N = " + n  + " terminos, la serie de Taylor es " + taylor;
	}
}
