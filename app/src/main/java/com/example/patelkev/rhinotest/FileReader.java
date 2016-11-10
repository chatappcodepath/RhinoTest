package com.example.patelkev.rhinotest;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by patelkev on 11/10/16.
 */

public class FileReader {

    public static String contentsOfFile(InputStream is) throws IOException {

        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();

        while (line != null) {
            sb.append(line).append("\n");
            line = buf.readLine();
        }
        return sb.toString();
    }

    public static String contentsOfAssetFile(Context mContext, String filename) throws IOException {
        return contentsOfFile(mContext.getAssets().open(filename));
    }
}
