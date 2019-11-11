package com.example.quiz.MainActivity;

/****************************************
 *      created by Shavlovskii Ivan     *
 *               08.11.2019             *
 ***************************************/

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.quiz.R;

public class LoadFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.load_fragment, null);

        ImageView imageView = (ImageView) v.findViewById(R.id.icon);
        Glide.with(this).load(R.drawable.load).into(imageView);

        return v;
    }


}
