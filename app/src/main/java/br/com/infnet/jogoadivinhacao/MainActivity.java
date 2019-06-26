package br.com.infnet.jogoadivinhacao;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private ImageView card1;
    private ImageView card2;
    private ImageView card3;
    private ImageView cardChoice;
    private TextView textResult;
    private Button buttonConfirm;
    private Button buttonRestart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);

        setTitle("Jogo da adivinhação");

        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonRestart = findViewById(R.id.buttonRestart);

        textResult = findViewById(R.id.textViewResult);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonConfirm.setVisibility(View.VISIBLE);
                cardChoice = card1;

                card1.setImageAlpha(100);
                card2.setImageAlpha(255);
                card3.setImageAlpha(255);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonConfirm.setVisibility(View.VISIBLE);
                cardChoice = card2;

                card1.setImageAlpha(255);
                card2.setImageAlpha(100);
                card3.setImageAlpha(255);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonConfirm.setVisibility(View.VISIBLE);
                cardChoice = card3;

                card1.setImageAlpha(255);
                card2.setImageAlpha(255);
                card3.setImageAlpha(100);
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCard(cardChoice);
            }
        });

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });
    }


    private void openCard(ImageView currentCardView) {

        int[] images = {R.drawable.card_curinga, R.drawable.card_curinga, R.drawable.card_a};
        currentCardView.setImageResource(images[RANDOM.nextInt(images.length)]);

        currentCardView.setImageAlpha(255);


        final Bitmap bmapCardView = ((BitmapDrawable) currentCardView.getDrawable()).getBitmap();
        Drawable drawableCuringa = getResources().getDrawable(R.drawable.card_curinga);
        final Bitmap bitmapCuringa = ((BitmapDrawable) drawableCuringa).getBitmap();

        //if (currentCardView.getDrawable().getConstantState() == R.drawable.card_curinga) {
        if(bmapCardView.sameAs(bitmapCuringa))
        {
            textResult.setText("Você escolheu o CURINGA! PERDEU!!!");
            textResult.setTextColor(Color.RED);
        }
        else
        {
            textResult.setText("Você escolheu o Espadilha! GANHOU!!!");
            textResult.setTextColor(Color.GREEN);
        }

        card1.setClickable(false);
        card2.setClickable(false);
        card3.setClickable(false);

        buttonConfirm.setVisibility(View.INVISIBLE);
        buttonRestart.setVisibility(View.VISIBLE);
        textResult.setVisibility(View.VISIBLE);

    }

    private void restartGame() {

        card1.setClickable(true);
        card2.setClickable(true);
        card3.setClickable(true);

        card1.setImageResource(R.drawable.card_default);
        card2.setImageResource(R.drawable.card_default);
        card3.setImageResource(R.drawable.card_default);

        card1.setImageAlpha(255);
        card2.setImageAlpha(255);
        card3.setImageAlpha(255);

        buttonConfirm.setVisibility(View.INVISIBLE);
        buttonRestart.setVisibility(View.INVISIBLE);
        textResult.setVisibility(View.INVISIBLE);
        textResult.setTextColor(Color.BLACK);
    }


}
