package com.anupam.androidcameraxtflite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class SecondActivity extends AppCompatActivity {

    private Button btn_Add,btn_sbm;
    private Intent intentToAdd,intentToConfirm;
    private ArrayList<String> newlist;
    private String url = "http://www.recipepuppy.com/api/?i=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final ArrayList<String> detecteditems ;
        detecteditems =getIntent().getStringArrayListExtra("DataValue");

        btn_Add = (Button) findViewById(R.id.add_item);
        btn_sbm = (Button) findViewById(R.id.confirm_itm);

        intentToAdd = new Intent(SecondActivity.this,MainActivity.class);
        intentToConfirm = new Intent(SecondActivity.this,ThirdActivity.class);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToAdd.putExtra("sender","SecondActivity");
                intentToAdd.putStringArrayListExtra("DataValue",detecteditems);
                startActivity(intentToAdd);
            }
        });

        btn_sbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newlist = new ArrayList<String>(new HashSet<String>(detecteditems));
                for (String s:newlist)
                {
                    url+=s+",";
                }
                intentToConfirm.putExtra("url",url);
                startActivity(intentToConfirm);
            }
        });


        //ArrayList<String> detecteditems = new ArrayList<>();
//        detecteditems.add("laptop");
//        detecteditems.add("laptop2");
//        detecteditems.add("mouse");
//        detecteditems.add("dfs");

        ListView view = (ListView)  findViewById(R.id.student_exam_registered_list);
        secondadapter adap = new secondadapter(detecteditems,SecondActivity.this);
        view.setAdapter(adap);


    }
}
