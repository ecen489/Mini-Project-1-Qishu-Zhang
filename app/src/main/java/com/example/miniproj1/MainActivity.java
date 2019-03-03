package com.example.miniproj1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements GetWordFragment.OnInputListener{

    private static final int REQ_CODE_ANS = 1111; //First/Main Activity

    //widgets
    private Button mOpenDialog; // opens first fragment
    private Button startGame; //opens 2nd activity
    private TextView player2, player1;
    private TextView warning;

    //vars
    public String key_word, key_hint; // stores word submitted from first fragment

    @Override
    public void sendInput(String input) {
        key_word = input;
        startGame.setVisibility(VISIBLE);
        player1.setVisibility(VISIBLE);
        player2.setVisibility(VISIBLE);
        warning.setVisibility(VISIBLE);
        mOpenDialog.setVisibility(View.INVISIBLE);
    }

    @Override
    public void sendHint(String hint) {
        key_hint = hint;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOpenDialog = findViewById(R.id.open_dialog);
        startGame = findViewById(R.id.button_startGame);
        player1 = findViewById(R.id.txt_player1);
        player2 = findViewById(R.id.txt_player2);
        warning = findViewById(R.id.txt_warning);

        mOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetWordFragment dialog = new GetWordFragment();
                dialog.show(getSupportFragmentManager(), "MyCustomDialog");

            }
        });

    }

    public void StartClk(View view) {
//        Toast.makeText(MainActivity.this, key_hint, Toast.LENGTH_SHORT).show();
        //create intent & attach data
        Intent forwardIntent = new Intent(MainActivity.this,GameActivity.class);
        //bundle & send the two data points QuestionPage needs (topic & score)
        Bundle info = new Bundle();
        info.putString("word", key_word);
        info.putString("hint", key_hint);
        forwardIntent.putExtras(info);

        //start game activity
        startActivityForResult(forwardIntent,REQ_CODE_ANS);
    }

}
