import java.util.ArrayList;

public class Serie {

	public Serie() {

	}

	public String fibonacci(int n) {
		double respuesta = 0.0;
		do {
<<<<<<< HEAD
			int i;

			double fibo1 = 1;
			double fibo2 = 1;
=======
			double respuesta = 0;

			int fibo1 = 1;
			int fibo2 = 1;
>>>>>>> a45037a6dde6ba4d3211c2e4a13192a261ecd91b

			for (int i = 2; i <= n; i++) {

				fibo2 = fibo1 + fibo2;
				fibo1 = fibo2 - fibo1;
				respuesta = fibo1;
			}
			return Double.toString(respuesta);
		} while (n >= 2);
<<<<<<< HEAD
=======

>>>>>>> a45037a6dde6ba4d3211c2e4a13192a261ecd91b
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

resultado*=i;*/

		return 0;
<<<<<<< HEAD
	}*/
=======
	}
>>>>>>> a45037a6dde6ba4d3211c2e4a13192a261ecd91b

}