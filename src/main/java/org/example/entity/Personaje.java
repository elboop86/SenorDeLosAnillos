package org.example.entity;

public abstract class Personaje {
    private String nombre;

    private int puntosVida;
    private int resistenciaArmadura;
    protected Personaje adversario;
    protected int potenciaOfensiva;
    protected int nivelArmaduraAdversario;
    protected int dados;
    private int daño;
    protected boolean derrotado = false;

    protected String tipo;



    // Constructor
    public Personaje(String nombre, int puntosVida, int resistenciaArmadura) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.resistenciaArmadura = resistenciaArmadura;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Personaje getAdversario() {
        return adversario;
    }

    public void setAdversario(Personaje adversario) {
        this.adversario = adversario;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public int getResistenciaArmadura() {
        return resistenciaArmadura;
    }

    public void setResistenciaArmadura(int resistenciaArmadura) {
        this.resistenciaArmadura = resistenciaArmadura;
    }

    public int getDados() {
        return this.dados;
    }

    public void setDados(int dados) {
        this.dados = dados;
    }

    public int getDaño() {
        return this.daño;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }


    // Método para recibir daño
    public void recibirDaño(int daño) {
       
        if (daño > 0) {
            this.puntosVida -= daño;
            if (this.puntosVida < 0) {
                this.puntosVida = 0;
            }
        }
    }
     /*El método "recibirDaño" reduce los puntos de vida del personaje según el daño recibido.
      Si el daño es mayor a 0, se resta el daño a los puntos de vida. Si los puntos de vida resultantes 
      son menores a 0, se establecen en 0.*/ 

    // Método para curar puntos de vida
    public void curar(int puntosACurar) {
        puntosVida += puntosACurar;
        if (this.puntosVida > 100) {
            this.puntosVida = 100;
        }
    }
    /** El método "curar" aumenta los puntos de vida del personaje según la cantidad de puntos a curar.
     *  Se suma la cantidad de puntos a curar a los puntos de vida actuales. Si los puntos de vida resultantes son mayores a 100,
     *  se establecen en 100.El método "recibirDaño" reduce los puntos de vida del personaje según el daño recibido.*/ 
     

    // Método para verificar si el personaje está vivo
    public boolean estaVivo() {
        return puntosVida > 0;
    }
        // Método abstracto para calcular el daño causado al adversario
    public abstract int calcularDaño(Personaje adversario);

        // Método abstracto para calcular la potencia ofensiva del personaje
    protected abstract int calcularPotenciaOfensiva();

    
   // Método para realizar un ataque al personaje adversario
public void atacar(Personaje adversario) {
    int nivelArmaduraAdversario = adversario.getResistenciaArmadura();
    
    // Verificar si la potencia ofensiva es mayor a la resistencia de armadura del adversario
    if (this.potenciaOfensiva > nivelArmaduraAdversario) {
        int daño = potenciaOfensiva - nivelArmaduraAdversario;
        this.daño = daño;
        adversario.recibirDaño(daño); // Llamar al método recibirDaño() del adversario para reducir sus puntos de vida
    } else {
        this.daño = 0; // Si la potencia ofensiva es menor o igual a la resistencia de armadura, el daño es 0
    }
}
        // Método abstracto para simular el lanzamiento de dados 

     abstract public int tirarDados();

}

