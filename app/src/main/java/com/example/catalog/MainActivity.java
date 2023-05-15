package com.example.catalog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.catalog.adapter.PositionAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AddPositionDialog.OnPositionAddedListener {
    private final ArrayList<Position> positionList = new ArrayList<>();
    private PositionAdapter positionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // добавляем некоторые позиции в список

        demo();//Удалить если нужен пустой список в начале

        // находим ListView и создаем адаптер
        ListView positionListView = findViewById(R.id.position_list_view);
        positionAdapter = new PositionAdapter(this, positionList);
        positionListView.setAdapter(positionAdapter);

        View addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(view -> showAddPositionDialog());
    }

    private void showAddPositionDialog() {
        AddPositionDialog dialog = AddPositionDialog.newInstance();
        dialog.show(getSupportFragmentManager(), "AddPositionDialog");
    }

    private void demo() {
        positionList.add(new Position("Торт", 0));
        positionList.add(new Position("Пирог", 0));
        positionList.add(new Position("Кекс", 0));
    }

    // метод для обновления списка позиций
//    public void updatePositionList(ArrayList<Position> updatedList) {
//        positionList = updatedList;
//        positionAdapter.notifyDataSetChanged();
//    }

    @Override
    public void onPositionAdded(Position position) {
        positionList.add(position);
        positionAdapter.notifyDataSetChanged();
    }
}