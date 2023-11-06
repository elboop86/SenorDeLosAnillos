package org.example;


import org.example.entity.Juego;

public class Main {
    public static void main(String[] args) {
     Juego juego = new Juego();
        juego.crearEjercitos();
        juego.manejarTurnos();
}
}