package com.xlily6x.compiler.read;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xiaowenlong on 9/4/2018.
 */
class U4 {

    public static long read(InputStream inputStream) {
        byte[] bytes = new byte[4];
        try {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long num = 0;
        for (int i= 0; i < bytes.length; i++) {
            num <<= 8;
            num |= (bytes[i] & 0xff);
        }
        return num;
    }
}
