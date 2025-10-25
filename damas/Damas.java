package damas;

import java.util.Scanner; 



class Ficha {
    private String color;

    public Ficha(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

class Tablero {
    private String[][] tablero;

    public Tablero() {
        tablero = new String[8][8];
        inicializar();
    }

    
    private void inicializar() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tablero[r][c] = "";
            }
        }

        
        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 == 1) tablero[r][c] = "B";
            }
        }

        
        for (int r = 5; r <= 7; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 == 1) tablero[r][c] = "R";
            }
        }
    }

    
    public void imprimir() {
        System.out.println("   1 2 3 4 5 6 7 8");
        System.out.println("  -----------------");
        for (int r = 0; r < 8; r++) {
            System.out.print((r + 1) + "| ");
            for (int c = 0; c < 8; c++) {
                String val = tablero[r][c];
                if (val.equals("")) System.out.print(". ");
                else System.out.print(val + " ");
            }
            System.out.println("|");
        }
        System.out.println("  -----------------");
    }

    
    public boolean movimientoValido(int r1, int c1, int r2, int c2, String turno) {
        if (!dentroTablero(r1, c1) || !dentroTablero(r2, c2)) return false; 
        if (tablero[r1][c1].equals("")) return false; 
        if (!tablero[r2][c2].equals("")) return false; 
        if (!tablero[r1][c1].equals(turno)) return false; 

        int dr = Math.abs(r2 - r1);
        int dc = Math.abs(c2 - c1);
        if (!(dr == 1 && dc == 1)) return false;

       
        if (turno.equals("R") && r2 != r1 + 1) return false; 
        if (turno.equals("B") && r2 != r1 - 1) return false; 

        return true;
    }

    
    public void mover(int r1, int c1, int r2, int c2) {
        tablero[r2][c2] = tablero[r1][c1];
        tablero[r1][c1] = "";
    }

    private boolean dentroTablero(int r, int c) {
        return r >= 0 && r < 8 && c >= 0 && c < 8;
    }

   
    public int contarFichas(String color) {
        int contador = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (tablero[r][c].equals(color)) contador++;
            }
        }
        return contador;
    }
}

public class Damas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tablero tablero = new Tablero();
        String turno = "R";

        System.out.println("=== JUEGO DE DAMAS ===");
        System.out.println("Jugador 1: R (rojas)");
        System.out.println("Jugador 2: B (negras)");
        System.out.println("Solo se permiten movimientos diagonales de 1 casilla.");
        System.out.println("El juego termina cuando un jugador se queda sin fichas.\n");

        while (true) {
            tablero.imprimir();
            System.out.println("Turno de: " + (turno.equals("R") ? "Jugador 1 (ROJAS)" : "Jugador 2 (NEGRAS)"));

            try {
                System.out.print("Fila origen: ");
                int orFila = sc.nextInt();
                System.out.print("Columna origen: ");
                int orCol = sc.nextInt();
                System.out.print("Fila destino: ");
                int deFila = sc.nextInt();
                System.out.print("Columna destino: ");
                int deCol = sc.nextInt();

                int r1 = orFila - 1;
                int c1 = orCol - 1;
                int r2 = deFila - 1;
                int c2 = deCol - 1;

                if (tablero.movimientoValido(r1, c1, r2, c2, turno)) {
                    tablero.mover(r1, c1, r2, c2);

                    
                    int rojas = tablero.contarFichas("R");
                    int negras = tablero.contarFichas("B");

                    if (rojas == 0) {
                        System.out.println("\n¡GANAN LAS NEGRAS (Jugador 2)! 🏆");
                        break;
                    } else if (negras == 0) {
                        System.out.println("\n¡GANAN LAS ROJAS (Jugador 1)! 🏆");
                        break;
                    }

                
                    turno = turno.equals("R") ? "B" : "R";
                } else {
                    System.out.println("Movimiento inválido. Intenta otra vez.\n");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Usa solo números entre 1 y 8.\n");
                sc.nextLine(); 
            }
        }

        sc.close();
        System.out.println("Fin del juego. ¡Gracias por jugar! 🎮");
    }
}
