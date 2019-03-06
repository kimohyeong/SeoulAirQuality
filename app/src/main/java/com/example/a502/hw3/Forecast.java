package com.example.a502.hw3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by 502 on 2018-11-15.
 */

public class Forecast  extends AppCompatActivity{

    TextView dateTxt;
    TextView alertTxt;
    TextView pollTxt;
    TextView levelTxt;
    TextView actionTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast);

        dateTxt = (TextView)findViewById(R.id.date);
        alertTxt = (TextView)findViewById(R.id.alert);
        pollTxt = (TextView)findViewById(R.id.poll);
        levelTxt = (TextView)findViewById(R.id.level);
        actionTxt = (TextView)findViewById(R.id.action);

        XmlParseTask task = new XmlParseTask(dateTxt,alertTxt,pollTxt,levelTxt,actionTxt);
        task.execute("http://openapi.seoul.go.kr:8088/665a45756e6b646834376643756645/xml/ForecastWarningUltrafineParticleOfDustService/1/5/");

    }
}
