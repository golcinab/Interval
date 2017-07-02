package mma.legacy.interval;

import org.apache.log4j.Logger;

/**
 * Created by golcinab on 02/07/2017.
 */
public abstract class Interval {
	// Creamos el logger del proyecto
	protected static final Logger logger = Logger.getLogger(Interval.class);
	protected double minimum;  // numero entero que indica el limite superior del intervalo
	protected double maximum;  // numero entero que indica el limite superior del intervalo

	protected Interval(double minimum, double maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}

	protected abstract Opening getOpening();

	protected abstract boolean isOpenMaxLimit();
	protected abstract boolean isOpenMinLimit();

	protected abstract boolean isUnderMaximunLimit(double value);
	protected abstract boolean isOverMinimunLimit(double value);

	/**
	 * Devuelve el punto medio del intervalo
	 *
	 * @return el punto medio del intervalo
	 */
	public double calculateMiddle() {
		return (maximum + minimum) / 2;
	}

	/**
	 * Comparamos si dos double son iguales
	 *
	 * @return true si son iguales, false en caso contrario
	 */
	private boolean doubleEquals(double value1, double value2) {
		return Double.compare(value1, value2) == 0;
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
	 * Indica si un intervalo esta dentro de otro intervalo
	 *
	 * @param interval intervalo a verificar si esta dentro del intervalo
	 * @return true si esta en el intervalo, false en caso contrario
	 */
	public boolean isIntervalIncluded(Interval interval) {
		boolean minimumIncluded = this.isNumberIncluded(interval.minimum);
		boolean maximumIncluded = this.isNumberIncluded(interval.maximum);

		boolean result = false;

		switch (getOpening()) {
			case BOTH_OPENED:
				switch (interval.getOpening()) {
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
					default:
						break;
				}
				break;

			case LEFT_OPENED:
				if( interval.isOpenMinLimit()){
					result = (minimumIncluded || this.doubleEquals(minimum, interval.minimum)) && (maximumIncluded || this.doubleEquals(maximum, interval.maximum));
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

			default:
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
		if (this.doubleEquals(minimum, interval.maximum)) {
			switch (getOpening()) {
				case BOTH_OPENED:
				case LEFT_OPENED:
					return false;
				case RIGHT_OPENED:
				case UNOPENED:
					return interval.getOpening() == Opening.LEFT_OPENED ||
							interval.getOpening() == Opening.UNOPENED;
				default:
					assert false;
					return false;
			}
		}
		if ( this.doubleEquals(maximum, interval.minimum)) {
			switch (getOpening()) {
				case BOTH_OPENED:
				case RIGHT_OPENED:
					return false;
				case LEFT_OPENED:
				case UNOPENED:
					return interval.getOpening() == Opening.RIGHT_OPENED ||
							interval.getOpening() == Opening.UNOPENED;
				default:
					assert false;
					return false;
			}
		}
		return this.isNumberIncluded(interval.minimum) || this.isNumberIncluded(interval.maximum);
	}
}
