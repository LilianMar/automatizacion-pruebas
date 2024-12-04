package com.automatizacion.demo;

import org.testng.TestNG;

public class MainTestRunner {

    public static void main(String[] args) {
        // Crear una instancia de TestNG
        TestNG testng = new TestNG();

        // Agregar las clases de prueba a ejecutar
        testng.setTestClasses(new Class[] {
                tests.LoginTest.class,
                tests.PurchaseTest.class
        });

        // Ejecutar los tests
        testng.run();

        System.out.println("Pruebas completadas.");
    }
}
