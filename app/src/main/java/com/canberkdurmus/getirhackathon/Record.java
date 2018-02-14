package com.canberkdurmus.getirhackathon;

import org.json.JSONException;
import org.json.JSONObject;



public class Record {
    String id;
    String key;
    String value;
    String createdAt;
    int totalCount;

    public Record(String id, String key, String value, String createdAt, int totalCount) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.createdAt = createdAt;
        this.totalCount = totalCount;
    }

    public Record(JSONObject j) {
        try {
            JSONObject tempID = j.getJSONObject("_id");
            this.totalCount = j.getInt("totalCount");
            this.id = tempID.getString("_id");
            this.key = tempID.getString("key");
            this.value = tempID.getString("value");
            this.createdAt = tempID.getString("createdAt");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
