package com.example.test01.one;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test01.R;


public class ActivityFragment extends Fragment {
    View view;
    Button button;
    TextView isShowAcitonBar,textDetial,text_bk_c;
    Switch aSwitch;
    Spinner spinner;

    String isShowActio,bkColor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity,container,false);
        initView();
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    isShowAcitonBar.setText("true");
                    isShowActio="true";
                    textDetial.setText("zhaoxitech://com.zhaoxitech.cbook?action_bar="+isShowActio+"?color="+bkColor);
                }
                else
                {
                    isShowAcitonBar.setText("flase");
                    isShowActio="flase";
                    textDetial.setText("zhaoxitech://com.zhaoxitech.cbook?action_bar="+isShowActio+"?color="+bkColor);
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bkColor=parent.getItemAtPosition(position).toString();
                textDetial.setText("zhaoxitech://com.zhaoxitech.cbook?action_bar="+isShowActio+"?color="+bkColor);
                text_bk_c.setText(bkColor);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    private void initView() {
        button=view.findViewById(R.id.button);
        isShowAcitonBar=view.findViewById(R.id.isShowActionBar);
        aSwitch=view.findViewById(R.id.switch1);
        textDetial=view.findViewById(R.id.text_detial);
        spinner=view.findViewById(R.id.spinner);
        text_bk_c=view.findViewById(R.id.text_bk_c);
    }
}
