package com.example.catalog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class AddPositionDialog extends DialogFragment {

    private EditText mPositionNameEditText;
    private EditText mPositionQuantityEditText;

    private OnPositionAddedListener mListener;

    public interface OnPositionAddedListener {
        void onPositionAdded(Position position);
    }

    public static AddPositionDialog newInstance() {
        return new AddPositionDialog();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (OnPositionAddedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement OnPositionAddedListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.alert_dialog, null);

        mPositionNameEditText = view.findViewById(R.id.position_name);
        mPositionQuantityEditText = view.findViewById(R.id.position_quantity);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Добавить позицию")
                .setView(view)
                .setPositiveButton("Добавить", (dialogInterface, i) -> {
                    String name = mPositionNameEditText.getText().toString();
                    int quantity = 0;
                    try {
                        quantity = Integer.parseInt(mPositionQuantityEditText.getText().toString());
                    }catch (NumberFormatException e){}
                    Position position = new Position(name, quantity);
                    mListener.onPositionAdded(position);
                })
                .setNegativeButton("Отмена", null);

        return builder.create();
    }
}

