package org.example.entity;

public class Bestia extends Personaje {
    public Bestia(String nombre, int puntosVida, int resistenciaArmadura, String tipo) {
        super(nombre, puntosVida, resistenciaArmadura);
        this.tipo = tipo;
    }
    
    // Método para tirar los dados
    @Override
    public int tirarDados() {
        int dado1 = (int) ((100 * Math.random()) % 90);
        return dado1;
    }
    
    // Método para calcular el daño causado al adversario
    @Override
    public int calcularDaño(Personaje adversario) {
        int potenciaOfensiva = calcularPotenciaOfensiva();
        int nivelArmaduraAdversario = adversario.getResistenciaArmadura();
        
        if (adversario instanceof Heroe && potenciaOfensiva > nivelArmaduraAdversario) {
            potenciaOfensiva -= 5; // Reducir la potencia ofensiva contra Héroes
        }
        
        int daño = potenciaOfensiva - nivelArmaduraAdversario;
        return Math.max(0, daño); // El daño mínimo es 0
    }
    
    // Método para calcular la potencia ofensiva
    @Override
    protected int calcularPotenciaOfensiva() {
        int potenciaOfensiva = (int) (Math.random() * 91); // Genera un número aleatorio entre 0 y 90
        return potenciaOfensiva;
    }
    
    // Método para atacar al adversario
    @Override
    public void atacar(Personaje adversario) {
        this.dados = tirarDados();
        this.potenciaOfensiva = this.dados;
        int nivelArmaduraAdversario = adversario.getResistenciaArmadura();
        
        if (adversario instanceof Heroe && potenciaOfensiva > nivelArmaduraAdversario) {
            this.potenciaOfensiva = potenciaOfensiva - 5; // Reducir la potencia ofensiva contra Héroes
        }
        
        super.atacar(adversario);
    }
}

