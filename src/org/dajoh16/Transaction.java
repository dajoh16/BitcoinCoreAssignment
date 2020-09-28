package org.dajoh16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Transaction {
    private String address;
    private String btcAmount;
    private boolean debug;

    public Transaction(String addressToSendTo, String bitcoinAmount, boolean debug) {
        this.address = addressToSendTo;
        this.btcAmount = bitcoinAmount;
        this.debug = debug;
    }

    public void processTransaction() {
        try {
            Process startProcess = Runtime.getRuntime().exec("bitcoin-cli -regtest sendtoaddress " + address + " " + btcAmount);
            InputStream stdout = startProcess.getInputStream();
            InputStream stderr = startProcess.getErrorStream();

            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
            String line = null;
            while((line = stdoutReader.readLine()) != null){
                if(debug){
                    System.out.println("[STDOUT] " + line);
                }

            }

            BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));
            String errLine = null;
            while((errLine = stderrReader.readLine()) != null){
                if(debug) {
                    System.out.println("[STDERR] " + errLine);
                }
            }

            int exitCode = startProcess.waitFor();
            if(debug) {
                System.out.println("Returned ExitCode: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        //createBlock();
    }

    private void createBlock() {
        try {
            Process startProcess = Runtime.getRuntime().exec("bitcoin-cli -regtest generatetoaddress 1 " + address);
            InputStream stdout = startProcess.getInputStream();
            InputStream stderr = startProcess.getErrorStream();

            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
            String line = null;
            while((line = stdoutReader.readLine()) != null){
                if(debug){
                    System.out.println("[STDOUT] " + line);
                }

            }

            BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));
            String errLine = null;
            while((errLine = stderrReader.readLine()) != null){
                if(debug) {
                    System.out.println("[STDERR] " + errLine);
                }
            }

            int exitCode = startProcess.waitFor();
            if(debug) {
                System.out.println("Returned ExitCode: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
