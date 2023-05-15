package com.example.catalog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.catalog.Position;
import com.example.catalog.R;

import java.util.ArrayList;

public class PositionAdapter extends ArrayAdapter<Position> {

    private final ArrayList<Position> positionList;

    public PositionAdapter(Context context, ArrayList<Position> positionList) {
        super(context, R.layout.list_item, positionList);
        this.positionList = positionList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }

        Position positionItem = positionList.get(position);
        TextView positionNameTextView = view.findViewById(R.id.positionName);
        positionNameTextView.setText(positionItem.getName());

        TextView countTextView = view.findViewById(R.id.countTextView);
        countTextView.setText(String.valueOf(positionItem.getCount()));

        View plusButton = view.findViewById(R.id.plusButton);
        plusButton.setOnClickListener(v -> {
            int count = positionItem.getCount();
            count++;
            positionItem.setCount(count);
            countTextView.setText(String.valueOf(count));
        });

        View minusButton = view.findViewById(R.id.minusButton);
        minusButton.setOnClickListener(v -> {
            int count = positionItem.getCount();
            if (count > 0) {
                count--;
                positionItem.setCount(count);
                countTextView.setText(String.valueOf(count));
            }
        });
        View deleteButton = view.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> {
            positionList.remove(position);
            notifyDataSetChanged();
        });
        return view;
    }
}
