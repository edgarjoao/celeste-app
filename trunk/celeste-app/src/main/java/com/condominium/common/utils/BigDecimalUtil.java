package com.condominium.common.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {

	/**
     * Escala estandar definida para importes en pesos.
     */
    public static final int ESCALA_IMPORTE = 2;

    /**
     * Escala estandar definida para factores o porcentajes.
     */
    public static final int ESCALA_FACTOR  = 7;
        
    /**
     * Redondeo estandar para las variables BigDecimal.
     */
    public static final int REDONDEO = BigDecimal.ROUND_HALF_EVEN;
        
    /** Usado para dividir entre 0 */
    public static final BigDecimal CERO = importe(new BigDecimal("0"));

    /** Usado para sacar el reciproco (1/x) */
    public static final BigDecimal UNO = importe(new BigDecimal("1"));

    /** Tipicamente meses en el año */    
    public static final BigDecimal DOCE = importe(new BigDecimal("12"));
    
    /** Tipicamente días en el mes */    
    public static final BigDecimal TREINTA = importe(new BigDecimal("30"));

    /** Tipicamente se usa para sacar porcentajes */    
    public static final BigDecimal CIEN = factor(new BigDecimal("100"));

    /**
     * Divide usando la escala y redondeo estandar
     * @param n1 dividendo.
     * @param n2 divisor.
     * @return resultado de la división.
     */
    public static BigDecimal divide(BigDecimal n1, BigDecimal n2) {
        return n1.divide(n2, ESCALA_FACTOR, REDONDEO);
    }

    /** 
     * Divide dos cantidades y regresa un resultado redondeado como importe.
     * @param n1 dividendo.
     * @param n2 divisor.
     * @return resultado redondeado como importe.
     */
    public static BigDecimal divideImporte(BigDecimal n1, BigDecimal n2) {
        return n1.divide(n2, ESCALA_IMPORTE, REDONDEO);
    }
    
    /** 
     * Divide dos cantidades y regresa un resultado redondeado como factor.
     * @param n1 dividendo.
     * @param n2 divisor.
     * @return resultado redondeado como factor.
     */
    public static BigDecimal divideFactor(BigDecimal n1, BigDecimal n2) {
        return n1.divide(n2, ESCALA_FACTOR, REDONDEO);
    }
    
    /**
     * Divide entre 12. Tipicamente para convertir importes anuales
     * a importes mensuales.
     * @param n1 número que sera dividido entre 12.
     * @return resultado de la división.
     */
    public static BigDecimal divide12(BigDecimal n1) {
        return n1.divide(DOCE, ESCALA_IMPORTE, REDONDEO);
    }
    
    /** 
     * Divide entre 30. Tipicamente para convertir importes mensuales en
     * a importes diarios.
     * @param n1 número que sera dividido entre 30.
     * @return resultado de la división.
     */
    public static BigDecimal divide30(BigDecimal n1) {
        return n1.divide(TREINTA, ESCALA_IMPORTE, REDONDEO);
    }

    /** 
     * Divide entre 100. Tipicamente se usa para sacar porcentajes.
     * @param n1 número que sera dividido entre 100.
     * @return resultado de la división.
     */
    public static BigDecimal divide100(BigDecimal n1) {
        return n1.divide(CIEN, ESCALA_FACTOR, REDONDEO);
    }
    
    /**
     * Genera un número nuevo redondeado a la escala de los importes.
     * @param num número a redondear.
     * @return número redodondeado
     */
    public static BigDecimal importe(BigDecimal num) {
        return num.setScale(ESCALA_IMPORTE, REDONDEO);
    }
    
    /**
     * Genera un número nuevo redondeado a la escala de los factores.
     * @param num número a redondear.
     * @return número redodondeado
     */
    public static BigDecimal factor(BigDecimal num) {
        return num.setScale(ESCALA_FACTOR, REDONDEO);
    }
    
    /**
     * Multiplica dos numeros y genera una con la escala de los importes.
     * @param num1 número a multiplicar.
     * @param num2 número a multiplicar.
     * @return resultado ya redondeado.
     */     
    public static BigDecimal multImporte(BigDecimal num1, BigDecimal num2) {
        return importe(num1.multiply(num2));        
    }
    
    /**
     * Multiplica dos numeros y genera una con la escala de los factores.
     * @param num1 número a multiplicar.
     * @param num2 número a multiplicar.
     * @return resultado ya redondeado.
     */     
    public static BigDecimal multFactor(BigDecimal num1, BigDecimal num2) {
        return factor(num1.multiply(num2));        
    }
    
    /**
     * Multiplica un numero por 12. Tipicamente meses en el año.
     * @param número a multiplicar.
     * @return resultado ya redondeado.
     */
    public static BigDecimal mult12(BigDecimal num) {
        return importe(num.multiply(DOCE));
    }

    /**
     * Trunca la parte decimal del monto 
     * @param número a ajustar.
     * @return resultado truncado.
     */
    public static BigDecimal truncaDecimales(BigDecimal num) {
    	final int ESCALA = 0;
        return new BigDecimal(num.toBigInteger(), ESCALA);
    }
    
}
