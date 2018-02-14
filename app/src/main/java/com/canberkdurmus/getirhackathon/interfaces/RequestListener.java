package com.canberkdurmus.getirhackathon.interfaces;

import com.canberkdurmus.getirhackathon.Record;

import java.util.ArrayList;

/**
 * Created by canberkdurmus on 13.02.2018.
 */

public interface RequestListener {
    void onSuccess(ArrayList<Record> records);
    void onError(String msg);
}