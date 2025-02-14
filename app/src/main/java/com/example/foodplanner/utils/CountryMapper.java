package com.example.foodplanner.utils;

import com.example.foodplanner.R;

import java.util.HashMap;
import java.util.Map;

public class CountryMapper {
    private static final Map<String, Integer> countryImageMap = new HashMap<>();

    static {
        countryImageMap.put("American", R.drawable.america);
        countryImageMap.put("British", R.drawable.britain);
        countryImageMap.put("Canadian", R.drawable.canada);
        countryImageMap.put("Chinese", R.drawable.china);
        countryImageMap.put("Croatian", R.drawable.croatia);
        countryImageMap.put("Dutch", R.drawable.dutch);
        countryImageMap.put("Egyptian", R.drawable.egypt);
        countryImageMap.put("Filipino", R.drawable.philippine);
        countryImageMap.put("French", R.drawable.france);
        countryImageMap.put("Greek", R.drawable.greece);
        countryImageMap.put("Indian", R.drawable.india);
        countryImageMap.put("Irish", R.drawable.ireland);
        countryImageMap.put("Italian", R.drawable.italy);
        countryImageMap.put("Jamaican", R.drawable.jamaica);
        countryImageMap.put("Japanese", R.drawable.japan);
        countryImageMap.put("Kenyan", R.drawable.kenya);
        countryImageMap.put("Malaysian", R.drawable.malaysia);
        countryImageMap.put("Mexican", R.drawable.mexico);
        countryImageMap.put("Moroccan", R.drawable.morocco);
        countryImageMap.put("Polish", R.drawable.poland);
        countryImageMap.put("Portuguese", R.drawable.portugal);
        countryImageMap.put("Russian", R.drawable.russia);
        countryImageMap.put("Spanish", R.drawable.spain);
        countryImageMap.put("Thai", R.drawable.thailand);
        countryImageMap.put("Tunisian", R.drawable.tunisia);
        countryImageMap.put("Turkish", R.drawable.turkey);
        countryImageMap.put("Ukrainian", R.drawable.ukraine);
        countryImageMap.put("Uruguayan", R.drawable.uruguay);
        countryImageMap.put("Vietnamese", R.drawable.vietnam);
    }

    public static int getImageForCountry(String area) {
        Integer imageRes = countryImageMap.get(area);
        return imageRes != null ? imageRes : R.drawable.holder;
    }
}
