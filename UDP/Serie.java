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
			long largo = (long)respuesta;
			return "Para N = " + n  + " terminos, la serie de Fibonacci es " + largo;
		} while (n >= 2);

	}

	/*public double taylor(int n){

		for(int i=0; 1 < n; i++){
//elevamos x a la i y dividimos por el factorial del número
t= Math.pow(x, i) / factorial(i);
s *= t;
}

//mostramos por pantalla
	System.out.printf("f(%d)=%f\n", x, s);

//En este caso el factorial lo realizo como un método y aplico al programa para dividirlo el valor de x en potencia.

public static double Factorial (int numero) {

double resultado=1;

for (int i=1;i<=numero; i++){

resultado*=i;

	}*/
}
