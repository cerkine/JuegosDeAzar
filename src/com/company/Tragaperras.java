package com.company;

import java.util.*;


public class Tragaperras {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    String[] symbols = {"#", "\033[31m🍎\033[0m", "\033[31m🍒\033[0m", "\033[31m🍓\033[0m", "\033[31m🍅\033[0m", "\033[30m\uD83D\uDC8E\033[0m", "\033[31m\uD83D\uDC8E\033[0m", "\033[34m\uD83D\uDC8E\033[0m", "\033[93m🍑\033[0m",};
    int [][]rotor = {{1,1,6,4,3,4,2,2,5,7,2,3,},{3,2,7,4,3,4,1,1,6,2,2,5},{5,5,4,4,6,1,3,3,7,7,2,2}};

    final int MANZANA = 1;
    final int CEREZA = 2;
    final int FRESA = 3;
    final int TOMATE = 4;
    final int DIAMANTEBLANCO = 5;
    final int DIAMANTEROJO = 6;
    final int DIAMANTEAZUL = 7;

    int[][] tablero = new int[3][3];
    final int ARRIBA = 0;
    final int CENTRO = 1;
    final int ABAJO = 2;

    boolean medio, encima, debajo, diagonalArribaAbajo, diagonalAbajoArriba;

    int creditojuego=1;
    int[] ganancia= new int [25];
    int money = 100;
    int totalganado;
    int apuesta;
    int creditogastado;

    void start() {
        do {
            pedirapuesta();
            if (apuesta > 0) {
                iniciartablero();
                eleccion();
                mostrartablero();


            } else {
                System.out.println("ADIOS ADIOS");
            }
        } while (money < 0 || apuesta!=0);

    }

    void pedirapuesta() {
        do {
            System.out.println("MONEY: " + money);
            System.out.println("Apuesta:");
            apuesta = scanner.nextInt();
            scanner.nextLine();

            if (apuesta > money) {
                System.out.println("NO TIENES TANTO DINERO");
            }
        } while (apuesta > money);

        money -= apuesta;
    }
    void contadorapuesta(){
        for (int n=ganancia.length-1;n>0;n--){
            ganancia[n] = ganancia[n-1];
//            if(ganancia[n-1]==ganancia[0]){
//            totalganado +=ganancia[n]+ganancia[0];
//            }else{
//                totalganado = 0;
//                for(int y=ganancia.length;y>0;y--){
//                    totalganado += ganancia[y];
//                }
//            }
        }
        totalganado = 0;
        for (int n = 1; n<ganancia.length;n++){
            totalganado += ganancia[n];
        }
    }
    void iniciartablero(){
        for (int n = 0; n < tablero.length; n++) {
            for (int y = 0; y < tablero[n].length; y++) {
                tablero[n][y] =0;
                System.out.print(symbols[tablero[n][y]]);
            }
            System.out.println();

        }
    }
    void mostrartablero() {
        contadorapuesta();

        System.out.println("Creditos: " + ganancia[0]);
        if (totalganado<20 && creditogastado>25){
            tablero[0][0]=rotor[0][6];
            tablero[0][1]=rotor[0][7];
            tablero[0][2]=rotor[0][8];
            tablero[1][0]=rotor[1][10];
            tablero[1][1]=rotor[1][11];
            tablero[1][2]=rotor[1][0];
            tablero[2][0]=rotor[2][1];
            tablero[2][1]=rotor[2][2];
            tablero[2][2]=rotor[2][3];
            for (int p=0;p<ganancia.length;p++){
                ganancia[p]=0;
            }
            creditogastado=0;
            for (int n = 0; n < tablero.length; n++) {
                for (int y = 0; y < tablero[n].length; y++) {

                    System.out.print(symbols[tablero[n][y]]);
                }  System.out.println();
            }

        }else {
            for (int n = 0; n < tablero.length; n++) {
                int valor = random.nextInt(12);
                if (valor == 11) {
                    tablero[n][0] = rotor[n][valor];
                    tablero[n][1] = rotor[n][0];
                    tablero[n][2] = rotor[n][1];
                    for (int y = 0; y < tablero[n].length; y++) {

                        System.out.print(symbols[tablero[n][y]]);
                    }
                    System.out.println();

                } else if (valor == 10) {
                    tablero[n][0] = rotor[n][valor];
                    tablero[n][1] = rotor[n][valor + 1];
                    tablero[n][2] = rotor[n][0];
                    for (int y = 0; y < tablero[n].length; y++) {

                        System.out.print(symbols[tablero[n][y]]);
                    }
                    System.out.println();

                } else {
                    for (int y = 0; y < tablero[n].length; y++) {
                        tablero[n][y] = rotor[n][valor + y];
                        System.out.print(symbols[tablero[n][y]]);
                    }
                    System.out.println();
                }
            }
        }
        comprobar();
        eleccion();
    }

    void cambiarcredito() {
        System.out.println("Cuanto credito quieres apostar por tirada");
        creditojuego=scanner.nextInt();scanner.nextLine();
        switch (creditojuego){
            case 1:
                eleccion();
                break;
            case 2:
                eleccion();
                break;
            case 3:
                eleccion();
                break;
            default:
                System.out.println("Opcion no valida (1-3)");
                cambiarcredito();
                break;
        }
    }
    void eleccion() {
        if (apuesta > 0) {
            System.out.println("Siguente jugada pulsa j,si quieres cambiar el credito por tirada pulsa c, si quieres retirar dinero pulsa s");
            char opcion = scanner.nextLine().charAt(0);
            switch (opcion) {
                case 's':
                    money += apuesta;
                    System.out.println("Muy bien tu dinero es: " + money);
                    break;
                case 'j':
                    apuesta -= creditojuego;
                    creditogastado+=creditojuego;
                    mostrartablero();
                    break;
                case 'c':
                    cambiarcredito();
                default:
                    System.out.println("Tecla no aceptada");
                    eleccion();
                    break;
            }
        } else {
            System.out.println("Te has quedado sin creditos");
            start();
        }
    }

    void comprobar() {
        medio = tablero[CENTRO][CENTRO] == tablero[CENTRO][CENTRO - 1] && tablero[CENTRO][CENTRO] == tablero[CENTRO][CENTRO + 1];
        encima = tablero[ARRIBA][CENTRO] == tablero[ARRIBA][CENTRO - 1] && tablero[ARRIBA][CENTRO] == tablero[ARRIBA][CENTRO + 1];
        debajo = tablero[ABAJO][CENTRO] == tablero[ABAJO][CENTRO - 1] && tablero[ABAJO][CENTRO] == tablero[ABAJO][CENTRO + 1];
        diagonalArribaAbajo = tablero[CENTRO][CENTRO] == tablero[ARRIBA][CENTRO - 1] && tablero[CENTRO][CENTRO] == tablero[ABAJO][CENTRO + 1];
        diagonalAbajoArriba = tablero[CENTRO][CENTRO] == tablero[ABAJO][CENTRO - 1] && tablero[CENTRO][CENTRO] == tablero[ARRIBA][CENTRO + 1];

        if (creditojuego == 1) {
            if (medio) {
                if (medio) {
                    if (tablero[CENTRO][CENTRO] == MANZANA) {
                        ganancia[0] = 3;
                    } else if (tablero[CENTRO][CENTRO] == CEREZA) {
                        ganancia[0] = 3;
                    } else if (tablero[CENTRO][CENTRO] == FRESA) {
                        ganancia[0] = 4;
                    } else if (tablero[CENTRO][CENTRO] == TOMATE) {
                        ganancia[0] = 5;
                    } else if (tablero[CENTRO][CENTRO] == DIAMANTEBLANCO) {
                        ganancia[0] = 20;
                    } else if (tablero[CENTRO][CENTRO] == DIAMANTEROJO) {
                        ganancia[0] = 40;
                    } else if (tablero[CENTRO][CENTRO] == DIAMANTEAZUL) {
                        ganancia[0] = 80;
                    }
                }
            }
        } else if (creditojuego == 2) {
            if (medio || encima || debajo) {
                if (medio) {
                    if (tablero[CENTRO][CENTRO] == MANZANA) {
                        ganancia[0] = 3;
                    } else if (tablero[CENTRO][CENTRO] == CEREZA) {
                        ganancia[0] = 3;
                    } else if (tablero[CENTRO][CENTRO] == FRESA) {
                        ganancia[0] = 4;
                    } else if (tablero[CENTRO][CENTRO] == TOMATE) {
                        ganancia[0] = 5;
                    } else if (tablero[CENTRO][CENTRO] == DIAMANTEBLANCO) {
                        ganancia[0] = 20;
                    } else if (tablero[CENTRO][CENTRO] == DIAMANTEROJO) {
                        ganancia[0] = 40;
                    } else if (tablero[CENTRO][CENTRO] == DIAMANTEAZUL) {
                        ganancia[0] = 80;
                    }
                }
                if (debajo) {
                    if (tablero[ABAJO][CENTRO] == MANZANA) {
                        ganancia[0] = 3;
                    } else if (tablero[ABAJO][CENTRO] == CEREZA) {
                        ganancia[0] = 3;
                    } else if (tablero[ABAJO][CENTRO] == FRESA) {
                        ganancia[0] = 4;
                    } else if (tablero[ABAJO][CENTRO] == TOMATE) {
                        ganancia[0] = 5;
                    } else if (tablero[ABAJO][CENTRO] == DIAMANTEBLANCO) {
                        ganancia[0] = 20;
                    } else if (tablero[ABAJO][CENTRO] == DIAMANTEROJO) {
                        ganancia[0] = 40;
                    } else if (tablero[ABAJO][CENTRO] == DIAMANTEAZUL) {
                        ganancia[0] = 80;
                    }
                }
                if (encima) {
                    if (tablero[ARRIBA][CENTRO] == MANZANA) {
                        ganancia[0] = 3;
                    } else if (tablero[ARRIBA][CENTRO] == CEREZA) {
                        ganancia[0] = 3;
                    } else if (tablero[ARRIBA][CENTRO] == FRESA) {
                        ganancia[0] = 4;
                    } else if (tablero[ARRIBA][CENTRO] == TOMATE) {
                        ganancia[0] = 5;
                    } else if (tablero[ARRIBA][CENTRO] == DIAMANTEBLANCO) {
                        ganancia[0] = 20;
                    } else if (tablero[ARRIBA][CENTRO] == DIAMANTEROJO) {
                        ganancia[0] = 40;
                    } else if (tablero[ARRIBA][CENTRO] == DIAMANTEAZUL) {
                        ganancia[0] = 80;
                    }
                }

            }
        } else if (creditojuego == 3) {
            if (medio || encima || debajo || diagonalArribaAbajo || diagonalAbajoArriba){

                boolean[] lineas = {medio, diagonalAbajoArriba, diagonalArribaAbajo};
                    for (int i = 0; i < lineas.length; i++) {
                        if (lineas[i]) {
                            if (tablero[CENTRO][CENTRO] == MANZANA) {
                                ganancia[0] = 3;
                            } else if (tablero[CENTRO][CENTRO] == CEREZA) {
                                ganancia[0] = 3;
                            } else if (tablero[CENTRO][CENTRO] == FRESA) {
                                ganancia[0] = 4;
                            } else if (tablero[CENTRO][CENTRO] == TOMATE) {
                                ganancia[0] = 5;
                            } else if (tablero[CENTRO][CENTRO] == DIAMANTEBLANCO) {
                                ganancia[0] = 20;
                            } else if (tablero[CENTRO][CENTRO] == DIAMANTEROJO) {
                                ganancia[0] = 40;
                            } else if (tablero[CENTRO][CENTRO] == DIAMANTEAZUL) {
                                ganancia[0] = 80;
                            }
                        }
                    }
                    if (debajo) {
                        if (tablero[ABAJO][CENTRO] == MANZANA) {
                            ganancia[0] = 3;
                        } else if (tablero[ABAJO][CENTRO] == CEREZA) {
                            ganancia[0] = 3;
                        } else if (tablero[ABAJO][CENTRO] == FRESA) {
                            ganancia[0] = 4;
                        } else if (tablero[ABAJO][CENTRO] == TOMATE) {
                            ganancia[0] = 5;
                        } else if (tablero[ABAJO][CENTRO] == DIAMANTEBLANCO) {
                            ganancia[0] = 20;
                        } else if (tablero[ABAJO][CENTRO] == DIAMANTEROJO) {
                            ganancia[0] = 40;
                        } else if (tablero[ABAJO][CENTRO] == DIAMANTEAZUL) {
                            ganancia[0] = 80;
                        }
                    }
                    if (encima) {
                        if (tablero[ARRIBA][CENTRO] == MANZANA) {
                            ganancia[0] = 3;
                        } else if (tablero[ARRIBA][CENTRO] == CEREZA) {
                            ganancia[0] = 3;
                        } else if (tablero[ARRIBA][CENTRO] == FRESA) {
                            ganancia[0] = 4;
                        } else if (tablero[ARRIBA][CENTRO] == TOMATE) {
                            ganancia[0] = 5;
                        } else if (tablero[ARRIBA][CENTRO] == DIAMANTEBLANCO) {
                            ganancia[0] = 20;
                        } else if (tablero[ARRIBA][CENTRO] == DIAMANTEROJO) {
                            ganancia[0] = 40;
                        } else if (tablero[ARRIBA][CENTRO] == DIAMANTEAZUL) {
                            ganancia[0] = 80;
                        }
                    }
            }
        }
        apuesta+=ganancia[0];
        if (totalganado>=10){
            creditogastado=0;
        }

        System.out.println("Has ganado CREDITO: " + ganancia[0]);
        System.out.println("TOTAL CREDITO: " + apuesta);
        ganancia[0]=0;

    }

//    void devolucion(){
//        boolean[] lineas = {medio, diagonalAbajoArriba ,diagonalArribaAbajo};
//        if (comprobar()){
//            for(int i=0; i<lineas.length; i++) {
//                if (lineas[i]) {
//                    if (tablero[CENTRO][CENTRO] == MANZANA) {
//                        ganancia[0] += 3;
//                    } else if (tablero[CENTRO][CENTRO] == CEREZA) {
//                        ganancia[0] += 3;
//                    } else if (tablero[CENTRO][CENTRO] == FRESA) {
//                        ganancia[0] += 4;
//                    } else if (tablero[CENTRO][CENTRO] == TOMATE) {
//                        ganancia[0] += 5;
//                    } else if (tablero[CENTRO][CENTRO] == DIAMANTEBLANCO) {
//                        ganancia[0] += 20;
//                    } else if (tablero[CENTRO][CENTRO] == DIAMANTEROJO) {
//                        ganancia[0] += 40;
//                    } else if (tablero[CENTRO][CENTRO] == DIAMANTEAZUL) {
//                        ganancia[0] += 80;
//                    }
//                }
//            }
//
//            if(debajo){
//                if (tablero[CENTRO][CENTRO]==MANZANA){
//                    ganancia[0]+=3;
//                }else if (tablero[CENTRO][CENTRO]==CEREZA){
//                    ganancia[0]+=3;
//                }else if (tablero[CENTRO][CENTRO]==FRESA){
//                    ganancia[0]+=4;
//                }else if (tablero[CENTRO][CENTRO]==TOMATE){
//                    ganancia[0]+=5;
//                }else if (tablero[CENTRO][CENTRO]==DIAMANTEBLANCO){
//                    ganancia[0]+=20;
//                }else if (tablero[CENTRO][CENTRO]==DIAMANTEROJO){
//                    ganancia[0]+=40;
//                }else if (tablero[CENTRO][CENTRO]==DIAMANTEAZUL){
//                    ganancia[0]+=80;
//                }
//            }
//
//            if(encima){
//                if (tablero[CENTRO][CENTRO]==MANZANA){
//                    ganancia[0]+=3;
//                }else if (tablero[CENTRO][CENTRO]==CEREZA){
//                    ganancia[0]+=3;
//                }else if (tablero[CENTRO][CENTRO]==FRESA){
//                    ganancia[0]+=4;
//                }else if (tablero[CENTRO][CENTRO]==TOMATE){
//                    ganancia[0]+=5;
//                }else if (tablero[CENTRO][CENTRO]==DIAMANTEBLANCO){
//                    ganancia[0]+=20;
//                }else if (tablero[CENTRO][CENTRO]==DIAMANTEROJO){
//                    ganancia[0]+=40;
//                }else if (tablero[CENTRO][CENTRO]==DIAMANTEAZUL){
//                    ganancia[0]+=80;
//                }
//            }
//        }
//    }
}