package Client;

import Implement.SalariosImpl;
import Interface.SalariosInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            SalariosInterface obj = (SalariosInterface) registry.lookup("Salarios");

            Scanner sc = new Scanner(System.in);

            System.out.print("Número de empleados: ");
            int empleados = sc.nextInt();

            System.out.print("Número de meses: ");
            int meses = sc.nextInt();

            double[][] matriz = obj.generarMatriz(empleados, meses);

            double[] totales = obj.totalPorEmpleado(matriz);
            double[] promedios = obj.promedioPorMes(matriz);
            double totalGeneral = obj.totalGeneral(matriz);

            System.out.println("\nTotal por empleado:");
            for (int i = 0; i < totales.length; i++) {
                System.out.println("Empleado " + (i + 1) + ": " + totales[i]);
            }

            System.out.println("\nPromedio por mes:");
            for (int j = 0; j < promedios.length; j++) {
                System.out.println("Mes " + (j + 1) + ": " + promedios[j]);
            }

            System.out.println("\nTotal general pagado: " + totalGeneral);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
