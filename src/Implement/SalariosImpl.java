package Implement;

import Interface.SalariosInterface;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Random;

public class SalariosImpl extends UnicastRemoteObject implements SalariosInterface {

    public SalariosImpl() throws RemoteException {
        super();
    }

    @Override
    public double[][] generarMatriz(int empleados, int meses) throws RemoteException {
        double[][] matriz = new double[empleados][meses];
        Random rand = new Random();

        for (int i = 0; i < empleados; i++) {
            for (int j = 0; j < meses; j++) {
                matriz[i][j] = 800 + rand.nextDouble() * 2200; // salarios entre 800 y 3000
            }
        }
        return matriz;
    }

    @Override
    public double[] totalPorEmpleado(double[][] matriz) throws RemoteException {
        double[] totales = new double[matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                totales[i] += matriz[i][j];
            }
        }
        return totales;
    }

    @Override
    public double[] promedioPorMes(double[][] matriz) throws RemoteException {
        int empleados = matriz.length;
        int meses = matriz[0].length;
        double[] promedios = new double[meses];

        for (int j = 0; j < meses; j++) {
            for (int i = 0; i < empleados; i++) {
                promedios[j] += matriz[i][j];
            }
            promedios[j] /= empleados;
        }
        return promedios;
    }

    @Override
    public double totalGeneral(double[][] matriz) throws RemoteException {
        double total = 0;

        for (double[] fila : matriz) {
            for (double valor : fila) {
                total += valor;
            }
        }
        return total;
    }
} // implements
