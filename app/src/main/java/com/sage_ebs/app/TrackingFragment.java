package com.sage_ebs.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TrackingFragment extends Fragment implements View.OnClickListener{

    private EditText editText;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.trackingfragment, container, false);
        editText = view.findViewById(R.id.tracking_id);
        button = view.findViewById(R.id.check);
        button.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View view){
        String tracking_id = editText.getText().toString().trim();
        if (tracking_id.isEmpty()){
            editText.setError("Please, BL Number");
            editText.requestFocus();
        }else if (view.getId() == R.id.check){
            if (getActivity() != null){
                Toast.makeText(getActivity(), "Hold on, checking through", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getActivity(), "Please enter a valid tracking Id", Toast.LENGTH_SHORT).show();
        }
    }

}
