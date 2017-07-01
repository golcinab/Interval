package mma.legacy.interval;

import org.apache.log4j.Logger;

/**
 * Clase para el ejemplo de trabajo con Legacy
 *
 * @author Agustin
 *         Controla operaciones sobre intervalos que pudeen ser abiertos o cerrados
 */
public class Interval {
	// Creamos el logger del proyecto
	private static final Logger logger = Logger.getLogger(Interval.class);

	private double minimum;  // numero entero que indica el limite superior del intervalo
	private double maximum;  // numero entero que indica el limite superior del intervalo

	private Opening opening; // enum que indica el tipo de intervalo de los 4 posibles.

	/**
	 * Construye un objeto intervalo dado su maximo / minimo y el tipo de intervalo.
	 * Todos los parametros pueden ser nulos, se especifica el minimo, maximo y el tipo de intervalo.
	 *
	 * @param minimum valor minimo del intervalo
	 * @param maximum valor maximo del intervalo
	 * @param opening tipo de intervalo
	 */
	public Interval(double minimum, double maximum, Opening opening) {
		this.minimum = minimum;
		this.maximum = maximum;
		this.opening = opening;

		logger.debug("Objeto creado");
	}

	/**
	 * Devuelve el punto medio del intervalo
	 *
	 * @return el punto medio del intervalo
	 */
	public double calculateMiddle() {
		return (maximum + minimum) / 2;
	}

	/**
	 * Indica si un numero dado se encuentra dentro del interval.
	 *
	 * @param value numero a verificar si esta en el itervalo
	 * @return true si esta en el intervalo, false en caso contrario
	 */
	public boolean isNumberIncluded(double value) {
		logger.debug("Entro en el metodo");

		return isUnderMaximunLimit(value) && isOverMinimunLimit(value);
	}

	/**
	 * Verifica si un numero esta por debajo del limite superior
	 * @param value
	 * @return true si esta por debajo, false en caso contrario
	 */
	private boolean isUnderMaximunLimit(double value){
		if (isOpenMaxLimit()){
			return value < this.maximum;
		}else{
			return value <= this.maximum;
		}
	}

	/**
	 * Indica si el limite superior es abierto
	 * @return true si limite superior abierto, false en caso contrario
	 */
	private boolean isOpenMaxLimit() {
		return this.opening == Opening.BOTH_OPENED || this.opening == Opening.RIGHT_OPENED;
	}

	/**
	 * Verifica si un numero esta por encima del limite inferior
	 * @param value
	 * @return true si esta por encima, falso en caso contrario
	 */
	private boolean isOverMinimunLimit(double value) {
		if (isOpenMinLimit()) {
			return this.minimum < value;
		} else {
			return this.minimum <= value;
		}
	}

	/**
	 * Indica si el liminte inferior es abierto
	 * @return true si limite inferior es abierto, false en caso contrario
	 */
	private boolean isOpenMinLimit() {
		return this.opening == Opening.BOTH_OPENED || this.opening == Opening.LEFT_OPENED;
	}

	/**
	 * Comparamos si dos double son iguales
	 * @return true si son iguales, false en caso contrario
	 */
	private boolean doubleEquals(double value1, double value2){
		return Double.compare(value1, value2) == 0;
	}
	/**
	 * Indica si un intervalo esta dentro de otro intervalo
	 *
	 * @param interval intervalo a verificar si esta dentro del intervalo
	 * @return true si esta en el intervalo, false en caso contrario
	 */
	public boolean isIntervalIncluded(Interval interval) {
		boolean minimumIncluded = this.isNumberIncluded(interval.minimum);
		boolean maximumIncluded = this.isNumberIncluded(interval.maximum);

		boolean result = false;

		switch (opening) {
			case BOTH_OPENED:
				switch (interval.opening) {
					case BOTH_OPENED:
						result = (minimumIncluded || this.doubleEquals(minimum, interval.minimum)) && (maximumIncluded || this.doubleEquals(maximum, interval.maximum));
						break;
					case LEFT_OPENED:
						result =  (minimumIncluded || this.doubleEquals(minimum, interval.minimum))  && (maximumIncluded);
						break;
					case RIGHT_OPENED:
						result = (minimumIncluded) && (maximumIncluded || this.doubleEquals(maximum, interval.maximum));
						break;
					case UNOPENED:
						result =  (minimumIncluded) && (maximumIncluded);
						break;
				}
				break;

			case LEFT_OPENED:
				if( interval.isOpenMinLimit()){
					result =  (minimumIncluded || this.doubleEquals(minimum, interval.minimum)) && (maximumIncluded || this.doubleEquals(maximum, interval.maximum));
				} else{
					result = (minimumIncluded) && (maximumIncluded || this.doubleEquals(maximum, interval.maximum));
				}
				break;

			case RIGHT_OPENED:
				if (interval.isOpenMaxLimit()) {
					result = (minimumIncluded || this.doubleEquals(minimum, interval.minimum)) && (maximumIncluded || this.doubleEquals(maximum, interval.maximum));
				}else{
					result = (minimumIncluded || this.doubleEquals(minimum, interval.minimum)) && (maximumIncluded);
				}
				break;

			case UNOPENED:
				result = (minimumIncluded || this.doubleEquals(minimum, interval.minimum)) && (maximumIncluded || this.doubleEquals(maximum, interval.maximum));
				break;
		}
		return result;
	}

	/**
	 * Indica si un intervalo se "intersecta" con otro intervalo
	 * Se considera que intersecta si uno de los limites está dentro del intervalo, y el otro fuera
	 *
	 * @param interval intervalo a verificar si intersecta con el intervalo
	 * @return true si esta en el intervalo, false en caso contrario
	 */
	public boolean intersectsWith(Interval interval) {
		if ( this.doubleEquals(minimum, interval.maximum))  {
			switch (opening) {
				case BOTH_OPENED:
				case LEFT_OPENED:
					return false;
				case RIGHT_OPENED:
				case UNOPENED:
					return interval.opening == Opening.LEFT_OPENED ||
							interval.opening == Opening.UNOPENED;
				default:
					assert false;
					return false;
			}
		}
		if ( this.doubleEquals(maximum, interval.minimum)) {
			switch (opening) {
				case BOTH_OPENED:
				case RIGHT_OPENED:
					return false;
				case LEFT_OPENED:
				case UNOPENED:
					return interval.opening == Opening.RIGHT_OPENED ||
							interval.opening == Opening.UNOPENED;
				default:
					assert false;
					return false;
			}
		}
		return this.isNumberIncluded(interval.minimum)
				|| this.isNumberIncluded(interval.maximum);
	}
}
