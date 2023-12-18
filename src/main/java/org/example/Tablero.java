package org.example;

public class Tablero {
    /**
     * La clase Tablero contiene los metodos para operar con el tablero
     * @author alorenzot
     */
    public static int barco = 0;   // indica que se debe colocar una B "Barco"  en el tablero
    public static int acierto = 1; // indica que se debe colocar una T "Tocado" en el tablero
    public static int fallo = 2;   // indica que se debe colocar un  * "Fallo"  en el tablero

    /**
     * Este método inicializa el tablero
     * @param tablero el tablero a inicializar
     */
    public static void inicializarTablero(char[][] tablero) {
    /* Este método inicializa cada tablero de la siguiente manera:
       A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
       B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
       C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
       D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
       E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
       F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
       G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
       H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
       I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
       J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
         0 1 2 3 4 5 6 7 8 9 */
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tablero[i][j] = '~';
            }
        }

    }

    /**
     * Este método muestra el tablero por pantalla
     * @param tablero el tablero a mostrar
     * @param tableroDisparos el tablero de los disparos a mostrar
     * @param jugador verdadero si se pide el del jugador, falso paraa PC
     */
    public static void visualizarTablero(char[][] tablero, char[][] tableroDisparos, boolean jugador) {

        char[] indiceFilas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        if (jugador) {
            System.out.println(" Tablero Jugador                    Tablero Disparos");
        } else {
            System.out.println(" Tablero PC                         Tablero PC Disparos");
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(indiceFilas[i] + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(tablero[i][j] + " ");
            }

            System.out.print("            " + indiceFilas[i] + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(tableroDisparos[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("  0 1 2 3 4 5 6 7 8 9               0 1 2 3 4 5 6 7 8 9");
        System.out.println("");
    }

    /**
     * Este metodo implementa el disparo del jugador
     * @param tableroDisparosJugador para rellenar el tablero con acierto o fallo
     * @param tableroPC para rellenar el tablero con acierto o fallo
     * @return verdadero si se acierta el disparo
     */
    public static boolean disparoJugador(char[][] tableroDisparosJugador, char[][] tableroPC) {
        //  El vector barcos esta compuesto de 5 columnas:
        //    columna 0 = posición de la fila en el tablero
        //    columna 1 = posición de la columna en el tablero
        //    columna 2 = longitud de barco
        //    columna 3 = orientacion (Horizontal o vertical)
        //    columna 4 = caracter a colocar 0=B (barco), 1=T (Acierto Disparo), 2=* (Fallo Disparo)
        //  Todos los datos son enteros
        int[] barcos = new int[5];
        boolean aciertoDisparo = false;
        int fila = Entrada.coordenadaY();
        int columna = Entrada.coordenadaX();

        if (!hayColision(tableroPC, 1, fila, columna, 1)) {
            barcos[0] = fila;
            barcos[1] = columna;
            barcos[2] = 1;
            barcos[3] = 1;
            barcos[4] = fallo;
            colocarBarcosPC(tableroPC, barcos);
            colocarBarcosJugador(tableroDisparosJugador, barcos);
            aciertoDisparo = false;
        } else {
            barcos[0] = fila;
            barcos[1] = columna;
            barcos[2] = 1;
            barcos[3] = 1;
            barcos[4] = acierto;
            colocarBarcosPC(tableroPC, barcos);
            colocarBarcosJugador(tableroDisparosJugador, barcos);
            aciertoDisparo = true;
        }

        return aciertoDisparo;
    }

    /**
     * Este metodo implementa el disparo del PC
     * @param tableroDisparosPC para rellenar el tablero con acierto o fallo
     * @param tableroJugador para rellenar el tablero con acierto o fallo
     * @return verdadero si es acierto
     */
    public static boolean disparoPC(char[][] tableroDisparosPC, char[][] tableroJugador) {

        //  El vector barcos esta compuesto de 5 columnas:
        //    columna 0 = posición de la fila en el tablero
        //    columna 1 = posición de la columna en el tablero
        //    columna 2 = longitud de barco
        //    columna 3 = orientacion (Horizontal o vertical)
        //    columna 4 = caracter a colocar 0=B (barco), 1=T (Acierto Disparo), 2=* (Fallo Disparo)
        //  Todos los datos son enteros
        int[] barcos = new int[5];
        boolean aciertoDisparo = false;

        int fila = Entrada.coordenadaY();
        int columna = Entrada.coordenadaX();
        if (!hayColision(tableroJugador, 1, fila, columna, 1)) {
            barcos[0] = fila;
            barcos[1] = columna;
            barcos[2] = 1;
            barcos[3] = 1;
            barcos[4] = fallo;
            colocarBarcosPC(tableroJugador, barcos);
            colocarBarcosJugador(tableroDisparosPC, barcos);
            aciertoDisparo = false;
        } else {
            barcos[0] = fila;
            barcos[1] = columna;
            barcos[2] = 1;
            barcos[3] = 1;
            barcos[4] = acierto;
            colocarBarcosPC(tableroJugador, barcos);
            colocarBarcosJugador(tableroDisparosPC, barcos);
            aciertoDisparo = true;
        }
        return aciertoDisparo;
    }

    /**
     * Este metodo borra la terminal en linux
     */
    public static void borrarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Este metodo suma todos los valores de un vector. Se usara para saber cuantos barcos quedan
     * @param unVector vector a sumar
     * @return la cantidad de celdas como entero
     */
    public static int sumaCeldas(int[] unVector) {

        int totalCeldas = 0;
        for (int i = 0; i < 10; i++) {
            if (unVector[i] == 1) {
                totalCeldas++;
            }
        }
        return totalCeldas;
    }

    /**
     * Este metodo coloca los barcos pasados como vector dentro del tablero del Jugador
     * @param tablero el tablero del jugador
     * @param barcos el vector de barcos
     */
    public static void colocarBarcosJugador(char[][] tablero, int[] barcos) {

        //  El vector barcos esta compuesto de 5 columnas:
        //    columna 0 = posición de la fila en el tablero
        //    columna 1 = posición de la columna en el tablero
        //    columna 2 = longitud de barco
        //    columna 3 = orientacion (Horizontal o vertical)
        //    columna 4 = caracter a colocar 0=B (barco), 1=T (Acierto Disparo), 2=* (Fallo Disparo)
        //  Todos los datos son enteros

        if (barcos[3] == 1) {
            // Se coloca un barco en el tablero de manera horizontal
            for (int i = barcos[1]; i < (barcos[1] + barcos[2]); i++) {
                tablero[barcos[0]][i] = caracterPantalla(barcos[4]);
            }
        }

        if (barcos[3] == 0) {
            // Se coloca un barco en el tablero de manera vertical
            for (int i = barcos[0]; i < (barcos[0] + barcos[2]); i++) {
                tablero[i][barcos[1]] = caracterPantalla(barcos[4]);
            }
        }
    }

    /**
     * Este metodo coloca los barcos pasados como vector dentro del tablero del PC
     *
     * @param tablero el tablero del pc
     * @param barcos el vector de barcos
     */
    public static void colocarBarcosPC(char[][] tablero, int[] barcos) {

        //  El vector barcos esta compuesto de 5 columnas:
        //    columna 0 = posición de la fila en el tablero
        //    columna 1 = posición de la columna en el tablero
        //    columna 2 = longitud de barco
        //    columna 3 = orientacion (Horizontal o vertical)
        //    columna 4 = caracter a colocar 0=B (barco), 1=T (Acierto Disparo), 2=* (Fallo Disparo)
        //  Todos los datos son enteros

        if (barcos[3] == 1) {
            // Se coloca un barco en el tablero de manera horizontal
            for (int i = barcos[1]; i < (barcos[1] + barcos[2]); i++) {
                tablero[barcos[0]][i] = caracterPantalla(barcos[4]);
            }
        }

        if (barcos[3] == 0) {
            // Se coloca un barco en el tablero de manera vertical
            for (int i = barcos[0]; i < (barcos[0] + barcos[2]); i++) {
                tablero[i][barcos[1]] = caracterPantalla(barcos[4]);
            }
        }
    }

    /**
     * Este metodo comprueba si hay algún barco en la zona del barco a colocar
     *
     * @param tablero el tablero a comprobar
     * @param longitudBarco la longitud del barco
     * @param fila el valor de la fila
     * @param columna el valor de la columna
     * @param orientacion el valor de la orientacion
     * @return verdadero si hay colision, falso si no
     */
    public static boolean hayColision(char[][] tablero, int longitudBarco, int fila, int columna, int orientacion) {

        boolean hayColisionEnTablero = false;
        if (orientacion == 1) {
            // Se valida si no hay algún barco en las coordenadas X
            for (int i = columna; i < (columna + longitudBarco) && !hayColisionEnTablero; i++) {
                if (tablero[fila][i] == 'B') {
                    hayColisionEnTablero = true;
                }
            }
        }

        if (orientacion == 0) {
            // Se valida si no hay algún barco en las coordenadas Y
            for (int i = fila; i < (fila + longitudBarco) && !hayColisionEnTablero; i++) {
                if (tablero[i][columna] == 'B') {
                    hayColisionEnTablero = true;
                }
            }
        }
        return hayColisionEnTablero;
    }

    /**
     * Este metodo comprueba si el barco está en los límites del tablero
     * @param tablero el tablero a comprobar
     * @param longitudBarco la longitud del barco
     * @param fila el valor de la fila
     * @param columna el valor de la columna
     * @param orientacion el valor de la orientacion
     * @return verdadero si cabe, falso si no
     */
    public static boolean cabeBarco(char[][] tablero, int longitudBarco, int fila, int columna, int orientacion) {

        boolean CabeBarcoEnTablero = true;
        if (orientacion == 1) {
            // Horizontal se mueve por las columnas
            if (columna + longitudBarco > 10) {
                CabeBarcoEnTablero = false;
            }
        }

        if (orientacion == 0) {
            // Vertical se mueve por las filas
            if (fila + longitudBarco > 10) {
                CabeBarcoEnTablero = false;
            }
        }
        return CabeBarcoEnTablero;
    }

    /**
     * Este metodo coloca un barco en una posicion, cabe y no coincide con ninguna posicion de otro barco en la zona
     * @param tablero el tablero a comprobar
     * @param longitudBarco la longitud del barco
     * @param fila el valor de la fila
     * @param columna el valor de la columna
     * @param orientacion el valor de la orientacion
     * @param jugador jugador si es verdadero, falso si es PC
     * @return verdadero si el barco se coloca, falso si no
     */
    public static boolean colocarBarco(char[][] tablero, int longitudBarco, int fila, int columna, int orientacion, boolean jugador) {
        //Este metodo coloca un barco en una posicion, cabe y no coincide con ninguna posicion de otro barco en la zona
        //  El vector barcos esta compuesto de 5 columnas:
        //    columna 0 = posición de la fila en el tablero
        //    columna 1 = posición de la columna en el tablero
        //    columna 2 = longitud de barco
        //    columna 3 = orientacion (Horizontal o vertical)
        //    columna 4 = caracter a colocar 0=B (barco), 1=T (Acierto Disparo), 2=* (Fallo Disparo)
        //  Todos los datos son enteros
        int[] barcos = new int[5];
        boolean colocarBarcoOk = true;

        if (!cabeBarco(tablero, longitudBarco, fila, columna, orientacion)) {
            colocarBarcoOk = false;
            System.out.println("El barco no cabe en esa posicion. Vuelve a intentarlo");
        } else {
            if (hayColision(tablero, longitudBarco, fila, columna, orientacion)) {
                colocarBarcoOk = false;
                System.out.println("Ya existe un barco en la zona donde lo quieres colocar. Vuelve a intentarlo");
            } else {
                barcos[0] = fila;
                barcos[1] = columna;
                barcos[2] = longitudBarco;
                barcos[3] = orientacion;
                barcos[4] = barco;
                if (jugador) {
                    colocarBarcosJugador(tablero, barcos);
                } else {
                    colocarBarcosPC(tablero, barcos);
                }
            }
        }

        return colocarBarcoOk;
    }

    /**
     * Este metodo cambia el agua por acierto, barco, o fallo, segun el indice
     * @param indice el valor a colocar
     * @return el valor en char
     */
    private static char caracterPantalla(int indice) {
        char caracter = '~';
        switch (indice) {
            case 0:
                caracter = 'B';
                break;
            case 1:
                caracter = 'T';
                break;
            case 2:
                caracter = '*';
                break;
            default:
                caracter = '~';
                break;
        }
        return caracter;
    }
}
