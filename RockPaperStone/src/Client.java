package PaperScissorsStone;

import java.io.*;
import java.net.*;

class Client {

    /**
     * The host
     *
     * @var string
     *
     */
    private static String host = "localhost";

    /**
     * The port
     *
     * @var integer
     */
    private static Integer port = 1337;

    /**
     * The version of the client class
     *
     * @var double
     */
    private static Double versionNumber = 1.0;

    /**
     * A short welcome msg
     *
     * @var string
     */
    private static String msgWelcome = "--- Welcome to Rock Paper Scissors Version "
            + versionNumber + " --- \n";

    /**
     * The help context
     *
     * @var string
     *
     */
    private static String msgRules = "\nRule set:\n - (1)Rock beats (3)Scissors\n - (3)Scissors beats (2)Paper\n - (2)Paper beats (1)Rock\n";

    public static void main(String args[]) throws Exception {

        String input = "";
        String response;

        System.out.println(Client.msgWelcome);

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
                System.in));
        Socket clientSocket = new Socket(Client.host, Client.port);
        DataOutputStream outToServer = new DataOutputStream(
                clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
                clientSocket.getInputStream()));

        do {

            if (input.equals("-rules")) {
                System.out.println(Client.msgRules);
            }

            // Prompt user for select rock, paper or scissors ...
            System.out
                    .println("Start the game by selecting (1)Rock (2)Paper, (3)Scissors");
            System.out.print("or type \"-rules\" in order to see the rules: ");
            input = inFromUser.readLine();

        } while (!input.equals("1") && !input.equals("2") && !input.equals("3"));

        // Transmit input to the server and provide some feedback for the user
        outToServer.writeBytes(input + "\n");
        System.out
                .println("\nYour input ("
                        + input
                        + ") was successfully transmitted to the server. Now just be patient and wait for the result ...");

        // Catch respones
        response = inFromServer.readLine();

        // Display respones
        System.out.println("Response from server: " + response);

        // Close socket
        clientSocket.close();

    }
}