package com.example.td_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private String[] words = {
            "Rutshelle", "Fatima", "Bedjine",
            "Shassy"
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

        mChansText = (TextView) findViewById(R.id.chansTv);
        mChansText.setText(chans + " chans");

        mHidText = (TextView) findViewById(R.id.hiddenWord);

        // get the random word
        chosenWord = getRandomWord();

        // init the array with the chosenWord length.
        moKache = new char[chosenWord.length()];

        // Create underscores based on that length
        for(int i=0; i< chosenWord.length(); i++){
            moKache[i] = '_';
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

            // Tanke endèks la >= 0, sa vle di gen lèt la nan <chosenWord> la toujou
            while(index >= 0){
                // This is just a hack to convert the string to char, since moKache is an array of characters
                moKache[index] = letter.charAt(0); // chartAt(0) to take the first character from the letter.

                index = chosenWord.indexOf(letter, index + 1); // DO NOT forget to specify the fromIndex to avoid infinite loop
            }
            System.out.println(moKache);
        }else{
            // Minus the total of tries
            chans -= 1;
            mChansText.setText(chans + " chans");
            // Display a short message
            Toast.makeText(this, "Lèt ou tape a, pa nan mo a", Toast.LENGTH_SHORT).show();
        }

        // Finally, display the new text.
        mHidText.setText(getDisplayText());
    }
}