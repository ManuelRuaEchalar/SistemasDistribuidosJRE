/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
// Interfaces para los componentes
interface Motor {
    void encender();
}

interface SistemaDeArmas {
    void disparar();
}

interface SistemaDeNavegacion {
    void navegar();
}

// Clases concretas para la nave de exploración
class MotorIonico implements Motor {
    public void encender() {
        System.out.println("Motor iónico encendido");
    }
}

class SensoresToponimicos implements SistemaDeArmas {
    public void disparar() {
        System.out.println("Escaneando superficie planetaria");
    }
}

class NavegadorEstelar implements SistemaDeNavegacion {
    public void navegar() {
        System.out.println("Trazando ruta entre estrellas");
    }
}

// Clases concretas para la nave de combate
class MotorWarp implements Motor {
    public void encender() {
        System.out.println("Motor warp activado");
    }
}

class LaserPlasmico implements SistemaDeArmas {
    public void disparar() {
        System.out.println("Disparando láser plásmico");
    }
}

class NavegadorTactico implements SistemaDeNavegacion {
    public void navegar() {
        System.out.println("Calculando maniobras evasivas");
    }
}

// Abstract Factory
interface FabricaDeNaves {
    Motor crearMotor();
    SistemaDeArmas crearSistemaDeArmas();
    SistemaDeNavegacion crearSistemaDeNavegacion();
}

// Fábricas concretas
class FabricaDeNavesDeExploracion implements FabricaDeNaves {
    public Motor crearMotor() {
        return new MotorIonico();
    }
    public SistemaDeArmas crearSistemaDeArmas() {
        return new SensoresToponimicos();
    }
    public SistemaDeNavegacion crearSistemaDeNavegacion() {
        return new NavegadorEstelar();
    }
}

class FabricaDeNavesDeCombate implements FabricaDeNaves {
    public Motor crearMotor() {
        return new MotorWarp();
    }
    public SistemaDeArmas crearSistemaDeArmas() {
        return new LaserPlasmico();
    }
    public SistemaDeNavegacion crearSistemaDeNavegacion() {
        return new NavegadorTactico();
    }
}

// Clase que usa la fábrica
class CreadorDeNaves {
    private FabricaDeNaves fabrica;

    public CreadorDeNaves(FabricaDeNaves fabrica) {
        this.fabrica = fabrica;
    }

    public void crearNave() {
        Motor motor = fabrica.crearMotor();
        SistemaDeArmas armas = fabrica.crearSistemaDeArmas();
        SistemaDeNavegacion navegacion = fabrica.crearSistemaDeNavegacion();

        System.out.println("Nave creada con los siguientes componentes:");
        motor.encender();
        armas.disparar();
        navegacion.navegar();
    }
}

// Ejemplo de uso
public class Main {
    public static void main(String[] args) {
        CreadorDeNaves creadorExploracion = new CreadorDeNaves(new FabricaDeNavesDeExploracion());
        CreadorDeNaves creadorCombate = new CreadorDeNaves(new FabricaDeNavesDeCombate());

        System.out.println("Creando nave de exploración:");
        creadorExploracion.crearNave();

        System.out.println("\nCreando nave de combate:");
        creadorCombate.crearNave();
    }
}
