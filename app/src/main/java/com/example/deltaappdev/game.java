package com.example.deltaappdev;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;


//import org.w3c.dom.Document;

public class game extends AppCompatActivity {
    private static final String TAG = "game";

    int option[] = {0,0,0};
    int correct=0;
    int correctindex=0;
    int flag=0;
    int flag2=0;
    int flag3=0;
    int red=0;



    public void reset (){
        flag=0;
    }



    public void generatefuntion (int num){

        int temp;
        int flag=0;
        int index=0;
        Random rand = new Random();
        int arr[] = {0,0,0};

            while(flag==0){

                temp = rand.nextInt(num+2)+1;
                if(num%temp==0) {
                    flag = 1;
                    correct = temp;
                    arr[index]=(temp);
                    index++;
                }
            }

            temp = rand.nextInt(num+2)+1;
            while(index<3){
                if(index==1 && num%temp!=0)
                {
                    arr[index]=temp;
                    index++;
                }
                if(index==2 && num%temp!=0 && temp!=arr[1]){
                    arr[index]=temp;
                    index++;
                }

                    temp = rand.nextInt(num+2)+1;

            }

        temp=rand.nextInt(3);
        option[temp]=correct;
        temp=0;
        index=1;
        while(temp<=2){
            if(option[temp]!=correct){
                option[temp]= arr[index];
                index++;
            }
            temp++;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelayout);

        final TextView t = (TextView) findViewById(R.id.text);
        final Button back = (Button) findViewById(R.id.button5);
        final Button opt1 = (Button) findViewById(R.id.button2);
        final Button opt2 = (Button) findViewById(R .id.button3);
        final Button opt3 = (Button) findViewById(R.id.button4);
        final Button next = (Button) findViewById(R.id.next);
        final EditText input = (EditText) findViewById(R.id.input);
        final Button gen = (Button) findViewById(R.id.getopt);

        if(savedInstanceState!=null){
            flag=savedInstanceState.getInt("flag");
            flag2=savedInstanceState.getInt("flag2");
            option=savedInstanceState.getIntArray("option");
            correct=savedInstanceState.getInt("correct");
            correctindex=savedInstanceState.getInt("correctindex");
            flag3=savedInstanceState.getInt("flag3");
            red=savedInstanceState.getInt("red");
        }
        //set visibility
        if(flag3==0){
            input.setVisibility(View.VISIBLE);
            t.setVisibility(View.INVISIBLE);
            opt1.setVisibility(View.INVISIBLE);
            opt2.setVisibility(View.INVISIBLE);
            opt3.setVisibility(View.INVISIBLE);
            if(flag2==1)
                next.setVisibility(View.VISIBLE);
            else
                next.setVisibility(View.INVISIBLE);

        }
        else{
            opt1.setText(Integer.toString(option[0]));
            opt2.setText(Integer.toString(option[1]));
            opt3.setText(Integer.toString(option[2]));
            t.setText(Integer.toString(correctindex));
            if(flag2==1) {
                next.setVisibility(View.VISIBLE);
                //get colored buttons
                if(red==0)
                    opt1.setBackgroundColor(Color.RED);
                if(red==1)
                    opt2.setBackgroundColor(Color.RED);
                if(red==2)
                    opt3.setBackgroundColor(Color.RED);
                if(correct==Integer.parseInt(opt1.getText().toString()))
                    opt1.setBackgroundColor(Color.GREEN);
                if(correct==Integer.parseInt(opt2.getText().toString()))
                    opt2.setBackgroundColor(Color.GREEN);
                if(correct==Integer.parseInt(opt3.getText().toString()))
                    opt3.setBackgroundColor(Color.GREEN);

            }
            else
                next.setVisibility(View.INVISIBLE);
            input.setVisibility(View.INVISIBLE);
            t.setVisibility(View.VISIBLE);
            opt1.setVisibility(View.VISIBLE);
            opt2.setVisibility(View.VISIBLE);
            opt3.setVisibility(View.VISIBLE);

        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bac = new Intent(game.this,MainActivity.class);
                startActivity(bac);
            }
        });
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int val = Integer.parseInt(input.getText().toString());
                if(flag==0) {

                    String val = input.getText().toString();
                    if (val.matches("") || Integer.parseInt(val)==0) {
                        Toast.makeText(game.this,"Enter a number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        input.setVisibility(View.INVISIBLE);
                        t.setVisibility(View.VISIBLE);
                        t.setText(val);


                        //add toast;
                        flag = 1;
                        int number = Integer.parseInt(val);
                        correctindex=number;
                        generatefuntion(number);
                        flag3=1;

                        opt1.setText(Integer.toString(option[0]));
                        opt1.setVisibility(View.VISIBLE);
                        opt2.setText(Integer.toString(option[1]));
                        opt2.setVisibility(View.VISIBLE);
                        opt3.setText(Integer.toString(option[2]));
                        opt3.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opt1.setVisibility(View.INVISIBLE);
                opt2.setVisibility(View.INVISIBLE);
                opt3.setVisibility(View.INVISIBLE);
                reset();
                opt1.setBackgroundColor(Color.LTGRAY);
                opt2.setBackgroundColor(Color.LTGRAY);
                opt3.setBackgroundColor(Color.LTGRAY);
                input.setText("");
                next.setVisibility(View.INVISIBLE);
                input.setVisibility(View.VISIBLE);
                t.setVisibility(View.INVISIBLE);
                flag2=0;
                flag3=0;
            }
        });

        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(flag2==1)
                return;
            else
                flag2=1;
            if (Integer.parseInt(opt1.getText().toString())==correct) {
                opt1.setBackgroundColor(Color.GREEN);
                input.setText("");
                red=3;
            }
            else {
                red=0;
                opt1.setBackgroundColor(Color.RED);
                if(correct==Integer.parseInt(opt1.getText().toString()))
                opt1.setBackgroundColor(Color.GREEN);
                if(correct==Integer.parseInt(opt2.getText().toString()))
                opt2.setBackgroundColor(Color.GREEN);
                if(correct==Integer.parseInt(opt3.getText().toString()))
                opt3.setBackgroundColor(Color.GREEN);
                reset();
                input.setText("");
            }

            next.setVisibility(View.VISIBLE);

            }
        });


        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2==1)
                    return;
                else
                    flag2=1;
                if (Integer.parseInt(opt2.getText().toString())==correct) {
                    opt2.setBackgroundColor(Color.GREEN);
                    input.setText("");
                    red=3;
                }
                else {
                    red=1;
                    opt2.setBackgroundColor(Color.RED);
                    if(correct==Integer.parseInt(opt1.getText().toString()))
                    opt1.setBackgroundColor(Color.GREEN);
                    if(correct==Integer.parseInt(opt2.getText().toString()))
                    opt2.setBackgroundColor(Color.GREEN);
                    if(correct==Integer.parseInt(opt3.getText().toString()))
                    opt3.setBackgroundColor(Color.GREEN);
                    reset();
                    input.setText("");
                }

                next.setVisibility(View.VISIBLE);
            }
        });

        opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2==1)
                    return;
                else
                    flag2=1;
                if (Integer.parseInt(opt3.getText().toString())==correct) {
                    opt3.setBackgroundColor(Color.GREEN);
                    input.setText("");
                    red=3;
                }
                else {
                    red=2;
                    opt3.setBackgroundColor(Color.RED);
                    if(correct==Integer.parseInt(opt1.getText().toString()))
                    opt1.setBackgroundColor(Color.GREEN);
                    if(correct==Integer.parseInt(opt2.getText().toString()))
                    opt2.setBackgroundColor(Color.GREEN);
                    if(correct==Integer.parseInt(opt3.getText().toString()))
                    opt3.setBackgroundColor(Color.GREEN);
                    reset();
                    input.setText("");
                }

                next.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("flag",flag);
        outState.putInt("flag2",flag2);
        outState.putIntArray("option",option);
        outState.putInt("correct",correct);
        outState.putInt("correctindex",correctindex);
        outState.putInt("flag3",flag3);
        outState.putInt("red",red);
    }


}
