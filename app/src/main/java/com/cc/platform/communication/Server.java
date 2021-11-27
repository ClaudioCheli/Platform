package com.cc.platform.communication;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Claudio on 06/09/2017.
 */

public class Server implements Runnable {

    private ServerSocket serverSocket;
    private Socket clientSocket = null;

    private Communication communicationThread;

    private int serverPort;
    private String ip;

    private boolean connected = false;

    public Server() {
        serverPort = 6000;
        ip = getIPAddress(true);
        Log.i("connection", "ip: " + ip);
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch(IOException e) {
            e.printStackTrace();
        }

        while(!Thread.currentThread().isInterrupted()) {
            try {
                clientSocket = serverSocket.accept();
                communicationThread = new Communication(clientSocket);
                new Thread(communicationThread).start();
                connected = true;
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for(NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for(InetAddress addr : addrs) {
                    if(!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if(useIPv4) {
                            if(isIPv4)
                                return sAddr;
                        } else {
                            if(!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch(Exception ex) {
        } // for now eat exceptions
        return "";
    }

    public boolean isConnected() {
        return connected;
    }

    private class Communication implements Runnable {

        private Socket clientSocket;
        private DataInputStream input;
        private DataOutputStream output;


        private byte[] oldData = new byte[0];

        private boolean send = false;

        public Communication(Socket clientSocket) {
            Log.i("communication", "Communication: " + Thread.currentThread().getName());
            this.clientSocket = clientSocket;
            try {
                input = new DataInputStream(clientSocket.getInputStream());
                output = new DataOutputStream(clientSocket.getOutputStream());
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    if(!Arrays.equals(oldData, Data.getData())) {
                        output.write(Data.getData(), 0, Data.getDataDimension());
                        oldData = Data.getData();
                    }
                    Thread.sleep(50);
                } catch(IOException e) {
                    e.printStackTrace();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        public synchronized boolean isSend() {
            return send;
        }

        public synchronized void setSend(boolean send) {
            this.send = send;
        }

    }
}
