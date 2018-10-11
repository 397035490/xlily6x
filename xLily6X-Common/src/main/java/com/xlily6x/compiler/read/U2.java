package com.xlily6x.compiler.read;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xiaowenlong on 9/4/2018.
 */
class U2 {

    public static int read(InputStream inputStream) {
        byte[] bytes = new byte[2];
        try {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int num = 0;
        for (int i= 0; i < bytes.length; i++) {
            num <<= 8;
            num |= (bytes[i] & 0xff);
        }
        return num;
    }
}
