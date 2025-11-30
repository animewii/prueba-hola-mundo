package com.ejemplo;

import org.junit.Test;
import static org.junit.Assert.*;

public class HolaMundoTest {
    
    @Test
    public void testObtenerMensaje() {
        HolaMundo app = new HolaMundo();
        String mensaje = app.obtenerMensaje();
        
        assertNotNull("El mensaje no debe ser nulo", mensaje);
        assertTrue("El mensaje debe contener 'Hola Mundo'", 
                   mensaje.contains("Hola Mundo"));
    }
    
    @Test
    public void testMensajeNoVacio() {
        HolaMundo app = new HolaMundo();
        String mensaje = app.obtenerMensaje();
        
        assertFalse("El mensaje no debe estar vacío", mensaje.isEmpty());
    }
}