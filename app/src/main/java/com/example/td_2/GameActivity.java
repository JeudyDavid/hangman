package com.example.td_2;

import static java.lang.Math.round;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private GameActivity activity;
    private String[] words = {
            "Troubleboy","Andybeatz","Bedgine",
            "Rutshelle","Kadilak","Danola",
            "Kolonel"
    };

    // kantite chans itilizatè a
    private int chans = 5;

    // mo kap chwazi otomatik la
    private String chosenWord;

    // Widjet pou afiche chans yo
    TextView mChansText;

    // Widget pou achite karaktè kache yo
    TextView mHidText;

    // table mo kache yo
    private char[] moKache;
    private static Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.activity = this;
        mChansText = (TextView) findViewById(R.id.chansTv);
        mChansText.setText(chans + " chans");

        mHidText = (TextView) findViewById(R.id.hiddenWord);

        // get the random word
        chosenWord = getRandomWord();

        // init the array with the chosenWord length.
        moKache = new char[chosenWord.length()];

        // Create underscores based on that length
        int let = (int) Math.ceil(chosenWord.length() * 0.20);
        Log.d("let", String.valueOf(let));
        int j = 1;
        for(int i=0; i< chosenWord.length(); i++){
            moKache[i] = '_';
            if(let == 1) {
                moKache[1] = chosenWord.charAt(let);
            }
            if(let == 2) {
                moKache[1] = chosenWord.charAt(1);
                moKache[3] = chosenWord.charAt(3);
            }

        }
        // Set the string to Widget
        mHidText.setText(getDisplayText());
    }

    public String getDisplayText(){
        // Create a new string based on the <moKache>, array of characters
        StringBuilder sb = new StringBuilder();

        for(int i=0; i < moKache.length; i++){
            sb.append(moKache[i] + " ");
        }

        return sb.toString();
    }

    // return a random digit between 0 and words length
    public String getRandomWord(){
        return words[random.nextInt(words.length)];
    }

    public void popup(){
        AlertDialog.Builder mypopup = new AlertDialog.Builder(activity);
        mypopup.setTitle("Hangman");
        mypopup.setMessage("si ou vle rekomanse peze wi");
        mypopup.setNegativeButton("non", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //pass
            }
        });
        mypopup.setPositiveButton("wi", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.recreate();
            }
        });
        mypopup.show();
    }
    public void onTapLetter(View theView){
        // get the tap letter
        Button mButton = (Button) theView;
        String letter = mButton.getText().toString();
        letter = letter.toLowerCase();
        chosenWord = chosenWord.toLowerCase();
        Log.d("testage", letter);

//        System.out.println(chosenWord);
//        System.out.println(letter);

        // The following condition will fail sometimes, because of the case. Make sure your convert both <chosenWord> and <letter> to lower or uppercase.
        if(chosenWord.contains(letter)){
            // get the index
            int index = chosenWord.indexOf(letter);
            String mh = mHidText.getText().toString();
            // Tanke endèks la >= 0, sa vle di gen lèt la nan <chosenWord> la toujou
            while(index >= 0){
                // This is just a hack to convert the string to char, since moKache is an array of characters
                moKache[index] = letter.charAt(0); // chartAt(0) to take the first character from the letter.

                index = chosenWord.indexOf(letter, index + 1); // DO NOT forget to specify the fromIndex to avoid infinite loop
            }
            if(chans > 0 && !mh.contains("_")) {
                Toast.makeText(this, "ou genyen pati sa felisitasyon", Toast.LENGTH_SHORT).show();
                popup();
            }
            System.out.println(moKache);
        }else{
            String mh = mHidText.getText().toString();
            // Minus the total of tries

//            if(mh.contains("_")) {
//                Log.d("contenu", "nou  bon sou yo");
//            }
            if(chans != 0 && mh.contains("_")) {
                chans -= 1;
                Toast.makeText(this, "Lèt ou tape a, pa nan mo a", Toast.LENGTH_SHORT).show();
            }
            if(chans == 0 && mh.contains("_")) {
                Toast.makeText(this, "ou pa genyen pati sa", Toast.LENGTH_SHORT).show();
                popup();
            }
            if(chans > 0 && !mh.contains("_")) {
                Toast.makeText(this, "ou genyen pati sa felisitasyon", Toast.LENGTH_SHORT).show();
                popup();
            }
            mChansText.setText(chans + " chans");
            // Display a short message
//            if(chans!=0) {
//                Toast.makeText(this, "Lèt ou tape a, pa nan mo a", Toast.LENGTH_SHORT).show();
//            }
        }

        // Finally, display the new text.
        mHidText.setText(getDisplayText());
    }



}