package org.dajoh16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Address {

    private boolean debug;

    public Address(boolean debug) {
        this.debug = debug;
    }

    public void printNewAddress(){
        System.out.println("New Address with id : " + createNewAddress());
    }

    public String createNewAddress() {
        String address = "";
        try {
            Process startProcess = Runtime.getRuntime().exec("bitcoin-cli -regtest getnewaddress");
            InputStream stdout = startProcess.getInputStream();
            InputStream stderr = startProcess.getErrorStream();

            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
            String line = null;
            while((line = stdoutReader.readLine()) != null){
                if(debug){
                    System.out.println("[STDOUT] " + line);
                }
                address = line;
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
            return address;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return address;
    }
}
