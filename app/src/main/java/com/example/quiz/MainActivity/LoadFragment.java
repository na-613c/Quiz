package com.example.quiz.MainActivity;


import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quiz.R;

@SuppressWarnings("ALL")
public class LoadFragment extends Fragment {
    private ImageView imageView;
    public static Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.load_fragment, null);

        toast = Toast.makeText(getActivity(),
                "Check your internet connection!",
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 300);

        imageView = (ImageView) v.findViewById(R.id.icon);
        Glide.with(this).load(R.drawable.load).into(imageView);

        return v;
    }

    public static void internetMessage(){
        toast.show();
    }
}
