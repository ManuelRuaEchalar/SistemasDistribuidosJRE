/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagosruatv3;


import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MunicipalityUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(6000)) {
            System.out.println("Municipality UDP Server is running.");
            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String receivedData = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received inquiry: " + receivedData);

                String id = receivedData.split(":")[1];
                String replyMessage;

                if (id.equals("1234567")) {
                    replyMessage = "response:false";
                } else {
                    replyMessage = "response:true";
                }
                byte[] replyBytes = replyMessage.getBytes();
                DatagramPacket replyPacket = new DatagramPacket(replyBytes, replyBytes.length, packet.getAddress(), packet.getPort());
                socket.send(replyPacket);
            }
        } catch (Exception e) {
            System.out.println("Error in Municipality UDP Server: " + e.getMessage());
        }
    }
}

