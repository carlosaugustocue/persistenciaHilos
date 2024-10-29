package com.supermercado.hilo;

import com.supermercado.modelo.Cliente;
import com.supermercado.persistencia.BaseDeDatos;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class Caja implements Runnable {
    private int idCaja;
    private AtomicInteger clientesAtendidos;
    private double totalVentas;
    private BaseDeDatos baseDeDatos;

    public Caja(int idCaja, BaseDeDatos baseDeDatos) {
        this.idCaja = idCaja;
        this.clientesAtendidos = new AtomicInteger(0);
        this.totalVentas = 0.0;
        this.baseDeDatos = baseDeDatos;
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (!Thread.currentThread().isInterrupted()) {
            Cliente cliente = new Cliente();
            atenderCliente(cliente);
            try {
                Thread.sleep(rand.nextInt(3000) + 1000); // Tiempo de procesamiento aleatorio entre 1 y 4 segundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private synchronized void atenderCliente(Cliente cliente) {
        double totalCliente = cliente.calcularTotal();
        totalVentas += totalCliente;
        clientesAtendidos.incrementAndGet();
        baseDeDatos.guardarTransaccion(idCaja, totalCliente);
        System.out.println("Caja " + idCaja + " atendió a un cliente. Total: $" + String.format("%.2f", totalCliente));
    }

    public int getClientesAtendidos() {
        return clientesAtendidos.get();
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public int getIdCaja() {
        return idCaja;
    }
}

