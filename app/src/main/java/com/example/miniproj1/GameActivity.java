package com.example.miniproj1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import javax.xml.transform.Result;

public class GameActivity extends AppCompatActivity{

    String[] alphabetList = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String hint, word, letter;
    int countNot;
    String fragMessage, fragTitle;
    Boolean won;
    MediaPlayer rightMP, wrongMP;

    //widgets
    TextView L1,L2,L3,L4,L5,L6,L7,L8,L9,showHint;
    TextView W1, W2, W3, W4, W5, W6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        countNot = 0;

        rightMP = MediaPlayer.create(this,R.raw.right);
        wrongMP = MediaPlayer.create(this,R.raw.wrong);

        //CONNECT WORD'S LETTER TEXTVIEWS
        L1 = findViewById(R.id.txt1);
        L2 = findViewById(R.id.txt2);
        L3 = findViewById(R.id.txt3);
        L4 = findViewById(R.id.txt4);
        L5 = findViewById(R.id.txt5);
        L6 = findViewById(R.id.txt6);
        L7 = findViewById(R.id.txt7);
        L8 = findViewById(R.id.txt8);
        L9 = findViewById(R.id.txt9);

        //CONNECT WONG LETTER TEXTVIEWS
        W1 = findViewById(R.id.wrong1);
        W2 = findViewById(R.id.wrong2);
        W3 = findViewById(R.id.wrong3);
        W4 = findViewById(R.id.wrong4);
        W5 = findViewById(R.id.wrong5);
        W6 = findViewById(R.id.wrong6);

        //RECEIVE INFO FROM MAIN ACTIVITY
        //create intent
        Intent intent = getIntent();
        //create vars & receive
        Bundle info = intent.getExtras();
        word = (info.getString("word")).toUpperCase();
        hint = info.getString("hint");

        //SHOW HINT
        showHint = findViewById(R.id.hintDisp);
        showHint.setText("Hint: " + hint);
        showHint.setVisibility(View.VISIBLE);

        //ONLY REVEAL LENGTH OF WORD IN TEXTVIEWS
        final int length = word.length();
        switch (length){
            case 1:
                L1.setVisibility(View.VISIBLE);
                break;
            case 2:{
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                break;
            }
            case 3:{
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                break;
            }
            case 4:{
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
                break;
            }
            case 5:{
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
                L5.setVisibility(View.VISIBLE);
                break;
            }
            case 6:{
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
                L5.setVisibility(View.VISIBLE);
                L6.setVisibility(View.VISIBLE);
                break;
            }
            case 7:{
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
                L5.setVisibility(View.VISIBLE);
                L6.setVisibility(View.VISIBLE);
                L7.setVisibility(View.VISIBLE);
                break;
            }
            case 8:{
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
                L5.setVisibility(View.VISIBLE);
                L6.setVisibility(View.VISIBLE);
                L7.setVisibility(View.VISIBLE);
                L8.setVisibility(View.VISIBLE);
                break;
            }
            case 9:{
                L1.setVisibility(View.VISIBLE);
                L2.setVisibility(View.VISIBLE);
                L3.setVisibility(View.VISIBLE);
                L4.setVisibility(View.VISIBLE);
                L5.setVisibility(View.VISIBLE);
                L6.setVisibility(View.VISIBLE);
                L7.setVisibility(View.VISIBLE);
                L8.setVisibility(View.VISIBLE);
                L9.setVisibility(View.VISIBLE);
                break;
            }
        }

        //convert alphabetList array to UI list format
        ListAdapter listAdapt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, alphabetList);
        //display array in list UI
        ListView alphaList = findViewById(R.id.alphabet_list);
        alphaList.setAdapter(listAdapt);


        //ON CLICK LISTENER FOR LIST
        alphaList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                letter = (String.valueOf(parent.getItemAtPosition(position))).toUpperCase();
                word = word.toUpperCase();
                Character c = letter.charAt(0);

                if(containsLetter(word,letter)){
                    rightMP.start();
                    int[] indexes = getIndexesPlusOne(c,word);
                    for (int i=0;i<indexes.length;i++){
                        word = deleteLetter(indexes,word);
                        switch(indexes[i]){
                            case 1: { L1.setText(letter); break; }
                            case 2: { L2.setText(letter); break; }
                            case 3: { L3.setText(letter); break; }
                            case 4: { L4.setText(letter); break; }
                            case 5: { L5.setText(letter); break; }
                            case 6: { L6.setText(letter); break; }
                            case 7: { L7.setText(letter); break; }
                            case 8: { L8.setText(letter); break; }
                            case 9: { L9.setText(letter); break; }
                            default: break;
                        }
                        if (wordComplete(word)){
                            fragTitle = "YOU ESCAPED!!!";
                            fragMessage = "Tell Player 1 to pick on someone with his own brain's size.";
                            won = true;
                            //launch win fragment!
                            ResultFragment dFrag = new ResultFragment();
                            Bundle info = new Bundle();
                            info.putString("title",fragTitle);
                            info.putString("message",fragMessage);
                            info.putBoolean("won",won);
                            dFrag.setArguments(info);
                            dFrag.show((GameActivity.this).getSupportFragmentManager(),"Result Dialog");
                        }
                    }
                }
                else{
                    wrongMP.start();
                    switch(countNot){
                        case 0: { W1.setText(letter); countNot = countNot+1; break; }
                        case 1: { W2.setText(letter); countNot = countNot+1; break; }
                        case 2: { W3.setText(letter); countNot = countNot+1; break; }
                        case 3: { W4.setText(letter); countNot = countNot+1; break; }
                        case 4: { W5.setText(letter); countNot = countNot+1; break; }
                        case 5: { W6.setText(letter); countNot = countNot+1; break; }
                        default:{
                            //launch lose fragment!
                            fragTitle = "GG R.I.P.";
                            fragMessage = "It was nice knowing you. JK, new phone who 'dis?";
                            won = false;
                            //launch win fragment!
                            ResultFragment dFrag = new ResultFragment();
                            Bundle info = new Bundle();
                            info.putString("title",fragTitle);
                            info.putString("message",fragMessage);
                            info.putBoolean("won",won);
                            dFrag.setArguments(info);
                            dFrag.show((GameActivity.this).getSupportFragmentManager(),"Result Dialog");

                            break;
                        }
                    }
                }
            }
        });

    }

    public Boolean wordComplete(String word){
        if (word.matches("[0-9]+")){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean containsLetter(String word, String letter){
        if (word.contains(letter)){
            return true;
        }
        else{
            return false;
        }
    }

    //all indexes returned is 1 or more (so true index is i-1). if the array is only 0's, then there is no letter found
    public int[] getIndexesPlusOne(Character letter, String word){
        int[] storage = new int[word.length()];
        int j = 0;
        for (int i = 1; i <= word.length(); i++){
            Character wordchar = word.charAt(i-1);
            if(wordchar.equals(letter)){
                storage[j] = i;
                j++;
            }
        }
        return storage;
    }

    public String deleteLetter(int[] indexList, String word){
        StringBuffer sbf = new StringBuffer(word);
        for(int i = 1; i <= indexList.length; i++){
            //if no letter matches, the entire array will be 0
            if(indexList[i-1]==0){ }
            //if last letter of string
            else if (indexList[i-1]==indexList.length) {
                sbf.replace(indexList.length - 1, indexList.length, "9");
                word = sbf.toString();
            }
            //first letter of string
            else if (indexList[i-1]==1){
                sbf.replace(0, 1, "9");
                word = sbf.toString();
            }
            //middle letters
            else if (indexList[i-1]==2){
                sbf.replace(1,2,"9");
                word = sbf.toString();
            }
            else if (indexList[i-1]==3){
                sbf.replace(2,3,"9");
                word = sbf.toString();
            }
            else if (indexList[i-1]==4){
                sbf.replace(3,4,"9");
                word = sbf.toString();
            }
            else if (indexList[i-1]==5){
                sbf.replace(4,5,"9");
                word = sbf.toString();
            }
            else if (indexList[i-1]==6){
                sbf.replace(5,6,"9");
                word = sbf.toString();
            }
            else if (indexList[i-1]==7){
                sbf.replace(6,7,"9");
                word = sbf.toString();
            }
            else if (indexList[i-1]==8){
                sbf.replace(7,8,"9");
                word = sbf.toString();
            }
            else if (indexList[i-1]==9){
                sbf.replace(8,9,"9");
                word = sbf.toString();
            }

        }
        return word;
    }

}
