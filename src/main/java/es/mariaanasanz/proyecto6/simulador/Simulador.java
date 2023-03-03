package es.mariaanasanz.proyecto6.simulador;

import es.mariaanasanz.proyecto6.ejercicios.Estadisticas;
import javafx.scene.input.KeyCode;

public class Simulador {

    /**
     * TODO: Debereis simular los datos de una partida con el objetivo de validar vuestros metodos implementados (14 puntos)
     * Es decir, tendreis que llamar desde aqui a todos vuestros metodos:
     *      - capturarEventoTeclado
     *      - mostrarEventosTeclado
     *      - teclaMasPulsada
     *      - objetoRecogido
     *      - mostrarObjetosRecogidos
     *      - mostrarQuienHaRecogidoMasObjetos
     *      - capturarDisparo
     *      - borrarDisparo
     *      - mostrarRatioPrecision
     * IMPORTANTE: tendreis que validar que llegan los parametros correctos
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * Para ello, se emplearan los argumentos de entrada de la siguiente manera:
     * @param args numFlechasIzquierda numFlechasDerecha numShift numEscape numOtrasTeclas numComidaJugador
     *             numJoyaJugador numComidaZarigueya numJoyaZarigueya numDisparosCerteros numDisparosFallidos numDisparosCerterosBorrar numDisparosFallidosBorrar
     */
    public static void main(String[] args) {

        int numFlechasIzquierdas = Integer.parseInt(args[0]);
        int numFlechasDerecha = Integer.parseInt(args[1]);
        int numShift = Integer.parseInt(args[2]);
        int numEscape = Integer.parseInt(args[3]);
        int numOtrasTeclas = Integer.parseInt(args[4]);
        int numComidaJugador = Integer.parseInt(args[5]);
        int numJoyaJugador = Integer.parseInt(args[6]);
        int numComidaZarigueya = Integer.parseInt(args[7]);
        int numJoyaZarigueya = Integer.parseInt(args[8]);
        int numDisparosCerteros = Integer.parseInt(args[9]);
        int numDisparosFallidos = Integer.parseInt(args[10]);
        int numDisparosCerterosBorrar = Integer.parseInt(args[11]);
        int numDisparosFallidosBorrar = Integer.parseInt(args[12]);

        for (int i = 0; i < numFlechasIzquierdas; i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.RIGHT);
        }
        for (int i = 0; i < numFlechasDerecha; i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.LEFT);
        }
        for (int i = 0; i < numShift; i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.SHIFT);
        }
        for (int i = 0; i < numEscape; i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.ESCAPE);
        }
        for (int i = 0; i < numOtrasTeclas; i++) {
            Estadisticas.capturarEventoTeclado(KeyCode.ASTERISK);
        }
        for (int i = 0; i < numComidaJugador; i++) {
            Estadisticas.objetoRecogido("JUGADOR", "comida");
        }
        for (int i = 0; i < numJoyaJugador; i++) {
            Estadisticas.objetoRecogido("JUGADOR", "gemas");
        }
        for (int i = 0; i < numComidaZarigueya; i++) {
            Estadisticas.objetoRecogido("ZARIGUEYA", "comida");
        }
        for (int i = 0; i < numJoyaZarigueya; i++) {
            Estadisticas.objetoRecogido("ZARIGUEYA", "gemas");
        }
        for (int i = 0; i < numDisparosCerteros; i++) {
            Estadisticas.capturarDisparo(true);
        }
        for (int i = 0; i < numDisparosFallidos; i++) {
            Estadisticas.capturarDisparo(false);
        }
        for (int i = 0; i < numDisparosCerterosBorrar; i++) {
            Estadisticas.borrarDisparo(true);
        }
        for (int i = 0; i < numDisparosFallidosBorrar; i++) {
            Estadisticas.borrarDisparo(false);
        }
        Estadisticas.mostrarEventosTeclado();
        Estadisticas.teclaMasPulsada();
        Estadisticas.mostrarObjetosRecogidos();
        Estadisticas.mostrarQuienHaRecogidoMasObjetos();
        Estadisticas.mostrarRatioPrecision();
    }

}
