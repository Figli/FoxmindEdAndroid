package com.anagrams.anagrams;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "anagram";

    @BindView(R.id.result)
    TextView result;

    @BindView(R.id.your_result)
    TextView textYourResult;

    @BindView(R.id.textInputLayout)
    TextInputLayout textInputLayout;

    @BindView(R.id.reverseButton)
    Button reverseButton;

    @BindView(R.id.inputText)
    TextInputEditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.reverseButton)
    public void reversWords() {
        String text = inputText.getText().toString();
        result.setText(splitString(text));
    }

    public static String splitString(String string) {
        char[] sourceStringToChar = string.toCharArray();
        int wordStartIndex = -1;

        for (int i = 0; i < sourceStringToChar.length; i++) {
            if (wordStartIndex < 0) {
                wordStartIndex = i;
            }
            if (i + 1 == sourceStringToChar.length || sourceStringToChar[i + 1] == ' ') {
                invertWord(sourceStringToChar, wordStartIndex, i);
                wordStartIndex = -1;
            }
        }
        Log.d(TAG, "The words are reversed");
        return new String(sourceStringToChar);
    }

    private static void invertWord(char[] sourceStringToChar,
                                   int wordStartIndex, int wordEndIndex) {

        while (wordStartIndex < wordEndIndex) {
            if (!Character.isAlphabetic(sourceStringToChar[wordStartIndex])) {
                wordStartIndex++;
            } else if (!Character.isAlphabetic(sourceStringToChar[wordEndIndex])) {
                wordEndIndex--;
            } else {
                char tempChar = sourceStringToChar[wordStartIndex];
                sourceStringToChar[wordStartIndex] = sourceStringToChar[wordEndIndex];
                sourceStringToChar[wordEndIndex] = tempChar;
                wordStartIndex++;
                wordEndIndex--;
            }
        }
        Log.d(TAG, "The word is inverted");
    }
}
