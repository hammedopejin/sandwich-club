package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView placeOfOrigin;
    TextView alsoKnownAs;
    TextView description;
    TextView ingredients;
    TextView mainName;

    Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        placeOfOrigin = findViewById(R.id.origin_tv);
        alsoKnownAs = findViewById(R.id.also_known_tv);
        description = findViewById(R.id.description_tv);
        ingredients = findViewById(R.id.ingredients_tv);
        mainName = findViewById(R.id.main_name_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        String mainNameString = sandwich.getMainName();
        if (mainNameString.isEmpty()){
            mainNameString = "Unknown";
        }
        mainName.setText(mainNameString);

        List igredientsArray = sandwich.getIngredients();
        String ingredientString = "";
        if(igredientsArray.size() > 0) {
            for (int i = 0; i < igredientsArray.size(); i++) {
                if (i < (igredientsArray.size() -1)) {
                    ingredientString += igredientsArray.get(i) + ", ";
                } else { ingredientString += igredientsArray.get(i) + ""; }
            }
        }
        if (ingredientString.isEmpty()){
            ingredientString = "Unknown";
        }
        ingredients.setText(ingredientString);

        String descriptionString = sandwich.getDescription();
        if (descriptionString.isEmpty()){
            descriptionString = "Unknown";
        }
        description.setText(descriptionString);

        String placeOfOriginString = sandwich.getPlaceOfOrigin();
        if (placeOfOriginString.isEmpty()){
            placeOfOriginString = "Unknown";
        }
        placeOfOrigin.setText(placeOfOriginString);

        List alsoKnownAsArray = sandwich.getAlsoKnownAs();
        String alsoKnownAsString = "";
        if (alsoKnownAsArray.size() > 0) {
            for (int i = 0; i < alsoKnownAsArray.size(); i++) {
                if (i < (alsoKnownAsArray.size() -1)) {
                    alsoKnownAsString += alsoKnownAsArray.get(i) + ", ";
                } else { alsoKnownAsString += alsoKnownAsArray.get(i) + ""; }
            }
        }
        if (alsoKnownAsString.isEmpty()){
            alsoKnownAsString = "Unknown";
        }
        alsoKnownAs.setText(alsoKnownAsString);
    }
}
