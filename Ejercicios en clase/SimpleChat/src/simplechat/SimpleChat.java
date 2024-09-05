package simplechat;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;

import java.io.*;
import java.util.*;

public class SimpleChat extends ReceiverAdapter {
    JChannel channel;
    private String user_name;
    
    // Mapa para almacenar votos: <Candidato, Map<Mesa, Votos>>
    private final Map<String, Map<String, Integer>> votos = new HashMap<>();
    
    public SimpleChat(String user_name) {
        this.user_name = user_name;
    }

    final List<String> state = new LinkedList<>();

    public void viewAccepted(View new_view) {
        System.out.println("** vista: " + new_view);
    }

    public void receive(Message msg) {
        String line = (String) msg.getObject();
        System.out.println(msg.getSrc() + ": " + line);
        processVote(line);  // Procesamos el voto recibido
        synchronized (state) {
            state.add(line);
        }
    }

    public void getState(OutputStream output) throws Exception {
        synchronized (state) {
            Util.objectToStream(state, new DataOutputStream(output));
        }
    }

    @SuppressWarnings("unchecked")
    public void setState(InputStream input) throws Exception {
        List<String> list = (List<String>) Util.objectFromStream(new DataInputStream(input));
        synchronized (state) {
            state.clear();
            state.addAll(list);
        }
        System.out.println("estado recibido (" + list.size() + " mensajes en la historia del chat):");
        for (String str : list) {
            System.out.println(str);
        }
    }

    // Método que procesa el voto
    private void processVote(String vote) {
        // Dividimos el mensaje en candidato y mesa. Se asume que es del formato NombreCandidatoNumeroMesa.
        String candidate = vote.replaceAll("[0-9]", "");  // Eliminamos los números para obtener el nombre
        String mesa = vote.replaceAll("[^0-9]", "");      // Eliminamos las letras para obtener el número de mesa

        // Verificamos si el candidato ya tiene votos
        votos.putIfAbsent(candidate, new HashMap<>());
        Map<String, Integer> mesaVotos = votos.get(candidate);

        // Actualizamos los votos para esa mesa
        mesaVotos.put(mesa, mesaVotos.getOrDefault(mesa, 0) + 1);

        // Mostramos el recuento actual de votos
        System.out.println("Voto registrado para " + candidate + " en la mesa " + mesa);
        showResults();
    }

    // Método para mostrar los resultados actuales
    private void showResults() {
        System.out.println("Resultados actuales:");
        for (String candidate : votos.keySet()) {
            int totalVotos = votos.get(candidate).values().stream().mapToInt(Integer::intValue).sum();
            System.out.println(candidate + " tiene " + totalVotos + " votos.");
        }
    }

    private void start() throws Exception {
        channel = new JChannel();
        channel.setReceiver(this);
        channel.connect("ChatCluster");
        channel.getState(null, 10000);
        eventLoop();
        channel.close();
    }

    private void eventLoop() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("> ");
                System.out.flush();
                String line = in.readLine().toLowerCase();
                if (line.startsWith("quit") || line.startsWith("exit")) {
                    break;
                }
                line = "[" + user_name + "] " + line;
                Message msg = new Message(null, line);
                channel.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca su nombre: ");
        String nombre = sc.next();
        new SimpleChat(nombre).start();
    }
}

