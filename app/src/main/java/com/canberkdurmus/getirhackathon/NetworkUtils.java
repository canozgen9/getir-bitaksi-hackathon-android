package com.canberkdurmus.getirhackathon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class NetworkUtils {

    public static String postRequest(String startDate, String endDate, int minCount, int maxCount) {
        HttpURLConnection connection;
        try {

            URL loginUrl = new URL("https://getir-bitaksi-hackathon.herokuapp.com/searchRecords");
            connection = (HttpURLConnection) loginUrl.openConnection();

            connection.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write("startDate=" + startDate);
            out.write("&endDate=" + endDate);
            out.write("&minCount=" + minCount);
            out.write("&maxCount=" + maxCount);
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response;
            String all = "";
            while ((response = in.readLine()) != null) {
                System.out.println("RESP:" + response);
                all += response;
            }
            in.close();
            return all;

        } catch (MalformedURLException error) {
            return "Error";
        } catch (SocketTimeoutException error) {
            return "Error";
        } catch (IOException error) {
            return "Error";
        }
    }
}
