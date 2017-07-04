package mma.legacy.interval;

import org.apache.log4j.Logger;

/**
 * Clase para el tratamiento de intervalos con limite inferior y superior abiertos
 *
 * @author Gabriel Olcina
 *
 */
public class IntervalOpened extends Interval {

	private static final Logger logger = Logger.getLogger(IntervalOpened.class);

	/**
	 * Construye un objeto intervalo dado su maximo / minimo y el tipo de intervalo.
	 * Todos los parametros pueden ser nulos, se especifica el minimo, maximo y el tipo de intervalo.
	 *  @param minimum valor minimo del intervalo
	 * @param maximum valor maximo del intervalo
	 */
	public IntervalOpened(double minimum, double maximum) {
		super(minimum, maximum);

		logger.debug("Objeto creado");
	}

	protected Opening getOpening() { return Opening.BOTH_OPENED; }

	/**
	 * Indica si el limite superior es abierto
	 *
	 * @return true si limite superior abierto, false en caso contrario
	 */
	@Override
	protected boolean isOpenMaxLimit() { return true; }

	/**
	 * Indica si el liminte inferior es abierto
	 *
	 * @return true si limite inferior es abierto, false en caso contrario
	 */
	@Override
	protected boolean isOpenMinLimit() { return true; }

	/**
	 * Verifica si un numero esta por debajo del limite superior
	 * @param value valor a verificar
	 * @return true si esta por debajo, false en caso contrario
	 */
	protected boolean isUnderMaximunLimit(double value){ return value < this.getMaximum(); }

	/**
	 * Verifica si un numero esta por encima del limite inferior
	 * @param value valora a verificar
	 * @return true si esta por encima, falso en caso contrario
	 */
	protected boolean isOverMinimunLimit(double value) { return this.getMinimum() < value; }

	/**
	 * Indica si un intervalo esta dentro de otro intervalo
	 *
	 * @param interval intervalo a verificar si esta dentro del intervalo
	 * @return true si esta en el intervalo, false en caso contrario
	 */
	public boolean isIntervalIncluded(Interval interval) {
		boolean minimumIncluded = this.isNumberIncluded(interval.getMinimum());
		boolean maximumIncluded = this.isNumberIncluded(interval.getMaximum());

		boolean result = false;

		switch (interval.getOpening()) {
			case BOTH_OPENED:
				result = (minimumIncluded || this.doubleEquals(getMinimum(), interval.getMinimum())) && (maximumIncluded || this.doubleEquals(getMaximum(), interval.getMaximum()));
				break;
			case LEFT_OPENED:
				result =  (minimumIncluded || this.doubleEquals(getMinimum(), interval.getMinimum()))  && (maximumIncluded);
				break;
			case RIGHT_OPENED:
				result = (minimumIncluded) && (maximumIncluded || this.doubleEquals(getMaximum(), interval.getMaximum()));
				break;
			case UNOPENED:
				result =  (minimumIncluded) && (maximumIncluded);
				break;
			default:
				break;
		}
		return result;
	}
}