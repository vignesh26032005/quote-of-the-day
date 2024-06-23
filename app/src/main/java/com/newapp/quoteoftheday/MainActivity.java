package com.newapp.quoteoftheday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private String[] quotes;
    private String currentQuote;
    private ArrayList<String> favoriteQuotes;
    private Handler handler;
    private Runnable quoteRefreshRunnable;
    private static final int REFRESH_INTERVAL_MS = 60000; // 1 minute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        quotes = new String[]{
                "The only way to do great work is to love what you do. - Steve Jobs",
                "Life is what happens when you're busy making other plans. - John Lennon",
                "Get busy living or get busy dying. - Stephen King",
                "You have within you right now, everything you need to deal with whatever the world can throw at you. - Brian Tracy",
                "Believe you can and you're halfway there. - Theodore Roosevelt",
                "The purpose of our lives is to be happy. - Dalai Lama",
                "Success is not how high you have climbed, but how you make a positive difference to the world. - Roy T. Bennett",
                "Act as if what you do makes a difference. It does. - William James"
        };

        favoriteQuotes = new ArrayList<>();

        handler = new Handler();
        quoteRefreshRunnable = new Runnable() {
            @Override
            public void run() {
                displayNewQuote();
                handler.postDelayed(this, REFRESH_INTERVAL_MS);
            }
        };

        displayNewQuote();
        handler.postDelayed(quoteRefreshRunnable, REFRESH_INTERVAL_MS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(quoteRefreshRunnable);
    }

    public void displayNewQuote(View view) {
        displayNewQuote();
    }

    private void displayNewQuote() {
        Random random = new Random();
        currentQuote = quotes[random.nextInt(quotes.length)];
        quoteTextView.setText(currentQuote);
    }

    public void shareQuote(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, currentQuote);
        startActivity(Intent.createChooser(shareIntent, "Share quote via"));
    }

    public void favoriteQuote(View view) {
        if (!favoriteQuotes.contains(currentQuote)) {
            favoriteQuotes.add(currentQuote);
            Toast.makeText(this, "Quote marked as favorite", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Quote is already in favorites", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewFavorites(View view) {
        Intent intent = new Intent(this, FavoritesActivity.class);
        intent.putStringArrayListExtra("favoriteQuotes", favoriteQuotes);
        startActivity(intent);
    }
}
