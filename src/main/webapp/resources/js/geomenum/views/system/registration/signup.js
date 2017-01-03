/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

function init() {
    geomenum.enableGeocoding(
        'registrationForm',
        'restaurantCountryCode',
        'restaurantAddress',
        'restaurantPostalCode',
        'restaurantCity',
        'latitude',
        'longitude',
        'formattedAddress',
        'signUpButton');
}

window.onload = init;