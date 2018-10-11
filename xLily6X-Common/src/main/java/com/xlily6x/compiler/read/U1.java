package com.xlily6x.compiler.read;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xiaowenlong on 9/4/2018.
 */
class U1 {
    public static short read(InputStream inputStream) {
        byte[] bytes = new byte[1];
        try {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        short value = (short) (bytes[0] & 0xFF);
        return value;
    }
}
