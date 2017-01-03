/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.domainmodel.restaurant;

/**
 * Cuisine applicable to a restaurant.
 */
public enum Cuisine {

    AFRICAN("african"),
    AMERICAN("american"),
    ASIAN("asian"),
    BAKERY("bakery"),
    BARBECUE("barbecue"),
    BRITISH("british"),
    CAFE("cafe"),
    CAJUN_CREOLE("creole"),
    CARIBBEAN("caribbean"),
    CHINESE("chinese"),
    CONTINENTAL("continental"),
    DELICATESSEN("delicatessen"),
    DESSERT("dessert"),
    EASTERN_EUROPEAN("easternEuropean"),
    FUSION_ECLECTIC("fusionEclectic"),
    EUROPEAN("european"),
    FRENCH("french"),
    GERMAN("german"),
    GLOBAL_INTERNATIONAL("globalInternational"),
    GREEK("greek"),
    INDIAN("indian"),
    IRISH("irish"),
    ITALIAN("italian"),
    JAPANESE("japanese"),
    MEDITERRANEAN("mediterranean"),
    MEXICAN_SOUTHWESTERN("mexicanSouthwestern"),
    MIDDLE_EASTERN("middleEastern"),
    PIZZA("pizza"),
    PUB("pub"),
    SEAFOOD("seafood"),
    SOUPS("soups"),
    SOUTH_AMERICAN("southAmerican"),
    SPANISH("spanish"),
    STEAKHOUSE("steakhouse"),
    SUSHI("sushi"),
    THAI("thai"),
    VEGETARIAN("vegetarian"),
    VIETNAMESE("vietnamese");

    private final String key;

    Cuisine(String key) {
        this.key = key;
    }

    public String getMessageKey() {
        return "cuisine." + key;
    }
}
