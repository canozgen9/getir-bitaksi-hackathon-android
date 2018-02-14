package com.canberkdurmus.getirhackathon.interfaces;

import com.canberkdurmus.getirhackathon.Record;

import java.util.ArrayList;

public interface RequestListener {
    void onSuccess(ArrayList<Record> records);
    void onError(String msg);
}