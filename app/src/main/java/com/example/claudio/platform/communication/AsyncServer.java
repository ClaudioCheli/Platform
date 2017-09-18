package com.example.claudio.platform.communication;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

/**
 * Created by Claudio on 06/09/2017.
 */

public class AsyncServer extends IntentService {

    private ServerSocket serverSocket;
    private Socket clientSocket = null;

    private Communication communicationThread;

    private int serverPort;
    private String ip;

    private boolean connected = false;


    public AsyncServer() {
        super("");
        serverPort = 6000;
        ip = getIPAddress(true);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
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


    public void sendData(byte[] data) {
        communicationThread.setData(data);
        communicationThread.setSend(true);
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
        private BufferedReader input;
        private DataOutputStream output;

        private boolean send = false;

        private byte[] data;

        public Communication(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
                output = new DataOutputStream(clientSocket.getOutputStream());
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                if(isSend()) {
                    /*
                    xPos = MainActivity.getXPos();
                    yPos = MainActivity.getYPos();
                    speed = MainActivity.getSpeed();
                    jumpSpeed = MainActivity.getJumpSpeed();
                    byte[] buffer = ByteBuffer.allocate(16).putFloat(xPos).putFloat(yPos)
                            .putFloat(speed).putFloat(jumpSpeed).array();
                    */
                    try {
                        output.write(data, 0, data.length);
                        setSend(false);
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public synchronized boolean isSend() {
            return send;
        }

        public synchronized void setSend(boolean send) {
            this.send = send;
        }

        public synchronized void setData(byte[] data) {
            this.data = data;
        }

    }

}
