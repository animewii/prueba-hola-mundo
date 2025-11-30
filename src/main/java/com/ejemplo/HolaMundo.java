package com.ejemplo;

public class HolaMundo {
    
    /**
     * Método que retorna el mensaje de Hola Mundo
     * @return String con el mensaje
     */
    public String obtenerMensaje() {
        return "¡Hola Mundo desde Maven y Java!";
    }
    
    /**
     * Método principal que se ejecuta al iniciar el programa
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        HolaMundo app = new HolaMundo();
        System.out.println(app.obtenerMensaje());
        System.out.println("Proyecto compilado exitosamente con Maven");
    }
}