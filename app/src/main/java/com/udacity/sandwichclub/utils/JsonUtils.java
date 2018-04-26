package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {



    public static Sandwich parseSandwichJson(String json) {
        String mainName = "";
        List<String> alsoKnownAs = new ArrayList<String>();
        String placeOfOrigin = "";
        String description = "";
        String image = "";
        List<String> ingredients = new ArrayList<String>();

        JSONObject jObject = null;
        try {
            jObject = new JSONObject(json);

            JSONObject jName = jObject.getJSONObject("name");
            mainName = jName.getString("mainName");

            JSONArray jAlsoKnownAsArray = jName.getJSONArray("alsoKnownAs");

            for (int i = 0; i < jAlsoKnownAsArray.length(); i++){
                alsoKnownAs.add(jAlsoKnownAsArray.getString(i));
            }

            placeOfOrigin = jObject.getString("placeOfOrigin");
            description = jObject.getString("description");
            image = jObject.getString("image");

            JSONArray jIngredientArray = jObject.getJSONArray("ingredients");
            for (int i = 0; i < jIngredientArray.length(); i++){
                ingredients.add(jIngredientArray.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }
}
