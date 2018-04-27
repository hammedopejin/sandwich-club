package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String NAME = "name";
    private static final String MAINNAME = "mainName";
    private static final String ALSOKNOWNAS = "alsoKnownAs";
    private static final String PLACEOFORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

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

            JSONObject jName = jObject.getJSONObject(NAME);
            mainName = jName.optString(MAINNAME);

            jsonArratToArrayList(jName, ALSOKNOWNAS, alsoKnownAs);

            placeOfOrigin = jObject.optString(PLACEOFORIGIN);
            description = jObject.optString(DESCRIPTION);
            image = jObject.optString(IMAGE);

            jsonArratToArrayList(jObject, INGREDIENTS, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }

    private static void jsonArratToArrayList (JSONObject jsonObject, String name, List<String> listItem){
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray(name);
            for (int i = 0; i < jsonArray.length(); i++){
                listItem.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
