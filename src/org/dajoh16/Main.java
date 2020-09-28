package org.dajoh16;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BitcoinDaemon bitcoinDaemon = startBitcoinDaemon(true);
        boolean cont = true;
        while(cont) {
           cont = programLoop();
        }
        System.out.println("Exiting Application...");
        shutdownBitcoinDaemon(bitcoinDaemon);
    }


    private static boolean programLoop() {
        System.out.println("What would you like to do?");
        System.out.println("1 : Get current balance");
        System.out.println("2 : Create a new Address");
        System.out.println("3 : Send Bitcoins to an Address");
        System.out.println("4 : List unspend Transactions");
        System.out.println("5 : Exit the Application");
        Scanner input = new Scanner(System.in);
        String inputString = input.nextLine();
        if(inputString.equalsIgnoreCase("1")){
            getBalance();
        } else if (inputString.equalsIgnoreCase("2")){
            createAddress();
        } else if (inputString.equalsIgnoreCase("3")){
            System.out.println("Which Address to send to?: ");
            String addressToSendTo = input.nextLine();
            System.out.println("How many bitcoins to send?: ");
            String bitcoinAmount = input.nextLine();
            sendBitcoins(addressToSendTo,bitcoinAmount);
        } else if(inputString.equalsIgnoreCase("4")){
            listUnspendTransactions();
        } else if(inputString.equalsIgnoreCase("5")){
            return false;
        } else {
            System.out.println("Invalid input. Input the corresponding number for each action to execute it");
        }
        return true;
    }

    private static void listUnspendTransactions() {
        UnspentTransactions unspentTransactions = new UnspentTransactions(false);
        unspentTransactions.printUnspent();
    }

    private static void sendBitcoins(String addressToSendTo, String bitcoinAmount) {
        Transaction transaction = new Transaction(addressToSendTo,bitcoinAmount, true);
        transaction.processTransaction();
    }

    private static void createAddress() {
        Address address = new Address(true);
        address.printNewAddress();
    }

    private static void getBalance() {
        Balance balance = new Balance(true);
        balance.printBalance();
    }

    private static BitcoinDaemon startBitcoinDaemon(boolean debug) {
        BitcoinDaemon bitcoinDaemon = new BitcoinDaemon(debug);
        bitcoinDaemon.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bitcoinDaemon.populate();
        return bitcoinDaemon;
    }


    private static void shutdownBitcoinDaemon(BitcoinDaemon daemon) {
        daemon.stop();
    }
}
