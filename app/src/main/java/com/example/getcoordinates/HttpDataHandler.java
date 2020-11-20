package com.example.getcoordinates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpDataHandler
{
        public HttpDataHandler()
        {

        }

        public String getHTTPData(String requestURL)
        {
            URL url;
            String response = "";
            try
            {
                url = new URL(requestURL);
                HttpURLConnection conex = (HttpURLConnection)url.openConnection();
                conex.setRequestMethod("GET");
                conex.setReadTimeout(15000);
                conex.setConnectTimeout(15000);
                conex.setDoInput(true);
                conex.setDoOutput(true);
                conex.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                int responseCode =  conex.getResponseCode();
                if(responseCode == HttpsURLConnection.HTTP_OK)
                {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conex.getInputStream()));
                    while((line = br.readLine()) != null) {
                        response += line;
                    }
                }
                else
                    response = "";
            }catch (ProtocolException e)
            {
                e.printStackTrace();
            }catch (MalformedURLException e)
            {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }
}
