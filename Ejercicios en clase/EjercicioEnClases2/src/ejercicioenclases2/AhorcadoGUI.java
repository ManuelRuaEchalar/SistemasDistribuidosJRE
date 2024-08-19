/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioenclases2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class AhorcadoGUI extends JFrame {
    private JTextField inputField;
    private JTextArea gameArea;
    private JButton submitButton;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public AhorcadoGUI() {
        // Configuración de la ventana principal
        setTitle("Juego de Ahorcado");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana

        // Creación de componentes
        gameArea = new JTextArea();
        gameArea.setEditable(false);
        gameArea.setFont(new Font("Courier New", Font.BOLD, 18));
        gameArea.setBackground(new Color(30, 30, 30));
        gameArea.setForeground(Color.WHITE);
        gameArea.setLineWrap(true);
        gameArea.setWrapStyleWord(true);

        inputField = new JTextField(2);
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setHorizontalAlignment(JTextField.CENTER);

        submitButton = new JButton("Enviar");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(0, 123, 255));
        submitButton.setForeground(Color.WHITE);

        // Estilo del panel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(50, 50, 50));
        inputPanel.add(new JLabel("Ingresa una letra: "));
        inputPanel.add(inputField);
        inputPanel.add(submitButton);

        // Layout principal
        setLayout(new BorderLayout());
        add(new JScrollPane(gameArea), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Acción al presionar el botón "Enviar"
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarLetra();
            }
        });

        // Conexión al servidor
        try {
            Socket socket = new Socket("localhost", 5056);
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());

            // Inicio del hilo para escuchar mensajes del servidor
            new Thread(new Listener()).start();

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void enviarLetra() {
        try {
            String letra = inputField.getText().toLowerCase();
            if (letra.length() == 1 && Character.isLetter(letra.charAt(0))) {
                salida.writeUTF(letra);
                inputField.setText(""); // Limpiar el campo de texto
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa una letra válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class Listener implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    String mensajeServidor = entrada.readUTF();
                    gameArea.append(mensajeServidor + "\n");
                    gameArea.setCaretPosition(gameArea.getDocument().getLength()); // Desplazarse automáticamente hacia abajo
                    if (mensajeServidor.startsWith("Juego terminado") || mensajeServidor.startsWith("¡Felicidades!")) {
                        inputField.setEnabled(false);
                        submitButton.setEnabled(false);
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AhorcadoGUI().setVisible(true);
            }
        });
    }
}