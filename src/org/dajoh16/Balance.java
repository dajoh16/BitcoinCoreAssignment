package org.dajoh16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Balance {
    private boolean debug;

    public Balance(boolean debug) {
        this.debug = debug;
    }

    public void printBalance(){
        System.out.println("Current balance is : " + getBalance());
    }

    private float getBalance(){
        float balance = 0.0f;
        try {
            Process startProcess = Runtime.getRuntime().exec("bitcoin-cli -regtest getbalance");
            InputStream stdout = startProcess.getInputStream();
            InputStream stderr = startProcess.getErrorStream();

            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
            String line = null;
            while((line = stdoutReader.readLine()) != null){
                if(debug){
                    System.out.println("[STDOUT] " + line);
                }
                balance = Float.parseFloat(line);
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
            return balance;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return balance;
    }
}
