package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Serverr {

    private DatagramSocket socket;
    private boolean running;
    InetAddress ad=null;
    public Serverr() {
        try {
            socket = new DatagramSocket(1000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        running = true;

        while (running) {
            try {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                if (ad != packet.getAddress()) {
                    InetAddress address = packet.getAddress();
                    ad = address;
                    int port = packet.getPort();

                    packet = new DatagramPacket(buf, buf.length, address, port);
                    String received = new String(packet.getData(), packet.getOffset(), packet.getLength()).trim();

                    System.out.println(received);
                    String msg = "Mein name ist Manni";
                    byte[] bu = msg.getBytes();
                    DatagramPacket packe = new DatagramPacket(bu, bu.length, address, 1000);

                    socket.send(packe);
                }
              /*  if (received.equals("end")) {
                    running = false;
                    continue;
                }
*/
                } catch(IOException e){
                    e.printStackTrace();
                }

            }
            System.out.println("Server stops...");
            socket.close();

    }
}
