/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exp2_s4_mauricio_ulisse_luz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author mauulisseluz
 */
public class Exp2_S4_Mauricio_Ulisse_luz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Crear un objeto Scanner para leer entradas del usuario.
        Scanner scanner = new Scanner(System.in);

        // Definir lista de entradas
        List<Map<String, double[]>> entradas = new ArrayList<>();

        // Definir las variables
        int eleccionPrimerMenu = 0;
        int eleccionSegundoMenu = 0;
        int edad = -1;
        String asiento;
        boolean entradaValida = false;
        boolean inputIsValid = false;
        boolean itemWasAdded = false;
        String tipoDeAsiento;
        int numeroDeAsiento;
        Map<String, int[]> precioEntrada = null;
        double precioFinal;
        boolean finalizarCompra = false;
        double total = 0;

        // Definir regex
        String regex = "^(?i:[abc])(?:[1-9]|[1-5][0-9]|60)$";
        Pattern pattern = Pattern.compile(regex);

        // Definir la lista de precios.
        List<Map<String, int[]>> precios = new ArrayList<>();

        Map<String, int[]> a = new HashMap<>();
        a.put("a", new int[] { 20000 });
        precios.add(a);

        Map<String, int[]> b = new HashMap<>();
        b.put("b", new int[] { 12000 });
        precios.add(b);

        Map<String, int[]> c = new HashMap<>();
        c.put("c", new int[] { 9000 });
        precios.add(c);

        // Define un array con las opciones disponibles
        String[] opcionesPrimerMenu = { "Comprar entrada", "Salir" };

        // Saludo de bienvenida
        System.out.println("Bienvenido al sistema de venta de entradas.");
        System.out.println();
        for (int i = 0; i < opcionesPrimerMenu.length; i++) {
            System.out.println("[" + (i + 1) + "] " + opcionesPrimerMenu[i]);
        }

        System.out.println();

        do {
            System.out.print("Seleccione el número de la acción que desea realizar: ");
            eleccionPrimerMenu = scanner.nextInt();

            // Comunicar error.
            if (eleccionPrimerMenu <= 0 || eleccionPrimerMenu > opcionesPrimerMenu.length) {
                System.out.println("**Por favor elegir una opción válida**\n");
            }

        } while (eleccionPrimerMenu <= 0 || eleccionPrimerMenu > opcionesPrimerMenu.length);

        if (eleccionPrimerMenu == 1) {

            do {

                // Pedir la edad del cliente.
                if (edad == -1) {
                    do {
                        System.out.println("Por favor indique su edad:");
                        edad = scanner.nextInt();
                        scanner.nextLine();
                    } while (edad <= 0);
                }

                // Imprimir mapa de asientos
                for (int i = 0; i < 60; i++) {
                    if (i % 12 == 0) {
                        System.out.println();
                    }

                    if (i < 3 || (i > 8 && i < 15) || (i > 20 && i < 36)) {
                        if (i < 9) {
                            System.out.print("[B" + 0 + (i + 1) + "]");
                        } else {
                            System.out.print("[B" + (i + 1) + "]");
                        }

                    } else if (i > 35) {
                        System.out.print("[C" + (i + 1) + "]");
                    } else {
                        if (i < 9) {
                            System.out.print("[A" + 0 + (i + 1) + "]");
                        } else {
                            System.out.print("[A" + (i + 1) + "]");
                        }
                    }
                }

                System.out.println("\n");

                do {

                    inputIsValid = false;
                    itemWasAdded = false;

                    // Pedir número de asiento.
                    do {
                        System.out.print("Por favor elija el número de asiento:");
                        asiento = scanner.nextLine();

                        Matcher matcher = pattern.matcher(asiento);

                        if (matcher.matches()) {
                            inputIsValid = true;
                        }

                    } while (!inputIsValid);

                    // Chequear asiento disponible.
                    do {
                        for (int i = 0; i < entradas.size(); i++) {
                            Map<String, double[]> entrada = entradas.get(i);
                            List<Map.Entry<String, double[]>> items = new ArrayList<>(entrada.entrySet());

                            for (int j = 0; j < items.size(); j++) {
                                Map.Entry<String, double[]> item = items.get(j);
                                String key = item.getKey();

                                if (key.equalsIgnoreCase(asiento)) {
                                    System.out.println();
                                    System.out.println("**Este asiento ya fue ingresado**");
                                    System.out.println();
                                    itemWasAdded = true;
                                }
                            }
                        }

                    } while (itemWasAdded);

                    tipoDeAsiento = asiento.substring(0, 1);
                    numeroDeAsiento = Integer.parseInt(asiento.substring(1, asiento.length()));

                    if (tipoDeAsiento.equalsIgnoreCase("a")) {
                        if ((numeroDeAsiento > 3 && numeroDeAsiento < 10)
                                || (numeroDeAsiento > 15 && numeroDeAsiento < 22)) {
                            precioEntrada = precios.get(0);
                            entradaValida = true;
                        } else {
                            System.out.println("**La entrada ingresada no existe**");
                        }
                    } else if (tipoDeAsiento.equalsIgnoreCase("b")) {
                        if (numeroDeAsiento < 4 || (numeroDeAsiento > 9 && numeroDeAsiento < 16)
                                || (numeroDeAsiento > 21 && numeroDeAsiento < 37)) {
                            precioEntrada = precios.get(1);
                            entradaValida = true;
                        } else {
                            System.out.println("**La entrada ingresada no existe**");
                        }
                    } else if (tipoDeAsiento.equalsIgnoreCase("c")) {
                        if (numeroDeAsiento > 36 && numeroDeAsiento < 61) {
                            precioEntrada = precios.get(2);
                            entradaValida = true;
                        } else {
                            System.out.println("**La entrada ingresada no existe**");
                        }
                    } else {
                        System.out.println("**La entrada ingresada no existe**");
                    }

                } while (!entradaValida);

                if (precioEntrada != null) {
                    // Capturar el precio.
                    int[] precioSinDescuento = precioEntrada.values().iterator().next();

                    // Calcular descuentos (10% estudiantes, 15% tercera edad)
                    if (edad < 18) {
                        precioFinal = precioSinDescuento[0] - (precioSinDescuento[0] * 0.1);
                    } else if (edad > 60) {
                        precioFinal = precioSinDescuento[0] - (precioSinDescuento[0] * 0.15);
                    } else {
                        precioFinal = precioSinDescuento[0];
                    }

                    total += precioFinal;

                    Map<String, double[]> entradaHash = new HashMap<>();
                    entradaHash.put(asiento.toUpperCase(), new double[] { precioFinal });
                    entradas.add(entradaHash);

                    for (int i = 0; i < entradas.size(); i++) {
                        Map<String, double[]> entrada = entradas.get(i);

                        List<Map.Entry<String, double[]>> items = new ArrayList<>(entrada.entrySet());

                        for (int j = 0; j < items.size(); j++) {
                            Map.Entry<String, double[]> item = items.get(j);

                            String key = item.getKey();
                            double precioAPagar = item.getValue()[0];

                            System.out.println();
                            System.out.println("Entrada " + (i + 1));
                            System.out.println("Ubicación del asiento: " + key);
                            System.out.println("Precio sin descuento: " + precioSinDescuento[0]);
                            System.out.println("Descuento: " + (edad < 18 ? "10%" : edad > 60 ? "15%" : "0%"));
                            System.out.println("Precio final: " + precioAPagar);
                            System.out.println();
                        }
                    }

                }
                System.out.println("Total: " + total);
                System.out.println();
                System.out.println("¿Qué desea hacer?");
                System.out.println();
                System.out.println("[1] Comprar otra entrada");
                System.out.println("[2] Finalizar compra");
                System.out.println();

                do {
                    System.out.print("Seleccione el número de la acción que desea realizar: ");
                    eleccionSegundoMenu = scanner.nextInt();
                    scanner.nextLine();
                } while (eleccionSegundoMenu <= 0 || eleccionSegundoMenu > 2);

                if (eleccionSegundoMenu == 2) {
                    finalizarCompra = true;
                    System.out.println("¡Hasta pronto!");
                }

            } while (!finalizarCompra);

        } else {
            System.out.println("¡Hasta pronto!");
        }

    }
}
