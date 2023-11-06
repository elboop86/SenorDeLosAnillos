package org.example.entity;

public class Juego {
    private Ejercito ejercitoHeroes;
    private Ejercito ejercitoBestias;

    public Juego() {
        ejercitoHeroes = new Ejercito();
        ejercitoBestias = new Ejercito();
    }

    // Getters y Setters (omitiendo por brevedad)

    // Método para crear los ejércitos de héroes y bestias
    public void crearEjercitos() {
        // Creación de los héroes
        Heroe legolas = new Heroe("Légolas", 150, 30, "Elfo");
        Heroe aragorn = new Heroe("Aragorn", 150, 50, "Humano");
        Heroe boromir = new Heroe("Boromir", 100, 60, "Humano");
        Heroe gandalf = new Heroe("Gandalf", 300, 30, "Humano");
        Heroe frodo = new Heroe("Frodo", 20, 10, "Hobbit");

        // Agregar los héroes al ejército de héroes
        ejercitoHeroes.agregarPersonaje(legolas);
        ejercitoHeroes.agregarPersonaje(aragorn);
        ejercitoHeroes.agregarPersonaje(boromir);
        ejercitoHeroes.agregarPersonaje(gandalf);
        ejercitoHeroes.agregarPersonaje(frodo);

        // Creación de las bestias
        Bestia lurtz = new Bestia("Lurtz", 200, 60, "Orco");
        Bestia shagrat = new Bestia("Shagrat", 220, 50, "Orco");
        Bestia ugluk = new Bestia("Uglúk", 120, 30, "Trasgo");
        Bestia mauhur = new Bestia("Mauhúr", 100, 30, "Trasgo");

        // Agregar las bestias al ejército de bestias
        ejercitoBestias.agregarPersonaje(lurtz);
        ejercitoBestias.agregarPersonaje(shagrat);
        ejercitoBestias.agregarPersonaje(ugluk);
        ejercitoBestias.agregarPersonaje(mauhur);
    }

    // Método para manejar los turnos del juego
    public void manejarTurnos() {
        for (int i = 0; i < ejercitoHeroes.getPersonajes().size(); i++) {
            if (i > ejercitoBestias.getPersonajes().size() - 1) {
                ejercitoHeroes.getPersonajes().remove(i);
            }
        }

        boolean seguirJugando = true;
        int turno = 1;

        while (seguirJugando && !ejercitoHeroes.estaDerrotado() && !ejercitoBestias.estaDerrotado()) {
            int posicion = 0;
            System.out.println("Turno " + turno + ":");
                 // Iterar a través de los personajes de los ejércitos
            for (posicion = 0; posicion < ejercitoHeroes.getPersonajes().size(); posicion++) {
                Personaje heroe = ejercitoHeroes.seleccionarPersonajeParaBatalla(posicion);
                Personaje bestia = ejercitoBestias.seleccionarPersonajeParaBatalla(posicion);

                     // Verificar si ambos personajes están vivos y realizar los ataques
                if (heroe != null && bestia != null && heroe.estaVivo() && bestia.estaVivo()) {
                    heroe.atacar(bestia);

                    if (bestia.estaVivo()) {
                        bestia.atacar(heroe);
                    }
                }
                    // verifica que ambos personajes están vivos, se establece en "true" para indicar que se está luchando
                boolean lucha = false;
                    // Verificar si ambos personajes están vivos y mostrar información de la lucha
                if (heroe != null && bestia != null && heroe.estaVivo() && bestia.estaVivo()) {
                    System.out.println("Lucha entre " + heroe.getNombre() + " (Vida: " + heroe.getPuntosVida() + ", Armadura: " + heroe.getResistenciaArmadura() + ")" +
                            " y " + bestia.getNombre() + " (Vida: " + bestia.getPuntosVida() + ", Armadura: " + bestia.getResistenciaArmadura() + ")");
                    System.out.println("Resultado de los dados: " + heroe.getNombre() + " saca " + heroe.getDados() + " y le quita " + heroe.getDaño() + " puntos de vida" +
                            ", " + bestia.getNombre() + " saca " + bestia.getDados() + " y le quita " + bestia.getDaño() + " puntos de vida");
                    System.out.println("¡El enfrentamiento entre " + heroe.getNombre() + " y " + bestia.getNombre() + " continúa!");
                    lucha = true;
                }

                boolean muerto = false;
                    // Verificar si el héroe derrotó a la bestia
                if (heroe != null && heroe.estaVivo() && !lucha && ejercitoBestias.seleccionarPersonajeParaBatalla(posicion) != null) {
                    System.out.println("¡" + heroe.getNombre() + " ha derrotado a " + bestia.getNombre() + "!");
                    ejercitoBestias.getPersonajes().set(posicion, null);
                    muerto = true;
                }
                    // Verificar si la bestia derrotó al héroe
                if (bestia != null && bestia.estaVivo() && !lucha && ejercitoHeroes.seleccionarPersonajeParaBatalla(posicion) != null) {
                    System.out.println("¡" + bestia.getNombre() + " ha derrotado a " + heroe.getNombre() + "!");
                    ejercitoHeroes.getPersonajes().set(posicion, null);
                    muerto = true;
                }
                     // Verificar si ambos personajes fueron derrotados
                if (heroe != null && bestia != null && !lucha && !muerto) {
                    System.out.println("¡Ambos personajes han sido derrotados!");
                    ejercitoHeroes.getPersonajes().set(posicion, null);
                    ejercitoBestias.getPersonajes().set(posicion, null);
                }
            }
                // Verificar si alguno de los ejércitos está totalmente derrotado
            if (this.determinarTotalmenteDerrotado()) {
                seguirJugando = false;
            }

            turno += 1;
        }
            // Determinar el ganador del juego
        determinarGanador();
    }

    // Método para determinar el ganador del juego
    public void determinarGanador() {
        if (ejercitoHeroes.estaTotalmenteDerrotado() && !ejercitoBestias.estaTotalmenteDerrotado()) {
            System.out.println("¡Las bestias han ganado la batalla!");
        } else if (!ejercitoHeroes.estaTotalmenteDerrotado() && ejercitoBestias.estaTotalmenteDerrotado()) {
            System.out.println("¡Los héroes han ganado la batalla!");
        } else {
            System.out.println("El juego ha terminado en empate.");
        }
    }

    // Método para determinar si uno de los ejércitos está totalmente derrotado
   public boolean determinarTotalmenteDerrotado() {
    int sum = 0;
    boolean result = false;
    
    // Iterar a través de los personajes de los ejércitos
    for (int i = 0; i < ejercitoHeroes.getPersonajes().size(); i++) {
        // Verificar si el héroe es nulo y la bestia no lo es
        if (ejercitoHeroes.seleccionarPersonajeParaBatalla(i) == null && ejercitoBestias.seleccionarPersonajeParaBatalla(i) != null) {
            sum += 1;
        } 
        // Verificar si el héroe no es nulo y la bestia sí lo es
        else if (ejercitoHeroes.seleccionarPersonajeParaBatalla(i) != null && ejercitoBestias.seleccionarPersonajeParaBatalla(i) == null) {
            sum += 1;
        }
    }
    
    // Verificar si la suma es mayor o igual al tamaño del ejército de bestias
    if (sum >= ejercitoBestias.getPersonajes().size()) {
        result = true;
    }
    
    return result;
}
}

