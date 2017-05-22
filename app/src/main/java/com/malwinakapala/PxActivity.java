package com.malwinakapala;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.malwinakapala.densitycalc.R;


public class PxActivity extends Fragment {

    private EditText editText1;
    private TextView textView;
    private RadioGroup resolutionGroup;
    private View rootView;

    public PxActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.pixels_fragment, container, false);

        getActivity().setTitle(R.string.action_bar_title_px);

        setHasOptionsMenu(true);

        textView = (TextView) rootView.findViewById(R.id.pixels_text_view);
        editText1 = (EditText) rootView.findViewById(R.id.pxEditText);
        resolutionGroup = (RadioGroup) rootView.findViewById(R.id.toggleGroup);
        resolutionGroup.check(resolutionGroup.getChildAt(0).getId());

        resolutionGroup.setOnCheckedChangeListener(ToggleListener);

        initToggleButtons(resolutionGroup);

        editText1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() != KeyEvent.ACTION_DOWN)
                    return false;

                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    refreshDensityTextView();
                    return true;
                }
                return false;
            }
        });
        return rootView;
    }

    //tu metoda z parametrem name
    private void refreshDensityTextView() {
        if (editText1.getText().toString().length() == 0) {
            return;
        }

        if (editText1.getText().toString().length() > 10) {
            Toast.makeText(getActivity(), "Podałeś więcej niż 10 znaków.To za duża liczba.", Toast.LENGTH_SHORT).show();
            return;
        }
//
        // Dzielnik
        float result = 0;
        float divider = 1;

        int selectedButtonId = resolutionGroup.getCheckedRadioButtonId();

        ToggleButton selectedButton = (ToggleButton)rootView.findViewById(selectedButtonId);

        String name = selectedButton.getText().toString();

        switch (name) {
            case "ldpi":
                divider = 0.75f;
                break;
            case "mdpi":
                divider = 1f;
                break;
            case "hdpi":
                divider = 1.5f;
                break;
            case "xhdpi":
                divider = 2f;
                break;
            case "xxhdpi":
                divider = 3f;
                break;
            case "xxxhdpi":
                divider = 4f;
            default:
                divider = 1;
        }

        result = Integer.parseInt(editText1.getText().toString());
        result = Math.round(result / divider);

        textView.setText(String.valueOf(result));
    }

    private void initToggleButtons(final RadioGroup radioGroup) {
        for (int j = 0; j < radioGroup.getChildCount(); j++) {
            final ToggleButton toggleButton = (ToggleButton) radioGroup.getChildAt(j);
            toggleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View clickedToggleButton) {
                    onToggle(radioGroup, clickedToggleButton);
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_px, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    static final RadioGroup.OnCheckedChangeListener ToggleListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
         public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
            for (int j = 0; j < radioGroup.getChildCount(); j++) {
                final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                view.setChecked(view.getId() == i);
            }
        }
    };

    public void onToggle(RadioGroup group, View view) {
        group.check(view.getId());

      refreshDensityTextView();

    }


}