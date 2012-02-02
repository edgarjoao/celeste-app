package com.condominium.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

public class NumberUtil {

	/** Formato para cantidades */
	private final static String quantityPattern = "###,###,##0.00";//$NON-NLS-1$
	/** Formato para precios */
	private final static String pricePattern = "###,###,##0.0000";//$NON-NLS-1$	
	/** Formato para enteros */
	private final static String intPattern = "###,###,###";//$NON-NLS-1$
	/** Caracter COMA */
	public static final String COMMA_LABEL = ",";//$NON-NLS-1$
	/** Cadena vacia */
	public static final String EMPTY_STRING = "";//$NON-NLS-1$
	/** Clase que formatea las cantidades */
	private static DecimalFormat quantityFormater = new DecimalFormat(quantityPattern);
	/** Clase que formatea los precios */
	private static DecimalFormat priceFormater = new DecimalFormat(pricePattern);
	/** Clase que formatea las enteros */
	private static DecimalFormat integerFormater = new DecimalFormat(intPattern);	
	
	/**
	 * Metodo para formatear cantidades con el formato establecido ###,###,##0.00 
	 * @param quantity cantidad
	 * @return cantidad formateada
	 */
	public static String floorQuantity( double quantity ){
		String formatedQuantity =  null;
		formatedQuantity = integerFormater.format(((int)(quantity+.99)));
		return formatedQuantity;
	}
		
	
	public static String convertTon( Double tons ){
		return convertTon(tons.doubleValue());
	}
	
	/**
	 * Metodo para formatear cantidades con el formato establecido ###,###,##0.00 
	 * @param quantity cantidad
	 * @return cantidad formateada
	 */
	public static String convertQuantity( double quantity ){
		String formatedQuantity =  null;
		formatedQuantity = quantityFormater.format(quantity);
		return formatedQuantity;
	}
	
	public static String convertPrice( double price ){
		String formatedQuantity =  null;
		formatedQuantity = priceFormater.format(price);
		return formatedQuantity;
	}
	
	public static String convertPrice( Double price ){
		return convertPrice(price.doubleValue());
	}
	/**
	 * Metodo para formatear enteros con el formato establecido ###,###,### 
	 * @param quantity cantidad
	 * @return cantidad formateada
	 */
	public static String convertInteger( int integer ){
		String formatedQuantity =  null;
		formatedQuantity = integerFormater.format(integer);
		return formatedQuantity;
	}
	
	public static String convertInteger( Integer integer ){
		return convertInteger(integer.intValue());
	}
	
	/**
	 * Metodo para formatear cantidades con el formato establecido ###,###,##0.00
	 * @param quantity Clase Double a convertir
	 * @return cantidad formateada
	 */
	public static String convertQuantity(Double quantity) {
		return convertQuantity(quantity.doubleValue());
	}
	/**
	 * Convierte a entero un objeto de tipo String
	 * @param object
	 * @return
	 */
	public static int getInt(Object object){
		return Integer.parseInt((String)object);
	}
	/**
	 * Convierte una cadena a su double correspondiente
	 * @param doubleToParse cadena a convertir
	 * @return double convertido
	 */
	public static double parseDouble(String doubleToParse){
		double parsedDouble = -1;
		if(null!=doubleToParse){
			String validDouble = doubleToParse.replaceAll(COMMA_LABEL, EMPTY_STRING);
			if(!validDouble.equals(EMPTY_STRING)){
				parsedDouble = Double.parseDouble(validDouble);
			}
		}
		return parsedDouble;
	}
	/**
	 * Convierte una cadena a su entero correspondiente
	 * @param intToParse cadena a convertir
	 * @return int convertido
	 */
	public static int parseInt(String intToParse){
		int parsedInt = -1;
		if(null!=intToParse){
			parsedInt = Integer.parseInt(intToParse.replaceAll(COMMA_LABEL, EMPTY_STRING));
		}
		return parsedInt;
	}
	
	public static String fill(int numberToFill, char withChar, int length){
		char[] fill = null;
		StringBuffer stringFilled = null;
		String strNumberToFill = null;
		
		strNumberToFill = Integer.toString(numberToFill);
		fill = new char[length-strNumberToFill.length()];
		stringFilled = new StringBuffer(0);
		
		Arrays.fill(fill, withChar);
		stringFilled.append(fill);
		stringFilled.append(strNumberToFill);
		
		return stringFilled.toString();
	}
	
    /**
     * Convierte una cantidad numérica a texto, hasta 99,999,999.99
     * 
     * @param numero
     * @return Un String con el texto de la cantidad numérica
     * @author José Luis Mayorga Alcántara.
     * @param numero
     *            BigDecimal que será convertido a texto.
     */
    public static String convertirNumeroATexto(BigDecimal numero) {
        /*
        int numInt = numero.intValue();
        double numDoub = numero.doubleValue();
        int residuo = new Double((numInt - numDoub) * NumberUtilConstantes.CIEN).intValue();
        */
        BigDecimal numEntero     = numero.setScale(0, BigDecimal.ROUND_DOWN);
        BigDecimal numRedondeado = numero.setScale(2, BigDecimalUtil.REDONDEO);
        BigDecimal residuo       = numRedondeado.subtract(numEntero);

        // Si tiene residuo
        if (residuo.signum() != 0) {
            String resultado = decmillon(numEntero.intValue());
            return resultado.concat(" pesos " + residuo.movePointRight(2) + "/100 m.n.").toUpperCase();
        }
        else {
            String resultado = decmillon(numEntero.intValue());
            return resultado.concat(" pesos m.n.").toUpperCase();
        }
    }
    
    
    /**
     * Descripción: Convierte la posicion de decenas de millones de un número a
     * texto
     * 
     * @param numero
     * @return Un String con la cantidad en texto.
     */
    private static String decmillon(int numero) {
        String numLetraDMM;
        
        if (numero == NumberUtilConstantes.DIEZ_MILLONES) {
            numLetraDMM = "diez millones";
        }
        else if ((numero > NumberUtilConstantes.DIEZ_MILLONES) && 
                        (numero < NumberUtilConstantes.VEINTE_MILLONES)) {
            numLetraDMM = decena(numero / 
                            NumberUtilConstantes.UN_MILLON).concat("millones ").concat(
                    cienmiles(numero % NumberUtilConstantes.UN_MILLON));
        }
        else if ((numero >= NumberUtilConstantes.VEINTE_MILLONES) && 
                        (numero < NumberUtilConstantes.CIEN_MILLONES)) {
            numLetraDMM = decena(numero / 
                            NumberUtilConstantes.UN_MILLON).concat(" millones ").concat(
                    millon(numero % NumberUtilConstantes.UN_MILLON));
        }
        else if (numero < NumberUtilConstantes.DIEZ_MILLONES) {
            numLetraDMM = millon(numero);
        }
        else {
            throw new Error("Error de logica con número = "+numero);
        }

        return numLetraDMM;
    }
    
    
    /**
     * Descripción: Convierte las unidades de un número a letra
     * 
     * @param numero
     * @return Un String con la cantidad en texto.
     */
    private static String unidad(int numero) {
        String num;
        switch (numero) {
            case NumberUtilConstantes.NUEVE:
                num = "nueve";
                break;

            case NumberUtilConstantes.OCHO:
                num = "ocho";
                break;

            case NumberUtilConstantes.SIETE:
                num = "siete";
                break;

            case NumberUtilConstantes.SEIS:
                num = "seis";
                break;

            case NumberUtilConstantes.CINCO:
                num = "cinco";
                break;

            case NumberUtilConstantes.CUATRO:
                num = "cuatro";
                break;

            case NumberUtilConstantes.TRES:
                num = "tres";
                break;

            case NumberUtilConstantes.DOS:
                num = "dos";
                break;

            case NumberUtilConstantes.UNO:
                num = "un";
                break;
            case 0:
                num = "";
                break;
            default:
                throw new Error("Error de logica con número = "+numero);
        }

        return num;
    }

    /**
     * Descripción: Convierte las decenas de un número a letra
     * 
     * @param numero
     * @return Un String con la cantidad en texto.
     */
    private static String decena(int numero) {
        String numLetraD;
        if ((numero >= NumberUtilConstantes.NOVENTA) && (numero <= 
              NumberUtilConstantes.NOVENTA_Y_NUEVE)) {
            numLetraD = "noventa";
            if (numero > NumberUtilConstantes.NOVENTA) {
                numLetraD = numLetraD.concat(" y ").concat(unidad(numero - 
                           NumberUtilConstantes.NOVENTA));
            }
        }
        else if ((numero >= NumberUtilConstantes.OCHENTA) && (numero <= 
                   NumberUtilConstantes.OCHENTA_Y_NUEVE)) {
            numLetraD = "ochenta";
            if (numero > NumberUtilConstantes.OCHENTA) {
                numLetraD = numLetraD.concat(" y ").concat(unidad(numero - 
                       NumberUtilConstantes.OCHENTA));
            }
        }
        else if ((numero >= NumberUtilConstantes.SETENTA) && (numero <= 
             NumberUtilConstantes.SETENTA_Y_NUEVE)) {
            numLetraD = "setenta";
            if (numero > NumberUtilConstantes.SETENTA) {
                numLetraD = numLetraD.concat(" y ").concat(unidad(numero - 
                       NumberUtilConstantes.SETENTA));
            }
        }
        else if ((numero >= NumberUtilConstantes.SESENTA) && (numero <= 
            NumberUtilConstantes.SESENTA_Y_NUEVE)) {
            numLetraD = "sesenta";
            if (numero > NumberUtilConstantes.SESENTA) {
                numLetraD = numLetraD.concat(" y ").concat(unidad(numero - 
                       NumberUtilConstantes.SESENTA));
            }
        }
        else if ((numero >= NumberUtilConstantes.CINCUENTA) && (numero <= 
            NumberUtilConstantes.CINCUENTA_Y_NUEVE)) {
            numLetraD = "cincuenta";
            if (numero > NumberUtilConstantes.CINCUENTA) {
                numLetraD = numLetraD.concat(" y ").concat(unidad(numero - 
                                NumberUtilConstantes.CINCUENTA));
            }
        }
        else if ((numero >= NumberUtilConstantes.CUARENTA) && (numero <= 
                NumberUtilConstantes.CUARENTA_Y_NUEVE)) {
            numLetraD = "cuarenta";
            if (numero > NumberUtilConstantes.CUARENTA) {
                numLetraD = numLetraD.concat(" y ").concat(unidad(numero - 
                                NumberUtilConstantes.CUARENTA));
            }
        }
        else if ((numero >= NumberUtilConstantes.TREINTA) && (numero <= 
                                        NumberUtilConstantes.TREINTA_Y_NUEVE)) {
            numLetraD = "treinta";
            if (numero > NumberUtilConstantes.TREINTA) {
                numLetraD = numLetraD.concat(" y ").concat(unidad(numero - 
                        NumberUtilConstantes.TREINTA));
            }
        }
        else if ((numero >= NumberUtilConstantes.VEINTE) && (numero <= 
            NumberUtilConstantes.VEINTE_Y_NUEVE)) {
            if (numero == NumberUtilConstantes.VEINTE) {
                numLetraD = "veinte";
            }
            else {
                numLetraD = "veinti".concat(unidad(numero - 
                        NumberUtilConstantes.VEINTE));
            }
        }
        else  if ((numero >= NumberUtilConstantes.DIEZ) && (numero <= 
            NumberUtilConstantes.DIECINUEVE)) {
            switch (numero) {
                case NumberUtilConstantes.DIEZ:        
                    numLetraD = "diez";
                    break;
                case NumberUtilConstantes.ONCE:
                    numLetraD = "once";
                    break;
                case NumberUtilConstantes.DOCE:
                    numLetraD = "doce";
                    break;
                case NumberUtilConstantes.TRECE:
                    numLetraD = "trece";
                    break;
                case NumberUtilConstantes.CATORCE:
                    numLetraD = "catorce";
                    break;
                case NumberUtilConstantes.QUINCE:
                    numLetraD = "quince";
                    break;
                case NumberUtilConstantes.DIECISEIS:
                    numLetraD = "dieciseis";
                    break;
                case NumberUtilConstantes.DIECISIETE:
                    numLetraD = "diecisiete";
                    break;
                case NumberUtilConstantes.DIECIOCHO:
                    numLetraD = "dieciocho";
                    break;
                case NumberUtilConstantes.DIECINUEVE:
                    numLetraD = "diecinueve";
                    break;
                default:
                    throw new Error("Error de logica con número = "+numero);
            }
        }
        else {
            numLetraD = unidad(numero);
        }
        return numLetraD;
    }

    /**
     * Descripción: Convierte las centenas de un número a texto
     * 
     * @param numero
     * @return Un String con la cantidad en texto.
     */
    private static String centena(int numero) {
        
        String numLetraC;
        
        if (numero >= NumberUtilConstantes.CIEN) {
            if ((numero >= NumberUtilConstantes.NOVECIENTOS) && (numero <= 
                NumberUtilConstantes.NOVECIENTOS_NOVENTA_Y_NUEVE)) {
                numLetraC = "novecientos ";
                if (numero > NumberUtilConstantes.NOVECIENTOS) {
                    numLetraC = numLetraC.concat(decena(numero - NumberUtilConstantes.NOVECIENTOS));
                }
            }
            else if ((numero >= NumberUtilConstantes.OCHOCIENTOS) && (numero <= 
                NumberUtilConstantes.OCHOCIENTOS_NOVENTA_Y_NUEVE)) {
                numLetraC = "ochocientos ";
                if (numero > NumberUtilConstantes.OCHOCIENTOS) {
                    numLetraC = numLetraC.concat(decena(numero - 
                            NumberUtilConstantes.OCHOCIENTOS));
                }
            }
            else if ((numero >= NumberUtilConstantes.SETECIENTOS) && (numero <= 
                NumberUtilConstantes.SETECIENTOS_NOVENTA_Y_NUEVE)) {
                numLetraC = "setecientos ";
                if (numero > NumberUtilConstantes.SETECIENTOS) {
                    numLetraC = numLetraC.concat(decena(numero - 
                            NumberUtilConstantes.SETECIENTOS));
                }
            }
            else if ((numero >= NumberUtilConstantes.SEISCIENTOS) && (numero <= 
                    NumberUtilConstantes.SEISCIENTOS_NOVENTA_Y_NUEVE)) {
                numLetraC = "seiscientos ";
                if (numero > NumberUtilConstantes.SEISCIENTOS) {
                    numLetraC = numLetraC.concat(decena(numero - 
                                    NumberUtilConstantes.SEISCIENTOS));
                }
            }
            else if ((numero >= NumberUtilConstantes.QUINIENTOS) && (numero <= 
                    NumberUtilConstantes.QUINIENTOS_NOVENTA_Y_NUEVE)) {
                numLetraC = "quinientos ";
                if (numero > NumberUtilConstantes.QUINIENTOS) {
                    numLetraC = numLetraC.concat(decena(numero - 
                                    NumberUtilConstantes.QUINIENTOS));
                }
            }
            else if ((numero >= NumberUtilConstantes.CUATROCIENTOS) && (numero <= 
                    NumberUtilConstantes.CUATROCIENTOS_NOVENTA_Y_NUEVE)) {
                numLetraC = "cuatrocientos ";
                if (numero > NumberUtilConstantes.CUATROCIENTOS) {
                    numLetraC = numLetraC.concat(decena(numero - 
                                    NumberUtilConstantes.CUATROCIENTOS));
                }
            }
            else if ((numero >= NumberUtilConstantes.TRESCIENTOS) && (numero <= 
                    NumberUtilConstantes.TRESCIENTOS_NOVENTA_Y_NUEVE)) {
                numLetraC = "trescientos ";
                if (numero > NumberUtilConstantes.TRESCIENTOS) {
                    numLetraC = numLetraC.concat(decena(numero - 
                                    NumberUtilConstantes.TRESCIENTOS));
                }
            }
            else if ((numero >= 
                NumberUtilConstantes.DOSCIENTOS) && (numero <=
                NumberUtilConstantes.DOSCIENTOS_NOVENTA_Y_NUEVE)) {
                numLetraC = "doscientos ";
                if (numero > NumberUtilConstantes.DOSCIENTOS) {
                    numLetraC = numLetraC.concat(decena(numero - 
                                    NumberUtilConstantes.DOSCIENTOS));
                }
            }
            else if ((numero >= NumberUtilConstantes.CIEN) && (numero <= 
                    NumberUtilConstantes.CIENTO_NOVENTA_Y_NUEVE)) {
                if (numero == NumberUtilConstantes.CIEN) {
                    numLetraC = "cien";
                }
                else {
                    numLetraC = "ciento ".concat(decena(numero - 
                                    NumberUtilConstantes.CIEN));
                }
            }
            else {
                throw new Error("Error de logica con número = "+numero);
            }
        }
        else {
            numLetraC = decena(numero);
        }
        return numLetraC;
    }

    /**
     * Descripción: Convierte la posicion de miles de un número a texto
     * 
     * @param numero
     * @return Un String con la cantidad en texto.
     */
    private static String miles(int numero) {
        String numLetraM;
        
        if ((numero >= NumberUtilConstantes.MIL) && (numero < NumberUtilConstantes.DOSMIL)) {
            numLetraM = "mil ".concat(centena(numero % NumberUtilConstantes.MIL));
        }
        else if ((numero >= NumberUtilConstantes.DOSMIL) && (numero < NumberUtilConstantes.DIEZMIL)) {
            numLetraM = unidad(numero / 
                     NumberUtilConstantes.MIL).concat(" mil ").concat(centena(numero % 
                     NumberUtilConstantes.MIL));
        }
        else if (numero < NumberUtilConstantes.MIL) {
            numLetraM = centena(numero);
        }
        else {
            throw new Error("Error de logica con número = "+numero);
        }

        return numLetraM;
    }

    /**
     * Descripción: Convierte la posicion de decenas de miles de un número a
     * texto
     * 
     * @param numero
     * @return Un String con la cantidad en texto.
     */
    private static String decmiles(int numero) {
        String numLetraDM;
        
        if (numero == NumberUtilConstantes.DIEZMIL) {
            numLetraDM = "diez mil";
        }
        else if ((numero > NumberUtilConstantes.DIEZMIL) && (numero < NumberUtilConstantes.VEINTEMIL)) {
            numLetraDM = decena(numero / 
                            NumberUtilConstantes.MIL).concat(" mil ").concat(centena(numero % 
                            NumberUtilConstantes.MIL));
        }
        else if ((numero >= NumberUtilConstantes.VEINTEMIL) && (numero < NumberUtilConstantes.CIENMIL)) {
            numLetraDM = decena(numero / 
                            NumberUtilConstantes.MIL).concat(" mil ").concat(miles(numero % 
                            NumberUtilConstantes.MIL));
        }
        else if (numero < NumberUtilConstantes.DIEZMIL) {
            numLetraDM = miles(numero);
        }
        else {            
            throw new Error("Error de logica con número = "+numero);
        }

        return numLetraDM;
    }

    /**
     * Descripción: Convierte la posicion de centenas de miles de un número a
     * texto
     * 
     * @param numero
     * @return Un String con la cantidad en texto.
     */
    private static String cienmiles(int numero) {
        
        String numLetraCM;
        
        if (numero == NumberUtilConstantes.CIENMIL) {
            numLetraCM = "cien mil";
        }
        else if ((numero >= NumberUtilConstantes.CIENMIL) && (numero < NumberUtilConstantes.UN_MILLON)) {
            numLetraCM = centena(numero / 
                            NumberUtilConstantes.MIL).concat(" mil ").concat(centena(numero % 
                            NumberUtilConstantes.MIL));
        }
        else if (numero < NumberUtilConstantes.CIENMIL) {
            numLetraCM = decmiles(numero);
        }
        else {
            throw new Error("Error de logica con número = "+numero);
        }


        return numLetraCM;
    }

    /**
     * Descripción: Convierte la posicion de millones de un número a texto
     * 
     * @param numero
     * @return Un String con la cantidad en texto.
     */
    private static String millon(int numero) {
        
        String numLetraMM;
        
        if ((numero >= NumberUtilConstantes.UN_MILLON) && 
                        (numero < NumberUtilConstantes.DOS_MILLONES)) {
            numLetraMM = "Un millon ".concat(cienmiles(numero % NumberUtilConstantes.UN_MILLON));
        }
        else if ((numero >= NumberUtilConstantes.DOS_MILLONES) && 
                        (numero < NumberUtilConstantes.DIEZ_MILLONES)) {
            numLetraMM = unidad(numero / 
                            NumberUtilConstantes.UN_MILLON).concat(" millones ").concat(
                    cienmiles(numero % NumberUtilConstantes.UN_MILLON));
        }
        else if (numero < NumberUtilConstantes.UN_MILLON) {
            numLetraMM = cienmiles(numero);
        }
        else {
            throw new Error("Error de logica con número = "+numero);
        }

        return numLetraMM;
    }
}
