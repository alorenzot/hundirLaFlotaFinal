package org.example;

/**
 * La clase juegoHundirFlota es la clase principal y ejecuta el juego
 *
 * @author alorenzot
 * @since 07-11-23
 * @version 1.0
 */

public class juegoHundirFlota {

    public static void main(String[] args) {
        // Inicializamos las variables
        char[][] tableroJugador = new char[10][10];
        char[][] tableroPC = new char[10][10];
        char[][] tableroDisparosJugador = new char[10][10];
        char[][] tableroDisparosPC = new char[10][10];
        int[] unVector = new int[10];
        boolean jugador = true;
        String nombreJugador = "";
        int disparosCorrectosJugador = 0;
        int disparosCorrectosPC = 0;
        boolean turnoJugador = true;
        Tablero.borrarPantalla();

        // Se inicializa y se llena el Tablero del Jugador
        nombreJugador = Entrada.nombreJugador();
        Tablero.borrarPantalla();
        Tablero.inicializarTablero(tableroJugador);
        Tablero.inicializarTablero(tableroDisparosJugador);
        Tablero.visualizarTablero(tableroJugador, tableroDisparosJugador, jugador);
        agregarBarcoTablero(tableroJugador, tableroDisparosJugador, 1, 4, jugador);
        Tablero.borrarPantalla();
        Tablero.visualizarTablero(tableroJugador, tableroDisparosJugador, jugador);
        agregarBarcoTablero(tableroJugador, tableroDisparosJugador, 2, 3, jugador);
        Tablero.borrarPantalla();
        Tablero.visualizarTablero(tableroJugador, tableroDisparosJugador, jugador);
        agregarBarcoTablero(tableroJugador, tableroDisparosJugador, 2, 2, jugador);
        Tablero.borrarPantalla();
        Tablero.visualizarTablero(tableroJugador, tableroDisparosJugador, jugador);
        agregarBarcoTablero(tableroJugador, tableroDisparosJugador, 1, 1, jugador);
        Tablero.borrarPantalla();
        Tablero.visualizarTablero(tableroJugador, tableroDisparosJugador, jugador);

        // Se inicializa y se llena el Tablero del PC
        jugador = false;
        Tablero.borrarPantalla();
        Tablero.inicializarTablero(tableroPC);
        Tablero.inicializarTablero(tableroDisparosPC);
        Tablero.visualizarTablero(tableroPC, tableroDisparosPC, jugador);
        agregarBarcoTablero(tableroPC, tableroDisparosPC, 1, 4, jugador);
        Tablero.borrarPantalla();
        Tablero.visualizarTablero(tableroPC, tableroDisparosPC, jugador);
        agregarBarcoTablero(tableroPC, tableroDisparosPC, 2, 3, jugador);
        Tablero.borrarPantalla();
        Tablero.visualizarTablero(tableroPC, tableroDisparosPC, jugador);
        agregarBarcoTablero(tableroPC, tableroDisparosPC, 2, 2, jugador);
        Tablero.borrarPantalla();
        Tablero.visualizarTablero(tableroPC, tableroDisparosPC, jugador);
        agregarBarcoTablero(tableroPC, tableroDisparosPC, 1, 1, jugador);
        Tablero.borrarPantalla();
        Tablero.visualizarTablero(tableroPC, tableroDisparosPC, jugador);
        Tablero.visualizarTablero(tableroJugador, tableroDisparosJugador, !jugador);

        //Contamos los barcos restantes del jugador
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                unVector[j] = 0;
                if (tableroJugador[i][j] == 'B') {
                    unVector[j] = 1;
                }
            }
            disparosCorrectosJugador = disparosCorrectosJugador + Tablero.sumaCeldas(unVector);
        }
        //Contamos los barcos restantes del pc
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                unVector[j] = 0;
                if (tableroPC[i][j] == 'B') {
                    unVector[j] = 1;
                }
            }
            disparosCorrectosPC = disparosCorrectosPC + Tablero.sumaCeldas(unVector);
        }

        System.out.println("Disparos correctos para gana el jugador: " + disparosCorrectosJugador);
        System.out.println("Disparos correctos para gana el PC: " + disparosCorrectosPC);

        while (disparosCorrectosJugador > 0 && disparosCorrectosPC > 0) {
            if (turnoJugador) {
                System.out.println("Turno de " + nombreJugador);
                if (Tablero.disparoJugador(tableroDisparosJugador, tableroPC)) {
                    disparosCorrectosJugador--;
                }
                turnoJugador = false;
            } else {
                System.out.println("Turno de PC");
                if (Tablero.disparoPC(tableroDisparosPC, tableroJugador)) {
                    disparosCorrectosPC--;
                }
                turnoJugador = true;
            }
            Tablero.borrarPantalla();
            Tablero.visualizarTablero(tableroPC, tableroDisparosPC, jugador);
            Tablero.visualizarTablero(tableroJugador, tableroDisparosJugador, !jugador);
            System.out.println("Disparos correctos para gana el jugador: " + disparosCorrectosJugador);
            System.out.println("Disparos correctos para gana el PC: " + disparosCorrectosPC);
        }

        //Cuando el contador llega a 0 se imprime el mensaje de victoria
        if (disparosCorrectosJugador == 0) {
            System.out.println("Enhorabuena " + nombreJugador + ", ¡has ganado!");
        }
        if (disparosCorrectosPC == 0) {
            System.out.println("Enhorabuena PC, ¡has ganado!");
        }
    }

    /**
     * Este método coloca el barco si todas las demás funciones se cumplen
     * @param tablero - Elige un tablero
     * @param tableroDisparos - Elije un tablero de disparos
     * @param cantidadBarcos - Elige la cantidad de barcos
     * @param longitudBarco - Elige la longitud en celdas del barco
     * @param jugador - Decide si se trabaja con el jugador o no
     */
    public static void agregarBarcoTablero(char[][] tablero, char[][] tableroDisparos, int cantidadBarcos, int longitudBarco, boolean jugador) {
        int cantidad = 1;
        int fila;
        int columna;
        int orientacion;
        while (cantidad <= cantidadBarcos) {
            System.out.println("Vamos a proceder a colocar el barco de tamaño " + longitudBarco + ".");
            fila = Entrada.coordenadaY();
            columna = Entrada.coordenadaX();
            orientacion = Entrada.orientacion();
            while (!Tablero.colocarBarco(tablero, longitudBarco, fila, columna, orientacion, jugador)) {
                fila = Entrada.coordenadaY();
                columna = Entrada.coordenadaX();
                orientacion = Entrada.orientacion();
            }
            Tablero.borrarPantalla();
            Tablero.visualizarTablero(tablero, tableroDisparos, jugador);
            cantidad++;
        }
    }
}