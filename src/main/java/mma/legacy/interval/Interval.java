package mma.legacy.interval;

import org.apache.log4j.Logger;

/**
 * Created by golcinab on 02/07/2017.
 */
public abstract class Interval {
	// Creamos el logger del proyecto
	private static final Logger logger = Logger.getLogger(Interval.class);

	protected double getMinimum() { return minimum; }
	protected double getMaximum() { return maximum; }

	private double minimum;  // numero entero que indica el limite superior del intervalo
	private double maximum;  // numero entero que indica el limite superior del intervalo

	protected Interval(double minimum, double maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}

	/**
	 * Obtenemos el tipo de intervalo del objeto
	 * @return tipo de "Opening"
	 */
	protected abstract Opening getOpening();

	protected abstract boolean isOpenMaxLimit();
	protected abstract boolean isOpenMinLimit();

	protected abstract boolean isUnderMaximunLimit(double value);
	protected abstract boolean isOverMinimunLimit(double value);

	/**
	 * Obtenemmos is el interval actual está incluido en el interval abierto
	 * @param interval intervalo abierto donde hay que estar incluido
	 * @return si esta incluido
	 */
	protected abstract boolean isIntervalIncludedOnBothOpenedInterval(Interval interval);

	/**
	 * Indica si un intervalo esta dentro de otro intervalo
	 *
	 * @param interval intervalo a verificar si esta dentro del intervalo
	 * @return true si esta en el intervalo, false en caso contrario
	 */
	public abstract boolean isIntervalIncluded(Interval interval);

	/**
	 * Indica si un intervalo se "intersecta" con otro intervalo
	 * Se considera que intersecta si uno de los limites está dentro del intervalo, y el otro fuera
	 *
	 * @param interval intervalo a verificar si intersecta con el intervalo
	 * @return true si esta en el intervalo, false en caso contrario
	 */
	public abstract boolean intersectsWith(Interval interval);

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
	protected boolean doubleEquals(double value1, double value2) {
		return Double.compare(value1, value2) == 0;
	}

	/**
	 * Indica si un numero dado se encuentra dentro del interval.
	 *
	 * @param value numero a verificar si esta en el itervalo
	 * @return true si esta en el intervalo, false en caso contrario
	 */
	public boolean isNumberIncluded(double value) {
		logger.debug("Entro en el metodo isNumberIncluded");
		return isUnderMaximunLimit(value) && isOverMinimunLimit(value);
	}

}
