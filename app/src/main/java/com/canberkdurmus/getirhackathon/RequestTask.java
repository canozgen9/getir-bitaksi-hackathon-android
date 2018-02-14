package com.canberkdurmus.getirhackathon;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;

import com.canberkdurmus.getirhackathon.interfaces.RequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by canberkdurmus on 13.02.2018.
 */

public class RequestTask extends AsyncTask<String, Void, String> {

    RequestListener listener;

    private ProgressDialog dialog;


    public RequestTask(Activity activity, RequestListener listener) {
        this.listener = listener;
        dialog = new ProgressDialog(activity);

    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Fetching data...");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... parameters) {
        if (parameters.length != 4) {
            listener.onError("Parameters are not correct.");
            return null;
        }
        String startDate = parameters[0];
        String endDate = parameters[1];
        int minCount = Integer.parseInt(parameters[2]);
        int maxCount = Integer.parseInt(parameters[3]);

        for(String s: parameters){
            System.out.println("XXX: " + s);
        }

        if (startDate != null && endDate != null && minCount != -1 && maxCount != -1) {
            return NetworkUtils.postRequest(startDate, endDate, minCount, maxCount);
        }
        listener.onError("Response is null.");
        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        if (response == null) {
            return;
        }

        JSONObject allResponseJSON;
        JSONArray recordArrayJSON;
        ArrayList<Record> recordList = new ArrayList<>();

        try {
            allResponseJSON = new JSONObject(response);
            recordArrayJSON = allResponseJSON.getJSONArray("records");
            for (int i = 0; i < recordArrayJSON.length() - 1; i++) {
                recordList.add(new Record(recordArrayJSON.getJSONObject(i)));
            }
            listener.onSuccess(recordList);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError("JSON object parse error.");
            dialog.setMessage("Something went wrong");
            new Handler().postDelayed(() -> dialog.dismiss(), 3000);
        }


    }
}