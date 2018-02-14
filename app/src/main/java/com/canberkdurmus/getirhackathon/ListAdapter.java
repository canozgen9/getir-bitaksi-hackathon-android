package com.canberkdurmus.getirhackathon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class ListAdapter extends ArrayAdapter<Record> {
    private Context context;
    private List<Record> records = new ArrayList<>();


    String[] months;

    public ListAdapter(@NonNull Context context, int resource, @NonNull List<Record> objects) {
        super(context, resource, objects);
        this.context = context;
        records = objects;

        months = new String[13];
        months[0] = null;
        months[1] = "January";
        months[2] = "February";
        months[3] = "March";
        months[4] = "April";
        months[5] = "May";
        months[6] = "June";
        months[7] = "July";
        months[8] = "August";
        months[9] = "September";
        months[10] = "October";
        months[11] = "November";
        months[12] = "December";
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        Record currentRecord = records.get(position);


        if (currentRecord.getId() != null) {
            TextView id = listItem.findViewById(R.id.id);
            if (id != null)
                id.setText(currentRecord.getId());
        }

        if (currentRecord.getKey() != null) {
            TextView key = listItem.findViewById(R.id.key);
            if (key != null)
                key.setText(currentRecord.getKey());
        }

        if (currentRecord.getValue() != null) {
            TextView value = listItem.findViewById(R.id.value);
            if (value != null)
                value.setText(currentRecord.getValue());
        }

        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        StringBuilder dateString = new StringBuilder();
        try {
            Date date = format.parse(currentRecord.getCreatedAt());
            dateString.append(date.getDay());
            dateString.append(" ");
            dateString.append(months[date.getMonth()]);
            dateString.append(" ");
            dateString.append(date.getYear() + 1900);
            dateString.append(" - ");
            dateString.append(date.getHours());
            dateString.append(":");
            dateString.append(date.getMinutes());
            dateString.append(":");
            dateString.append(date.getSeconds());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (currentRecord.getCreatedAt() != null) {
            TextView createdAt = listItem.findViewById(R.id.createdAt);
            if (createdAt != null)
                createdAt.setText(dateString.toString());
        }

        TextView totalCount = listItem.findViewById(R.id.totalCount);
        if (totalCount != null)
            totalCount.setText(currentRecord.getTotalCount() + "");

        return listItem;
    }
}
