package org.example.entity;

public class Heroe extends Personaje {


    // Constructor
    public Heroe(String nombre, int puntosVida, int resistenciaArmadura, String tipo) {
        super(nombre, puntosVida, resistenciaArmadura);
        this.tipo = tipo;
    }

    

     // Método para tirar los dados
    @Override
    public int tirarDados() {
        int dado1 = (int) (100 * Math.random());
        int dado2 = (int) (100 * Math.random());
        return Math.max(dado1, dado2);
    }
    
    // Método para calcular el daño causado al adversario
    @Override
    public int calcularDaño(Personaje adversario) {
        int nivelArmaduraAdversario = 0; // Valor por defecto para nivel de armadura del adversario
        
        if (adversario != null) {
            nivelArmaduraAdversario = adversario.getResistenciaArmadura();
            int potenciaOfensiva = calcularPotenciaOfensiva();
            
            if (adversario instanceof Bestia && potenciaOfensiva > nivelArmaduraAdversario) {
                potenciaOfensiva += 10; // Incrementar la potencia ofensiva contra Bestias
            }
            
            int daño = potenciaOfensiva - nivelArmaduraAdversario;
            return Math.max(0, daño); // El daño mínimo es 0
        }
        
        return 0;
    }
    
    // Método para calcular la potencia ofensiva
    @Override
    protected int calcularPotenciaOfensiva() {
        int potenciaOfensiva = (int) (Math.random() * 101); // Genera un número aleatorio entre 0 y 100
        return potenciaOfensiva;
    }
    
    // Método para atacar al adversario
    @Override
    public void atacar(Personaje adversario) {
        this.dados = tirarDados();
        this.potenciaOfensiva = this.dados;
        int nivelArmaduraAdversario = adversario.getResistenciaArmadura();
        
        if (tipo.equals("Elfo") && adversario.getTipo().equals("Orco")) {
            this.potenciaOfensiva = potenciaOfensiva + 10; // Incrementa la potencia ofensiva en 10 puntos de Elfos contra Orcos
        }
        
        if (tipo.equals("Hobbit") && adversario.getTipo().equals("Trasgos")) {
            this.potenciaOfensiva = potenciaOfensiva - 5; // Hobbit perderá 5 puntos de ofensiva contra los Trasgos
        }
        
        if (tipo.equals("Orco")) {
            this.nivelArmaduraAdversario = (int) (nivelArmaduraAdversario * 0.9); // Orco decrementa la armadura a sus rivales un 10%
        }
        
        super.atacar(adversario);
    }
}

