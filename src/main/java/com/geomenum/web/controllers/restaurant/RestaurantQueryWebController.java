/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.controllers.restaurant;

import com.geomenum.common.Countries;
import com.geomenum.common.Currencies;
import com.geomenum.common.Languages;
import com.geomenum.common.domainmodel.restaurant.Cuisine;
import com.geomenum.web.View;
import com.geomenum.web.WebURLPath;
import com.geomenum.web.controllers.AbstractWebController;
import com.geomenum.web.controllers.WebController;
import com.geomenum.web.requestsresponses.restaurant.GetRestaurantRequest;
import com.geomenum.web.requestsresponses.restaurant.GetRestaurantResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;

import static com.geomenum.common.Countries.Country;

@WebController
@RequestMapping(value = WebURLPath.RESTAURANT_ROOT, method = RequestMethod.GET)
@SessionAttributes({RestaurantQueryWebController.restaurantBackingBeanName})
public class RestaurantQueryWebController extends AbstractWebController {

    public static final String restaurantBackingBeanName = "restaurant";

    @RequestMapping
    public String viewRestaurant(Model model) {
        if(model.containsAttribute(restaurantBackingBeanName)) {
            return View.RESTAURANT;
        } else {
            return View.ERROR;
        }
    }

    @RequestMapping(params = "mode=edit")
    public String editRestaurant(Model model) {
        if(model.containsAttribute(restaurantBackingBeanName)) {
            model.addAttribute("mode", "edit");
            return View.RESTAURANT;
        } else {
            return View.ERROR;
        }
    }

    @ModelAttribute
    private void populateRestaurant(@PathVariable("restaurantId") String restaurantId,
                                    Model model) {
        model.asMap().remove(restaurantBackingBeanName);
        GetRestaurantResponse response = createRequestDispatcher().getResponse(
                new GetRestaurantRequest(restaurantId),
                GetRestaurantResponse.class);
        if(!response.hasExceptionOccurred()) {
            model.addAttribute(restaurantBackingBeanName, response.getRestaurant());
        }
    }

    @ModelAttribute("cuisines")
    private Cuisine[] populateCuisines() {
        // sort the map by values
        //LinkedHashMap<Cuisine, String> cuisinesStyles = Maps.newLinkedHashMap();
        //Collections.sort();
        return Cuisine.values();
    }

    @ModelAttribute("currencies")
    private Collection<Currency> populateCurrencies(Locale lang) {
        return Currencies.getCurrenciesSortedByName(lang);
    }

    @ModelAttribute("countries")
    private Collection<Country> populateCountries(Locale lang) {
        return Countries.getCountriesSortedByName(lang);
    }

    @ModelAttribute("countryMap")
    private Map<String, Country> populateCountryMap() {
        return Countries.getCountries();
    }

    @ModelAttribute("languages")
    private Collection<Locale> populateLanguages(Locale lang) {
        return Languages.getLanguagesSortedByName(lang);
    }
}
