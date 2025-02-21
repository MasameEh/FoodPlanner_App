package com.example.foodplanner.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;

public class CustomToast {


    public static void showCustomToast(Context _context, String err){
        LayoutInflater inflater = LayoutInflater.from(_context);
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView text = layout.findViewById(R.id.toast_text);

        text.setText(err);
        Toast toast = new Toast(_context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}
