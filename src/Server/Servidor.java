package Server;

import Interface.SalariosInterface;
import Implement.SalariosImpl;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            Registry registry;

            try {
                
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("Registry creado en puerto 1099");
            } catch (Exception e) {
                
                registry = LocateRegistry.getRegistry(1099);
                System.out.println("Registry activo");
            }

            SalariosInterface obj = new SalariosImpl();
            registry.rebind("Salarios", obj);

            System.out.println("Servidor RMI arriba");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}