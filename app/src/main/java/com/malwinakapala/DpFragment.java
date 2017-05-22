package com.malwinakapala;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.malwinakapala.densitycalc.R;

public class DpFragment extends Fragment {

    public DpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dp_fragment, container, false);

        final TextView textView1 = (TextView) rootView.findViewById(R.id.myTextView1);
        final TextView textView2 = (TextView) rootView.findViewById(R.id.myTextView2);
        final TextView textView3 = (TextView) rootView.findViewById(R.id.myTextView3);
        final TextView textView4 = (TextView) rootView.findViewById(R.id.myTextView4);
        final TextView textView5 = (TextView) rootView.findViewById(R.id.myTextView5);
        final TextView textView6 = (TextView) rootView.findViewById(R.id.myTextView6);
        final EditText editText = (EditText) rootView.findViewById(R.id.myEditText);

       getActivity().setTitle(R.string.action_bar_title_dp);

        setHasOptionsMenu(true);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() != KeyEvent.ACTION_DOWN)
                    return false;

                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    if (editText.getText().toString().length() > 10) {
                        Toast.makeText(getActivity(), "Podałeś więcej niż 10 znaków.To za duża liczba.", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    int result;
                    try {
                        result = Integer.parseInt(editText.getText().toString());
                    } catch (NumberFormatException nfe) {
                        result = 0;
                    }


                    textView1.setText("ldpi:" + Math.round(0.75 * result) + "px");
                    textView2.setText("mdpi:" + Math.round(result) + "px");
                    textView3.setText("hdpi:" + Math.round(1.5 * result) + "px");
                    textView4.setText("xhdpi:" + Math.round(2 * result) + "px");
                    textView5.setText("xxhdpi:" + Math.round(3 * result) + "px");
                    textView6.setText("xxxhdpi:" + Math.round(4 * result) + "px");

                    editText.setText("");
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_dp, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}


