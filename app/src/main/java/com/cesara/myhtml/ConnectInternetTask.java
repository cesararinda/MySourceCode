package com.cesara.myhtml;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ASUS X550V on 19/10/2017.
 */

public class ConnectInternetTask extends AsyncTask<String,Void,String> {

    Context ctx;

    public ConnectInternetTask(Context ct){
        ctx = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String s1= strings[0];
        InputStream in;
        try {
            URL myUrl = new URL(s1);
            HttpURLConnection myConn = (HttpURLConnection) myUrl.openConnection();
            myConn.setReadTimeout(10000);
            myConn.setConnectTimeout(20000);
            myConn.setRequestMethod("GET");
            myConn.connect();

            in = myConn.getInputStream();

            BufferedReader myBuf = new BufferedReader(new InputStreamReader(in));
            StringBuilder st = new StringBuilder();
            String line = "";

            while((line = myBuf.readLine()) !=null){
                st.append(line+"  \n");
            }

            myBuf.close();
            in.close();

            return st.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        MainActivity.myText.setText(s);
    }

}
