import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 * Created by danny on 9/2/2017.
 * This is a basic UDP message sent from a sender to a reciever
 */
public class TestSender {

    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        String userMessage = "";
        InetAddress address = InetAddress.getByName("228.5.6.7");
        MulticastSocket socket = new MulticastSocket(49152);
        socket.joinGroup(address);
        while(true) {
            if (userMessage.equals("bye")) {
                break;

            }
            System.out.println("Hi welcome to UDP messaging service, please enter the message to be sent, enter Bye to end messaging service.");
            userMessage = sc.next();

            DatagramPacket sendPacket = new DatagramPacket(userMessage.getBytes(), userMessage.length(), address, 49152);

            socket.send(sendPacket);
        }
        socket.leaveGroup(address);
        socket.close();
    }
}
