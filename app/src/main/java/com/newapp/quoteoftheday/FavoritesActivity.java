package com.newapp.quoteoftheday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private ListView favoritesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoritesListView = findViewById(R.id.favoritesListView);

        ArrayList<String> favoriteQuotes = getIntent().getStringArrayListExtra("favoriteQuotes");

        if (favoriteQuotes != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, favoriteQuotes);
            favoritesListView.setAdapter(adapter);
        }
    }
}
