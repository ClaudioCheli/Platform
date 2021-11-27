package com.cc.platform.communication;

/**
 * Created by Claudio on 12/09/2017.
 */

public class Data {

    private static byte[] data;

    private Data() {}

    public static void setDataDimension(int dimension) {
        data = new byte[dimension];
    }

    public static int getDataDimension() {
        return data.length;
    }

    public static synchronized void setData(byte[] newData){
        data = newData;
    }

    public static synchronized byte[] getData() {
        return data;
    }

}
