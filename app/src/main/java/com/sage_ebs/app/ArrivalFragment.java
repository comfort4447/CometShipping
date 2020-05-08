package com.sage_ebs.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public  class ArrivalFragment extends Fragment implements View.OnClickListener {


    private EditText  editText2;
    private TextView textview2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.arrivalfragment, container, false);
        Button button = view.findViewById(R.id.check);
        editText2 = view.findViewById(R.id.ed_id);
        textview2 = view.findViewById(R.id.scan);
        textview2.setOnClickListener(this);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        String vessel_id = editText2.getText().toString();
        if (vessel_id.isEmpty()) {
            editText2.setError("");
            editText2.requestFocus();
        } else if (view.getId() == R.id.check) {
            if (getActivity() != null)
                Toast.makeText(getActivity(), "Hold on, checking through", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "Enter valid vessel Id and vessel name", Toast.LENGTH_SHORT).show();
        }
        switch (view.getId()){
            case R.id.scan:
                Intent intent = new Intent(getActivity(), ScanActivity.class);
                startActivity(intent);
                break;
        }

    }
}

