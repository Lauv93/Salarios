package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SalariosInterface extends Remote {

    double[][] generarMatriz(int empleados, int meses) throws RemoteException;

    double[] totalPorEmpleado(double[][] matriz) throws RemoteException;

    double[] promedioPorMes(double[][] matriz) throws RemoteException;

    double totalGeneral(double[][] matriz) throws RemoteException;
}
