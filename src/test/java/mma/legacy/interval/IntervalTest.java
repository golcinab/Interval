package mma.legacy.interval;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class IntervalTest {

	private static final Logger logger = Logger.getLogger(Interval.class);

	/**
	 * Configuración inicial del logger.
	 */
	@BeforeClass
	public static void setUpClass(){
		BasicConfigurator.configure();
	}
	/**
	 * Tests de calculo punto medio
	 * Se introduce en la validacion tanto el caso positivo como el control de los limites de equivalencia
	 */
	@Test
	public void calcular_punto_medio_intervalo_positivo_ambos_limites_abiertos() {
		Interval interval = IntervalFactory.getInterval(0, 10, Opening.BOTH_OPENED);
		assertThat(interval.calculateMiddle(), is(5.0));
		assertThat(interval.calculateMiddle(), not(4.0));
		assertThat(interval.calculateMiddle(), not(6.0));
	}

	@Test
	public void calcular_punto_medio_intervalo_mixto_ambos_limites_abiertos() {
		Interval interval = IntervalFactory.getInterval(-10, 10, Opening.BOTH_OPENED);
		assertThat(interval.calculateMiddle(), is(0.0));
		assertThat(interval.calculateMiddle(), not(-1.0));
		assertThat(interval.calculateMiddle(), not(1.0));
	}

	@Test
	public void calcular_punto_medio_intervalo_negativo_ambos_limites_abiertos() {
		Interval interval = IntervalFactory.getInterval(-15, -5, Opening.BOTH_OPENED);
		assertThat(interval.calculateMiddle(), is(-10.0));
		assertThat(interval.calculateMiddle(), not(-9.0));
		assertThat(interval.calculateMiddle(), not(-11.0));
	}

	@Test
	public void calcular_punto_medio_intervalo_positivo_limite_inferior_abierto() {
		Interval interval = IntervalFactory.getInterval(0, 10, Opening.LEFT_OPENED);
		assertThat(interval.calculateMiddle(), is(5.0));
		assertThat(interval.calculateMiddle(), not(4.0));
		assertThat(interval.calculateMiddle(), not(6.0));
	}

	@Test
	public void calcular_punto_medio_intervalo_mixto_limite_inferior_abierto() {
		Interval interval = IntervalFactory.getInterval(-10, 10, Opening.LEFT_OPENED);
		assertThat(interval.calculateMiddle(), is(0.0));
		assertThat(interval.calculateMiddle(), not(-1.0));
		assertThat(interval.calculateMiddle(), not(1.0));
	}

	@Test
	public void calcular_punto_medio_intervalo_negativo_limite_inferior_abierto() {
		Interval interval = IntervalFactory.getInterval(-15, -5, Opening.LEFT_OPENED);
		assertThat(interval.calculateMiddle(), is(-10.0));
		assertThat(interval.calculateMiddle(), not(-9.0));
		assertThat(interval.calculateMiddle(), not(-11.0));
	}

	@Test
	public void calcular_punto_medio_intervalo_positivo_limite_superior_abierto() {
		Interval interval = IntervalFactory.getInterval(0, 10, Opening.RIGHT_OPENED);
		assertThat(interval.calculateMiddle(), is(5.0));
		assertThat(interval.calculateMiddle(), not(4.0));
		assertThat(interval.calculateMiddle(), not(6.0));
	}

	@Test
	public void calcular_punto_medio_intervalo_mixto_limite_superior_abierto() {
		Interval interval = IntervalFactory.getInterval(-10, 10, Opening.RIGHT_OPENED);
		assertThat(interval.calculateMiddle(), is(0.0));
		assertThat(interval.calculateMiddle(), not(-1.0));
		assertThat(interval.calculateMiddle(), not(1.0));
	}

	@Test
	public void calcular_punto_medio_intervalo_negativo_limite_superior_abierto() {
		Interval interval = IntervalFactory.getInterval(-15, -5, Opening.RIGHT_OPENED);
		assertThat(interval.calculateMiddle(), is(-10.0));
		assertThat(interval.calculateMiddle(), not(-9.0));
		assertThat(interval.calculateMiddle(), not(-11.0));
	}

	@Test
	public void calcular_punto_medio_intervalo_positivo_ambos_cerrados() {
		Interval interval = IntervalFactory.getInterval(0, 10, Opening.UNOPENED);
		assertThat(interval.calculateMiddle(), is(5.0));
		assertThat(interval.calculateMiddle(), not(4.0));
		assertThat(interval.calculateMiddle(), not(6.0));
	}

	@Test
	public void calcular_punto_medio_intervalo_mixto_ambos_cerrados() {
		Interval interval = IntervalFactory.getInterval(-10, 10, Opening.UNOPENED);
		assertThat(interval.calculateMiddle(), is(0.0));
		assertThat(interval.calculateMiddle(), not(-1.0));
		assertThat(interval.calculateMiddle(), not(1.0));
	}

	@Test
	public void calcular_punto_medio_intervalo_negativo_ambos_cerrados() {
		Interval interval = IntervalFactory.getInterval(-15, -5, Opening.UNOPENED);
		assertThat(interval.calculateMiddle(), is(-10.0));
		assertThat(interval.calculateMiddle(), not(-9.0));
		assertThat(interval.calculateMiddle(), not(-11.0));
	}

	/**
	 * Tests de validacion dentro de intervalo de un numero
	 * Incluimos para cada test los valores de equivalencia
	 */
	@Test
	public void validar_si_numero_esta_dentro_de_intervalo_ambos_limites_abiertos(){
		Interval interval = IntervalFactory.getInterval(0, 10, Opening.BOTH_OPENED);
		assertThat(interval.includes(-3), is(false));
		assertThat(interval.includes(0), is(false));
		assertThat(interval.includes(5), is(true));
		assertThat(interval.includes(10), is(false));
		assertThat(interval.includes(13), is(false));
	}

	@Test
	public void validar_si_numero_esta_dentro_de_intervalo_limite_inferior_abierto(){
		Interval interval = IntervalFactory.getInterval(0, 10, Opening.LEFT_OPENED);
		assertThat(interval.includes(-3), is(false));
		assertThat(interval.includes(0), is(false));
		assertThat(interval.includes(5), is(true));
		assertThat(interval.includes(10), is(true));
		assertThat(interval.includes(13), is(false));
	}

	@Test
	public void validar_si_numero_esta_dentro_de_intervalo_limite_superior_abierto(){
		Interval interval = IntervalFactory.getInterval(0, 10, Opening.RIGHT_OPENED);
		assertThat(interval.includes(-3), is(false));
		assertThat(interval.includes(0), is(true));
		assertThat(interval.includes(5), is(true));
		assertThat(interval.includes(10), is(false));
		assertThat(interval.includes(13), is(false));
	}

	@Test
	public void validar_si_numero_esta_dentro_de_intervalo_ambos_cerrados(){
		Interval interval = IntervalFactory.getInterval(0, 10, Opening.UNOPENED);
		assertThat(interval.includes(-3), is(false));
		assertThat(interval.includes(0), is(true));
		assertThat(interval.includes(5), is(true));
		assertThat(interval.includes(10), is(true));
		assertThat(interval.includes(13), is(false));
	}

	@Test
	public void includeIntervalTest() {

		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 35, Opening.BOTH_OPENED);
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.BOTH_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.BOTH_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.BOTH_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.BOTH_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.UNOPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.UNOPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.UNOPENED)));
		assertTrue(bothOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.UNOPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.UNOPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.UNOPENED)));
		assertFalse(bothOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.UNOPENED)));
		Interval leftOpenedPivot = IntervalFactory.getInterval(20, 35, Opening.LEFT_OPENED);
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.BOTH_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.BOTH_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.BOTH_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.BOTH_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.UNOPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.UNOPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.UNOPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.UNOPENED)));
		assertFalse(leftOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.UNOPENED)));
		Interval rightOpenedPivot = IntervalFactory.getInterval(20, 35, Opening.RIGHT_OPENED);
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.BOTH_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.BOTH_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.BOTH_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.BOTH_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.UNOPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.UNOPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.UNOPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.UNOPENED)));
		assertFalse(rightOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.UNOPENED)));
		Interval unopenedOpenedPivot = IntervalFactory.getInterval(20, 35, Opening.UNOPENED);
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.BOTH_OPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.BOTH_OPENED)));
		assertTrue(unopenedOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.BOTH_OPENED)));
		assertTrue(unopenedOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.BOTH_OPENED)));
		assertTrue(unopenedOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.BOTH_OPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.BOTH_OPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.BOTH_OPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.LEFT_OPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.LEFT_OPENED)));
		assertTrue(unopenedOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.LEFT_OPENED)));
		assertTrue(unopenedOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.LEFT_OPENED)));
		assertTrue(unopenedOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.LEFT_OPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.LEFT_OPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.LEFT_OPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.RIGHT_OPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.RIGHT_OPENED)));
		assertTrue(unopenedOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.RIGHT_OPENED)));
		assertTrue(unopenedOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.RIGHT_OPENED)));
		assertTrue(unopenedOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.RIGHT_OPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.RIGHT_OPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.RIGHT_OPENED)));

		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(10, 15, Opening.UNOPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(15, 20, Opening.UNOPENED)));
		assertTrue(unopenedOpenedPivot.includes(IntervalFactory.getInterval(20, 25, Opening.UNOPENED)));
		assertTrue(unopenedOpenedPivot.includes(IntervalFactory.getInterval(25, 30, Opening.UNOPENED)));
		assertTrue(unopenedOpenedPivot.includes(IntervalFactory.getInterval(30, 35, Opening.UNOPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(35, 40, Opening.UNOPENED)));
		assertFalse(unopenedOpenedPivot.includes(IntervalFactory.getInterval(40, 45, Opening.UNOPENED)));

	}

	@Test
	public void hasIntersectionTest() {

		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 40, Opening.BOTH_OPENED);

		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.BOTH_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.BOTH_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.BOTH_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.BOTH_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.UNOPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.UNOPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.UNOPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.UNOPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.UNOPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.UNOPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.UNOPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.UNOPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.UNOPENED)));

		Interval leftOpenedPivot = IntervalFactory.getInterval(20, 40, Opening.LEFT_OPENED);

		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.BOTH_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.BOTH_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.BOTH_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.BOTH_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.UNOPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.UNOPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.UNOPENED)));
		Interval rightOpenedPivot = IntervalFactory.getInterval(20, 40, Opening.RIGHT_OPENED);
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.BOTH_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.BOTH_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.BOTH_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.BOTH_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.UNOPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.UNOPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.UNOPENED)));
		Interval unopenedPivot = IntervalFactory.getInterval(20, 40, Opening.UNOPENED);
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.BOTH_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.BOTH_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.BOTH_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.BOTH_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.BOTH_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.BOTH_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.BOTH_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.BOTH_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.BOTH_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.LEFT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.LEFT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.LEFT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.RIGHT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.RIGHT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.RIGHT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.UNOPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.UNOPENED)));
	}

	@Test
	public void hasIntersectionTest2() {

		Interval bothOpenedPivot = IntervalFactory.getInterval(20, 40, Opening.BOTH_OPENED);

		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.BOTH_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.BOTH_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.BOTH_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.BOTH_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.BOTH_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.LEFT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.LEFT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.RIGHT_OPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.RIGHT_OPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.UNOPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.UNOPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.UNOPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.UNOPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.UNOPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.UNOPENED)));
		assertTrue(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.UNOPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.UNOPENED)));
		assertFalse(bothOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.UNOPENED)));

		Interval leftOpenedPivot = IntervalFactory.getInterval(20, 40, Opening.LEFT_OPENED);

		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.BOTH_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.BOTH_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.BOTH_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.BOTH_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.BOTH_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.LEFT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.LEFT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.RIGHT_OPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.RIGHT_OPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.UNOPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.UNOPENED)));
		assertTrue(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.UNOPENED)));
		assertFalse(leftOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.UNOPENED)));
		Interval rightOpenedPivot = IntervalFactory.getInterval(20, 40, Opening.RIGHT_OPENED);
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.BOTH_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.BOTH_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.BOTH_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.BOTH_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.BOTH_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.LEFT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.LEFT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.RIGHT_OPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.RIGHT_OPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.UNOPENED)));
		assertTrue(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.UNOPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.UNOPENED)));
		assertFalse(rightOpenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.UNOPENED)));
		Interval unopenedPivot = IntervalFactory.getInterval(20, 40, Opening.UNOPENED);
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.BOTH_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.BOTH_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.BOTH_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.BOTH_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.BOTH_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.BOTH_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.BOTH_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.BOTH_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.BOTH_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.LEFT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.LEFT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.LEFT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.LEFT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.RIGHT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.RIGHT_OPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.RIGHT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.RIGHT_OPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(5, 15, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(10, 20, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(15, 25, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(20, 30, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(25, 35, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(30, 40, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(35, 45, Opening.UNOPENED)));
		assertTrue(unopenedPivot.intersectsWith(IntervalFactory.getInterval(40, 50, Opening.UNOPENED)));
		assertFalse(unopenedPivot.intersectsWith(IntervalFactory.getInterval(45, 55, Opening.UNOPENED)));
	}

}
