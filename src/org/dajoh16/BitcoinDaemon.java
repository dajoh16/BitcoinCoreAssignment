package org.dajoh16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BitcoinDaemon {
    private boolean debug;
    public BitcoinDaemon(boolean debug) {
        this.debug = debug;
    }

    public void start(){
        try {
            Process startProcess = Runtime.getRuntime().exec("bitcoind -regtest -daemon -fallbackfee=0.001");
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

    public void stop(){
        try {
            Process startProcess = Runtime.getRuntime().exec("bitcoin-cli -regtest stop");
            InputStream stdout = startProcess.getInputStream();
            InputStream stderr = startProcess.getErrorStream();

            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
            String line = null;
            while((line = stdoutReader.readLine()) != null){
                if(debug) {
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

    public void populate() {
        String address = new Address(false).createNewAddress();
        try {
            Process startProcess = Runtime.getRuntime().exec("bitcoin-cli -regtest generatetoaddress 101 " + address);
            InputStream stdout = startProcess.getInputStream();
            InputStream stderr = startProcess.getErrorStream();

            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
            String line = null;
            while((line = stdoutReader.readLine()) != null){
                if(debug) {
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
