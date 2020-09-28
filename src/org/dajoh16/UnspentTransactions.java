package org.dajoh16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UnspentTransactions {
    private boolean debug;
    public UnspentTransactions(boolean debug) {
        this.debug = debug;
    }

    public void printUnspent() {
        System.out.println("Unspent Transactions: ");
        unspent();
    }

    private void unspent() {
        try {
            Process startProcess = Runtime.getRuntime().exec("bitcoin-cli -regtest listunspent 0");
            InputStream stdout = startProcess.getInputStream();
            InputStream stderr = startProcess.getErrorStream();

            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
            String line = null;
            while((line = stdoutReader.readLine()) != null){
                if(debug){
                    System.out.println("[STDOUT] " + line);
                }
                System.out.println(line);
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
