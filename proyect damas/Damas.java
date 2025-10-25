package proyect damas;

import java.util.Scanner;

class Ficha{
    private  String color;

    public Ficha(String color) {
        this.color = color;
    }

    public String getColor () {
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
                tablero[r][c] = ""; // vacío
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
}







public class Damas {
    
}
  