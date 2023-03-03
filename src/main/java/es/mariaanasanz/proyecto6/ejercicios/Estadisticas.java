package es.mariaanasanz.proyecto6.ejercicios;

import javafx.scene.input.KeyCode;

import java.util.*;

public class Estadisticas {

    private static LinkedHashMap<KeyCode, Integer> contadorEventosTeclado = new LinkedHashMap<KeyCode, Integer>();
    private static HashMap<String, HashMap<String, Integer>> contadorObjetosRecogidos = new HashMap<String, HashMap<String, Integer>>();
    private static ArrayList<Boolean> historicoDisparos = new ArrayList<Boolean>();

    public static void mostrarEstadisticasSeguro(){
        try {
            System.out.println("****************************************");
            mostrarEventosTeclado();
            System.out.println("****************************************");
            KeyCode code = teclaMasPulsada();
            if(code!=null) {
                System.out.println("La tecla que mas veces se ha pulsado ha sido: " + code.toString());
                System.out.println("****************************************");
            }
            mostrarObjetosRecogidos();
            System.out.println("****************************************");
            mostrarQuienHaRecogidoMasObjetos();
            System.out.println("****************************************");
            mostrarRatioPrecision();
            System.out.println("****************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void capturarEventoTecladoSeguro(KeyCode code) {
        try {
            capturarEventoTeclado(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void objetoRecogidoSeguro(String actor, String objeto) {
        try {
            objetoRecogido(actor, objeto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * DONE: Captura todos los eventos que se produzcan durante la partida en el LinkedHashMap contadorEventosTeclado (10 puntos)
     * Acabaremos poblando un HashMap que contenga la cantidad de veces que ha ocurrido cada evento de teclado
     * Los eventos posibles que pueden llegar son:
     *      - KeyCode.RIGHT  --> para desplazarse a la derecha
     *      - KeyCode.LEFT   --> para desplazarse a la izquierda
     *      - KeyCode.SHIFT  --> para correr
     *      - KeyCode.ESCAPE --> para cerrar la ventana
     *      - KeyCode.ASTERISK --> Este evento no llegara, sino que cualquier otro evento lo almacenaremos como si fuese KeyCode.ASTERISK
     * Ejemplo de validacion:
     *      code == KeyCode.RIGHT   o bien   code == RIGHT   dependiendo de como hagais la validacion (IntelliJ os lo dira)
     * IMPORTANTE: Se debera recoger el tipo de evento mediante la clausula switch
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     *
     * @param code el cogido de evento capturado
     */
    public static void capturarEventoTeclado(KeyCode code) {
        switch (code) {
            case RIGHT:
            case LEFT:
            case SHIFT:
            case ESCAPE:
                if (contadorEventosTeclado.containsKey(code)) {
                    Integer aux = contadorEventosTeclado.get(code);
                    contadorEventosTeclado.put(code, Integer.valueOf(aux.intValue() + 1));
                }
                else {
                    contadorEventosTeclado.put(code, Integer.valueOf(1));
                }
                break;
            default:
                if (contadorEventosTeclado.containsKey(KeyCode.ASTERISK)) {
                    Integer aux = contadorEventosTeclado.get(KeyCode.ASTERISK);
                    contadorEventosTeclado.put(KeyCode.ASTERISK, Integer.valueOf(aux.intValue() + 1));
                }
                else {
                    contadorEventosTeclado.put(KeyCode.ASTERISK, Integer.valueOf(1));
                }
                break;
        }

    }

    /**
     * DONE: Se debera mostrar por consola toda la informacion del LinkedHashMap contadorEventosTeclado con el siguiente formato (el orden SI importa) (8 puntos)
     * Teclas pulsadas durante la partida:
     *      - RIGHT: 55 veces
     *      - LEFT: 43 veces
     *      - SHIFT: 2 veces
     *      - ESCAPE: 1 vez
     *      - OTROS: 243 veces
     * IMPORTANTE: Se debera emplear el metodo entrySet() para recorrer las entradas
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarEventosTeclado(){
        StringBuilder sb = new StringBuilder();
        sb.append("Teclas pulsadas durante la partida:\n");
        int right = 0;
        int left = 0;
        int shift = 0;
        int escape = 0;
        int otros = 0;
        for (Map.Entry<KeyCode, Integer> entrada : contadorEventosTeclado.entrySet()) {
            switch (entrada.getKey()) {
                case RIGHT:
                    right = entrada.getValue().intValue();
                    break;
                case LEFT:
                    left = entrada.getValue().intValue();
                    break;
                case SHIFT:
                    shift = entrada.getValue().intValue();
                    break;
                case ESCAPE:
                    escape = entrada.getValue().intValue();
                    break;
                case ASTERISK:
                    otros += entrada.getValue().intValue();
                    break;
            }
        }
        sb.append("\t- RIGHT:").append(right).append(" veces\n");
        sb.append("\t- LEFT:").append(left).append(" veces\n");
        sb.append("\t- SHIFT:").append(shift).append(" veces\n");
        sb.append("\t- ESCAPE:").append(escape).append(" veces\n");
        sb.append("\t- OTROS:").append(otros).append(" veces\n");
        System.out.println(sb.toString());
    }

    /**
     * DONE: debera devolver el evento con mas ocurrencias del LinkedHashMap contadorEventosTeclado (6 puntos)
     * IMPORTANTE: Se debera emplear el metodo keySet() para recorrer las entradas
     * IMPORTANTE: Si el juego se cierra sin pulsar ninguna tecla, devera devolver KeyCode.ESCAPE
     * @return KeyCode mas frecuente
     */
    public static KeyCode teclaMasPulsada(){
        int max = Integer.MIN_VALUE;
        KeyCode maxKey = KeyCode.ASTERISK;
        for (KeyCode key : contadorEventosTeclado.keySet()) {
            if (contadorEventosTeclado.get(key).intValue() > max) {
                max = contadorEventosTeclado.get(key).intValue();
                maxKey = key;
            }
        }
        return maxKey;
    }

    /**
     * DONE: se debera almacenar la relacion de objetos que recoge cada actor en el HashMap contadorObjetosRecogidos (12 puntos)
     * IMPORTANTE: la primera clave del HashMap contadorObjetosRecogidos sera el actor
     * IMPORTANTE: para cada actor habra otro hashmap asociado con la relacion de objetos y las veces que estos se han recogido
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * @param actor sera o el jugador o la zarigueya
     * @param objeto sera o la comida o la gema
     */
    public static void objetoRecogido(String actor, String objeto){
        if (!contadorObjetosRecogidos.containsKey(actor)) {
            HashMap<String, Integer> objetoMap = new HashMap<>();
            objetoMap.put(objeto, Integer.valueOf(1));
            contadorObjetosRecogidos.put(actor, objetoMap);
        }
        else if (!contadorObjetosRecogidos.get(actor).containsKey(objeto)) {
            contadorObjetosRecogidos.get(actor).put(objeto, Integer.valueOf(1));
        }
        else {
            Integer aux = contadorObjetosRecogidos.get(actor).get(objeto);
            contadorObjetosRecogidos.get(actor).put(objeto, Integer.valueOf (aux.intValue() + 1));
        }
    }

    /**
     * DONE: Se debera mostrar por consola toda la informacion del HashMap contadorObjetosRecogidos con el siguiente formato (el orden no importa) (16 puntos)
     * Objetos recogidos durante la partida:
     *      - JUGADOR:
     *          - comida: 7 veces
     *          - gemas: 3 veces
     *      - ZARIGUEYA:
     *          - comida: 2 veces
     *          - gemas: 1 vez
     * IMPORTANTE: Se podra emplear el metodo deseado para recorrer las entradas
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarObjetosRecogidos(){
        StringBuilder sb = new StringBuilder();
        sb.append("Objetos recogidos durante la partida:\n");
        for (Map.Entry<String, HashMap<String, Integer>> actorEntrada : contadorObjetosRecogidos.entrySet()) {
            sb.append("\t- ").append(actorEntrada.getKey()).append(":\n");
            for (Map.Entry<String, Integer> objetoEntrada : actorEntrada.getValue().entrySet()) {
                sb.append("\t\t- ").append(objetoEntrada.getKey()).append(": ");
                sb.append(objetoEntrada.getValue().intValue()).append(" veces\n");
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * DONE: Se debera mostrar por consola quien ha recogido mas objetos en base al HashMap contadorObjetosRecogidos con el siguiente formato (14 puntos)
     * Quien ha recogido mas objetos ha sido... ¡[JUGADOR/ZARIGUEYA] con un total de [XX] objetos!
     * IMPORTANTE: Se debera emplear el metodo values() para sumar la cantidad de objetos
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarQuienHaRecogidoMasObjetos(){
        int maxObjetos = 0;
        int maxJugador = 0;
        int maxZarigueya = 0;
        String maxActor = "";
        for (Map.Entry<String, HashMap<String, Integer>> entrada : contadorObjetosRecogidos.entrySet()) {
            if (entrada.getKey().equals("JUGADOR")) {
                for (Integer value : entrada.getValue().values()) {
                    maxJugador += value.intValue();
                }
            }
            else {
                for (Integer value : entrada.getValue().values()) {
                    maxZarigueya += value.intValue();
                }
            }
        }
        if (maxJugador > maxZarigueya) {
            maxObjetos = maxJugador;
            maxActor = "JUGADOR";
        }
        else {
            maxObjetos = maxZarigueya;
            maxActor = "ZARIGUEYA";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Quien ha recogido mas objetos ha sido... ¡").append(maxActor).append(" con un total de ");
        sb.append(maxObjetos).append(" objetos!");
        System.out.println(sb.toString());
    }

    /**
     * TODO: Se debera incluir al ArrayList historicoDisparos los disparos que se efectuen durante el juego (el orden SI importa) (6 puntos)
     * AVISO: Cuando es certero, se invoca dos veces este metodo, no os preocupeis. Para eso luego teneis que implementar borrarDisparo
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * @param exito representa si el disparo es certero (true) o fallido (false)
     */
    public static void capturarDisparo(boolean exito){
        historicoDisparos.add(Boolean.valueOf(exito));
    }

    /**
     * TODO: Se debera eliminar el ULTIMO disparo QUE SE INDIQUE del ArrayList historicoDisparos (no vale eliminar cualquiera) (6 puntos)
     * AVISO: Es muy importante implementar correctamente este metodo para que el metodo mostrarRatioPrecision funcione correctamente
     * IMPORTANTE: Se debera evitar el autoboxing y el unboxing
     * @param exito representa si el disparo es certero (true) o fallido (false)
     */
    public static void borrarDisparo(boolean exito){

    }

    /**
     * TODO: Se debera mostrar por consola el porcentaje de precision en base al contenido del ArrayList historicoDisparos con el siguiente formato: (8 puntos)
     * Tienes una precision del [XX]%.
     * Adicionalmente, en base a la precision, se mostrara un mensaje adicional:
     *      Si la precion es entre un 67 y un 100% --> ¡Eres insuperable!
     *      Si la precion es entre un 34 y un 66% --> No esta nada mal
     *      Si la precion es entre un 0 y un 33% --> Deberias entrenar un poco mas...
     * IMPORTANTE: La precision se mide base a los disparos acertados entre el total de disparos
     * IMPORTANTE: Se debera emplear StringBuilder para construir la cadena a mostrar
     */
    public static void mostrarRatioPrecision(){

    }

    public static void main(String[] args) {
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
        HashMap<String,Integer> a1 = new HashMap<>();
        HashMap<String,Integer> a2 = new HashMap<>();
        /*
        a1.put("comida", 7);
        a1.put("gemas", 3);
        a2.put("comida", 2);
        a2.put("gemas", 5);
        map.put("JUGADOR", a1);
        map.put("ZARIGUEYA", a2);
        */
        objetoRecogido("ZARIGUEYA", "comida");
        objetoRecogido("ZARIGUEYA", "comida");
        objetoRecogido("ZARIGUEYA", "comida");
        objetoRecogido("ZARIGUEYA", "comida");
        objetoRecogido("ZARIGUEYA", "comida");
        objetoRecogido("ZARIGUEYA", "comida");
        objetoRecogido("ZARIGUEYA", "comida");
        objetoRecogido("ZARIGUEYA", "gemas");
        objetoRecogido("ZARIGUEYA", "gemas");
        objetoRecogido("ZARIGUEYA", "gemas");
        objetoRecogido("JUGADOR", "comida");
        objetoRecogido("JUGADOR", "comida");
        objetoRecogido("JUGADOR", "gemas");
        objetoRecogido("JUGADOR", "gemas");
        objetoRecogido("JUGADOR", "gemas");
        objetoRecogido("JUGADOR", "gemas");
        objetoRecogido("JUGADOR", "gemas");
        objetoRecogido("JUGADOR", "gemas");
        objetoRecogido("JUGADOR", "gemas");
        objetoRecogido("JUGADOR", "gemas");
        objetoRecogido("JUGADOR", "gemas");
        objetoRecogido("JUGADOR", "gemas");
        objetoRecogido("JUGADOR", "gemas");
        objetoRecogido("JUGADOR", "gemas");
        objetoRecogido("JUGADOR", "gemas");

        mostrarObjetosRecogidos();
        mostrarQuienHaRecogidoMasObjetos();
    }
}
