package Client;

import Implement.SalariosImpl;
import Interface.SalariosInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteGUI extends JFrame {

    private JTextField txtEmpleados;
    private JTextField txtMeses;
    private JTextArea areaResultado;
    private SalariosInterface obj;

    public ClienteGUI() {
        setTitle("Cálculo de Salarios RMI");
        setSize(420, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        conectarRMI();
        crearInterfaz();
    }

    private void conectarRMI() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            obj = (SalariosInterface) registry.lookup("Salarios");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error conectando al servidor RMI");
        }
    }

    private void crearInterfaz() {

        JPanel panelEntrada = new JPanel(new GridLayout(3, 2, 5, 5));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Datos"));

        panelEntrada.add(new JLabel("Número de empleados:"));
        txtEmpleados = new JTextField();
        panelEntrada.add(txtEmpleados);

        panelEntrada.add(new JLabel("Número de meses:"));
        txtMeses = new JTextField();
        panelEntrada.add(txtMeses);

        JButton btnCalcular = new JButton("Calcular");
        panelEntrada.add(new JLabel());
        panelEntrada.add(btnCalcular);

        add(panelEntrada, BorderLayout.NORTH);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        btnCalcular.addActionListener((ActionEvent e) -> calcular());
    }

    private void calcular() {
        try {
            int empleados = Integer.parseInt(txtEmpleados.getText());
            int meses = Integer.parseInt(txtMeses.getText());

            double[][] matriz = obj.generarMatriz(empleados, meses);
            double[] totales = obj.totalPorEmpleado(matriz);
            double[] promedios = obj.promedioPorMes(matriz);
            double totalGeneral = obj.totalGeneral(matriz);

            StringBuilder sb = new StringBuilder();

            sb.append("TOTAL POR EMPLEADO\n");
            for (int i = 0; i < totales.length; i++) {
                sb.append(String.format("Empleado %d: %.2f\n", i + 1, totales[i]));
            }

            sb.append("\nPROMEDIO POR MES\n");
            for (int j = 0; j < promedios.length; j++) {
                sb.append(String.format("Mes %d: %.2f\n", j + 1, promedios[j]));
            }

            sb.append("\nTOTAL GENERAL: ");
            sb.append(String.format("%.2f", totalGeneral));

            areaResultado.setText(sb.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos o error RMI");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteGUI().setVisible(true));
    }
}
