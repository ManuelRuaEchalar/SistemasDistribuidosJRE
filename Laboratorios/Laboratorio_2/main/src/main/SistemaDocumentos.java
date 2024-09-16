/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.Base64;
import java.util.zip.Deflater;

// Interfaz para el documento
interface Documento {
    String getContenido();
}

// Implementaciones concretas de Documento
class DocumentoClasico implements Documento {
    private String contenido;

    public DocumentoClasico(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String getContenido() {
        return contenido;
    }
}

class DocumentoEncriptado implements Documento {
    private String contenidoEncriptado;

    public DocumentoEncriptado(String contenido) {
        // Usamos Base64 como un método simple de "encriptación"
        this.contenidoEncriptado = Base64.getEncoder().encodeToString(contenido.getBytes());
    }

    @Override
    public String getContenido() {
        return contenidoEncriptado;
    }
}

class DocumentoComprimido implements Documento {
    private byte[] contenidoComprimido;

    public DocumentoComprimido(String contenido) {
        // Usamos Deflater para comprimir el contenido
        Deflater deflater = new Deflater();
        deflater.setInput(contenido.getBytes());
        deflater.finish();
        byte[] buffer = new byte[1024];
        int compressedLength = deflater.deflate(buffer);
        this.contenidoComprimido = new byte[compressedLength];
        System.arraycopy(buffer, 0, contenidoComprimido, 0, compressedLength);
    }

    @Override
    public String getContenido() {
        return Base64.getEncoder().encodeToString(contenidoComprimido);
    }
}

// Abstract Factory
interface FabricaDocumento {
    Documento crearDocumento(String contenido);
}

// Fábricas concretas
class FabricaDocumentoClasico implements FabricaDocumento {
    @Override
    public Documento crearDocumento(String contenido) {
        return new DocumentoClasico(contenido);
    }
}

class FabricaDocumentoEncriptado implements FabricaDocumento {
    @Override
    public Documento crearDocumento(String contenido) {
        return new DocumentoEncriptado(contenido);
    }
}

class FabricaDocumentoComprimido implements FabricaDocumento {
    @Override
    public Documento crearDocumento(String contenido) {
        return new DocumentoComprimido(contenido);
    }
}

// Clase principal para demostrar el uso
public class SistemaDocumentos {
    public static void main(String[] args) {
        String texto = "Un dia, el bufon de la corte se atrevio a darle una nalgada al rey Francisco I. Indignado, el rey lo amenazo con ejecutarlo. Sin embargo, el bufon, ingenioso como siempre, respondio que lo sentia, pues habia pensado que se trataba de la reina.";

        FabricaDocumento fabricaClasico = new FabricaDocumentoClasico();
        FabricaDocumento fabricaEncriptado = new FabricaDocumentoEncriptado();
        FabricaDocumento fabricaComprimido = new FabricaDocumentoComprimido();

        Documento docClasico = fabricaClasico.crearDocumento(texto);
        Documento docEncriptado = fabricaEncriptado.crearDocumento(texto);
        Documento docComprimido = fabricaComprimido.crearDocumento(texto);

        System.out.println("Documento Clásico:");
        System.out.println(docClasico.getContenido());
        System.out.println("\nDocumento Encriptado:");
        System.out.println(docEncriptado.getContenido());
        System.out.println("\nDocumento Comprimido:");
        System.out.println(docComprimido.getContenido());
    }
}