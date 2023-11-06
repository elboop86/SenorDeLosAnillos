package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Ejercito {
    private List<Personaje> personajes;

    public Ejercito() {
        personajes = new ArrayList<>();
    }

    // Obtener la lista de personajes
    public List<Personaje> getPersonajes() {
        return personajes;
    }

    // Establecer la lista de personajes
    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    // Agregar un personaje a la lista
    public void agregarPersonaje(Personaje personaje) {
        personajes.add(personaje);
    }

    // Seleccionar un personaje para la batalla según la posición en la lista
    public Personaje seleccionarPersonajeParaBatalla(int posicion) {
        if (posicion >= 0 && posicion < personajes.size()) {
            return personajes.get(posicion);
        } else {
            return null;
        }
    }

    // Realizar un ataque a un adversario
    public void realizarAtaque(Personaje adversario) {
        for (Personaje personaje : personajes) {
            personaje.atacar(adversario);
        }
    }

    // Verificar si el ejército está derrotado (todos los personajes muertos)
    public boolean estaDerrotado() {
        boolean todosMuertos = true;

        for (Personaje personaje : personajes) {
            if (personaje != null && personaje.estaVivo()) {
                todosMuertos = false;
                break;
            }
        }

        return todosMuertos;
    }

    // Verificar si el ejército está totalmente derrotado (todos los personajes muertos o nulos)
    public boolean estaTotalmenteDerrotado() {
        for (Personaje personaje : personajes) {
            if (personaje != null && personaje.estaVivo()) {
                return false;
            }
        }

        return true;
    }
}