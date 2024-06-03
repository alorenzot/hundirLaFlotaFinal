package org.example;

import java.util.Scanner;

/**
 * La clase Entrada recoge lo necesario para operar con el tablero
 * @author alorenzot
 */
public class Entrada {

    private static String[] valoresCoordenadaY = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static String[] valoresCoordenadaX = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static String[] valoresOrientacion = {"0", "1"};

    /**
     * 
     * @return El nombre del jugador preguntado por el metodo
     */
    public static String nombreJugador() {
        Scanner input = new Scanner(System.in);
        System.out.println("Introduce el nombre del jugador: ");
        String nombre = input.next();

        return nombre;
    }

    /**
     * Este método devuelve la fila como un entero
     * @see #valoresCoordenadaY Guarda los posibles valores
     * @see #validaCoordenada(String[], String) Verifica que la coordenada introducida se encuentra en los valores posibles
     * @see #convertirCoordenada(String[], String) Convierte la coordenada en entero
     * @return El valor de la coordenada del eje Y
     */
    public static int coordenadaY() {

        int fila = 0;
        boolean coordenadaCorrecta = false;
        Scanner sc = new Scanner(System.in);

        while (!coordenadaCorrecta) {
            System.out.println("Introduce las Coordenas Y [A - J]: ");
            String sFila = sc.next();
            sFila = sFila.toUpperCase();

            if (sFila.length() > 1) {
                System.out.println("Solo debes introducir un caracter.");
            } else {
                if (!validaCoordenada(valoresCoordenadaY, sFila)) {
                    System.out.println("Caracter no valido. Debes introducir un caracter entre [A - J]");
                } else {
                    fila = convertirCoordenada(valoresCoordenadaY, sFila);
                    coordenadaCorrecta = true;
                }
            }
        }
        return fila;
    }

    /**
     * Este método devuelve la columna como un entero
     * @see #valoresCoordenadaX Guarda los posibles valores
     * @see #validaCoordenada(String[], String) Verifica que la coordenada introducida se encuentra en los valores posibles
     * @see #convertirCoordenada(String[], String) Convierte la coordenada en entero
     * @return El valor de la coordenada en el eje X
     */
    public static int coordenadaX() {

        int columna = 0;
        boolean coordenadaCorrecta = false;
        Scanner input = new Scanner(System.in);

        while (!coordenadaCorrecta) {
            System.out.println("Introduce las Coordenadas X (0 - 9): ");
            String sColumna = input.next();
            sColumna = sColumna.toUpperCase();

            if (sColumna.length() > 1) {
                System.out.println("Solo debes introducir un caracter.");
            } else {
                if (!validaCoordenada(valoresCoordenadaX, sColumna)) {
                    System.out.println("Caracter no válido. Debes introducir un caracter entre [0 - 9].");
                } else {
                    columna = convertirCoordenada(valoresCoordenadaX, sColumna);
                    coordenadaCorrecta = true;
                }
            }
        }
        return columna;
    }

    /**
     * Este método devuelve la orientación como un entero
     * @see #validaCoordenada(String[], String) Verifica que la coordenada introducida se encuentra en los valores posibles
     * @see #convertirCoordenada(String[], String) Convierte la coordenada en entero
     * @return La orientacion, 1 -> Horizontal, 0 -> Vertical
     */
    public static int orientacion() {

        int orientacion = 0;
        boolean orientacionCorrecta = false;
        Scanner input = new Scanner(System.in);

        while (!orientacionCorrecta) {
            System.out.print("Introduce la orientación (1 - Horizontal o 0 - Vertical): ");
            String sOrientacion = input.next();
            sOrientacion = sOrientacion.toUpperCase();

            if (sOrientacion.length() > 1) {
                System.out.println("Solo debes introducir un caracter.");
            } else {
                if (!validaCoordenada(valoresOrientacion, sOrientacion)) {
                    System.out.println("Caracter no válido. Debes introducir un caracter entre [0 - 1].");
                } else {
                    orientacion = convertirCoordenada(valoresOrientacion, sOrientacion);
                    orientacionCorrecta = true;
                }
            }
        }
        return orientacion;
    }

    /**
     * @param valoresCoordenada los valores de la coordenada a convertir
     * @param coordenada la coordenada introducida por el usuario
     * @return la posición de la coordenada en el array
     */
    private static int convertirCoordenada(String[] valoresCoordenada, String coordenada) {

        int j = 0;
        boolean coordenadaCorrecta = false;
        for (int i = 0; i < valoresCoordenada.length && !coordenadaCorrecta; i++) {
            if (coordenada.equals(valoresCoordenada[i])) {
                j = i;
                coordenadaCorrecta = true;
            }
        }
        return j;
    }

    /**
     *
     * @param valoresCoordenada los valores de la coordenada a convertir
     * @param coordenada la coordenada introducida por el usuario
     * @return verdadero si la coordenada es válida
     */
    private static boolean validaCoordenada(String[] valoresCoordenada, String coordenada) {
        boolean coordenadaValida = false;
        for (int i = 0; i < valoresCoordenada.length && !coordenadaValida; i++) {
            if (coordenada.equals(valoresCoordenada[i])) {
                coordenadaValida = true;
            }
        }
        return coordenadaValida;
    }
}