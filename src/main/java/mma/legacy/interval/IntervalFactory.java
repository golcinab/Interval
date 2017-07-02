package mma.legacy.interval;


/**
 * Esta clase abstracta se ha creado por comodidad.
 * Crea automaticamente intervalos
 *
 * @author Agustin
 */
public class IntervalFactory {

	/**
	 * Ocultar constructor publico de clase de utilidades
	 */
	private IntervalFactory(){
		throw new IllegalStateException("Clase de utilidades");
	}

	/**
	 * Creacion de intervalo indicando los limites y el tipo de intervalo como parametro
	 * @param minimum limite inferior
	 * @param maximum limite superior
	 * @param opening tipo de intervalo
	 * @return devuelve el intervalo creado.
	 */
	public static Interval createInterval(double minimum, double maximum, Opening opening) {
		if (opening == Opening.BOTH_OPENED) return new IntervalOpened(minimum, maximum, opening);
		if (opening == Opening.RIGHT_OPENED) return new IntervalRightOpened(minimum, maximum, opening);
		if (opening == Opening.LEFT_OPENED) return new IntervalLeftOpened(minimum, maximum);
		if (opening == Opening.UNOPENED) return new IntervalUnopened(minimum, maximum, opening);
		else return null;
	}
}
