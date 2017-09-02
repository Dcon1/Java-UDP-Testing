import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/*
* Created by danny on 9/2/2017.
* This is a basic UDP message sent from a sender to a reciever
* broken parts to fix in reader - does not close with bye
*/
public class TestMulticastReader {

    public static void main(String[] args) throws Exception {

        InetAddress address = InetAddress.getByName("228.5.6.7");
        MulticastSocket socket = new MulticastSocket(49152);
        socket.joinGroup(address);
        while (true) {
            byte[] messageBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(messageBuffer, messageBuffer.length);
            //Blocking statement (will be open and wait for recieved packet.
            socket.receive(receivePacket);
            String userMessage = new String(messageBuffer);

            if (userMessage.equals("bye")) {
                break;
            }
            System.out.println(userMessage);
        }
        System.out.println("Thank you for using UDP messaging Service");
        socket.leaveGroup(address);
        socket.close();
    }
}