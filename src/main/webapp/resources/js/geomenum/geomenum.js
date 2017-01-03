/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

(function(geomenum, $, undefined) {

    //~ Image Support ==================================================================================================

    // violates cross-origin browser policy, see function displayImage for the workaround
    var doRetrieveImage = function(imageURL, imageContainerId) {
        if (imageURL) {
            var request = new XMLHttpRequest();
            request.open("GET", imageURL);
            request.onload = function() {
                var imageContainer = $('#' + imageContainerId);
                var image = new Image();
                image.alt = "image";
                /* TODO
                 * The following can be improved by directly retrieving the content of the ajax call (i.e the image)
                 * from the request instead of making another call but that would imply going through encoding issues
                 * that I'm not felling like dealing with at the moment... Since we're back-office-side, we can assume a
                 * solid internet connexion hence the second call.
                 */
                if (request.status == 200) {
                    image.src = imageURL;
                    image.crossOrigin = "Anonymous";
                } else {
                    image.src = "/img/image_not_found.jpeg";
                }
                imageContainer.append(image);
            };
            request.send();
        }
    };

    geomenum.retrieveImage = function(imageURL, imageContainerId) {
        return doRetrieveImage(imageURL, imageContainerId);
    };

    /**
     * This function assumes the following :
     * - a 'customImage' img element potentially containing a custom image
     * - a 'defaultImage' img element containing the default image
     * - a 'hasImage' field containing a boolean set to true if customImage is populated, false otherwise
     */
    var doDisplayImage = function() {
        var customImage = $('#customImage');
        var defaultImage = $('#defaultImage');
        var hasImage = $('#hasImage').val() === 'true';

        if (hasImage && customImage.length) {
            customImage.show();
        } else {
            defaultImage.show();
        }
    };

    geomenum.displayImage = function() {
        return doDisplayImage();
    };

    //~ Geocoding ======================================================================================================

    var doEnableGeocoding = function(
        formName,
        countryCodeFieldId,
        addressFieldId,
        postalCodeFieldId,
        cityFieldId,
        latitudeFieldId,
        longitudeFieldId,
        formattedAddressFieldId,
        formSubmitButtonId) {

        var geocoder = new google.maps.Geocoder();
        var restaurantCountry = $('#' + countryCodeFieldId);
        var restaurantAddress = $('#' + addressFieldId);
        var restaurantPostalCode = $('#' + postalCodeFieldId);
        var restaurantCity = $('#' + cityFieldId);
        var latitude = $('#' + latitudeFieldId);
        var longitude = $('#' + longitudeFieldId);
        var formattedAddress = $('#' + formattedAddressFieldId);

        function resetRestaurantLocation() {
            latitude.val('');
            longitude.val('');
            formattedAddress.val('');
        }

        restaurantCountry.change(resetRestaurantLocation);
        restaurantAddress.change(resetRestaurantLocation);
        restaurantCity.change(resetRestaurantLocation);

        restaurantPostalCode.blur(function() {
            resetRestaurantLocation();
            var address = $(this).val() + ' ' + restaurantCountry.find('option:selected').text();
            geocoder.geocode({'address': address}, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    restaurantCity.find('option').remove(); // clear options
                    results.forEach(function(result) {
                        result.address_components.forEach(function(addressComponent) {
                            addressComponent.types.forEach(function(type) {
                                if(type == 'locality') {
                                    restaurantCity.append(new Option(addressComponent.long_name, addressComponent.long_name));
                                }
                            });
                        });
                    });
                    // remove duplicate
                    var optionsValue = [];
                    restaurantCity.find('option').each(function() {
                        if($.inArray(this.value, optionsValue) != -1) $(this).remove();
                        optionsValue.push(this.value);
                    });
                }
            });
        });

        $('#' + formSubmitButtonId).click(function() {
            if(restaurantCountry.val() && restaurantAddress.val() && restaurantPostalCode.val() && restaurantCity.val()) {
                var address = restaurantAddress.val() + ' '
                    + restaurantPostalCode.val() + ' '
                    + restaurantCity.find('option:selected').text() + ' '
                    + restaurantCountry.find('option:selected').text();
                geocoder.geocode({'address': address}, function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        if(results.length === 1) {
                            var result = results[0];
                            latitude.val(result.geometry.location.lat());
                            longitude.val(result.geometry.location.lng());
                            formattedAddress.val(result.formatted_address);
                            // submit the form (with fixed data-invalid)
                            $('form[name=' + formName + ']').submit();
                        } else {
                            $('#geocodingErrorModal-precision').foundation('reveal', 'open');
                        }
                    } else if(status == google.maps.GeocoderStatus.ZERO_RESULTS) {
                        $('#geocodingErrorModal-precision').foundation('reveal', 'open');
                    } else {
                        $('#geocodingErrorModal-generic').foundation('reveal', 'open');
                    }
                });
            }
        });

        // in case of page reload
        if(restaurantPostalCode.val()) {
            restaurantPostalCode.blur(); // fires event
        }
    };

    geomenum.enableGeocoding = function(formName,
                                       countryCodeFieldId,
                                       addressFieldId,
                                       postalCodeFieldId,
                                       cityFieldId,
                                       latitudeFieldId,
                                       longitudeFieldId,
                                       formattedAddressFieldId,
                                       formSubmitButtonId) {
        return doEnableGeocoding(formName,
            countryCodeFieldId,
            addressFieldId,
            postalCodeFieldId,
            cityFieldId,
            latitudeFieldId,
            longitudeFieldId,
            formattedAddressFieldId,
            formSubmitButtonId);
    };

    //~ Phone Number Masks =============================================================================================

    geomenum.phoneNumberMasks = {
        'AD': '',
        'AE': '',
        'AF': '',
        'AG': '',
        'AI': '',
        'AL': '',
        'AM': '',
        'AN': '',
        'AO': '',
        'AQ': '',
        'AR': '',
        'AS': '',
        'AT': '',
        'AU': '',
        'AW': '',
        'AX': '',
        'AZ': '',
        'BA': '',
        'BB': '',
        'BD': '',
        'BE': '',
        'BF': '',
        'BG': '',
        'BH': '',
        'BI': '',
        'BJ': '',
        'BL': '',
        'BM': '',
        'BN': '',
        'BO': '',
        'BQ': '',
        'BR': '',
        'BS': '',
        'BT': '',
        'BV': '',
        'BW': '',
        'BY': '',
        'BZ': '',
        'CA': '',
        'CC': '',
        'CD': '',
        'CF': '',
        'CG': '',
        'CH': '',
        'CI': '',
        'CK': '',
        'CL': '',
        'CM': '',
        'CN': '',
        'CO': '',
        'CR': '',
        'CU': '',
        'CV': '',
        'CW': '',
        'CX': '',
        'CY': '',
        'CZ': '',
        'DE': '',
        'DJ': '',
        'DK': '',
        'DM': '',
        'DO': '',
        'DZ': '',
        'EC': '',
        'EE': '',
        'EG': '',
        'EH': '',
        'ER': '',
        'ES': '',
        'ET': '',
        'FI': '',
        'FJ': '',
        'FK': '',
        'FM': '',
        'FO': '',
        'FR': '09-99-99-99-99',
        'GA': '',
        'GB': '',
        'GD': '',
        'GE': '',
        'GF': '',
        'GG': '',
        'GH': '',
        'GI': '',
        'GL': '',
        'GM': '',
        'GN': '',
        'GP': '',
        'GQ': '',
        'GR': '',
        'GS': '',
        'GT': '',
        'GU': '',
        'GW': '',
        'GY': '',
        'HK': '',
        'HM': '',
        'HN': '',
        'HR': '',
        'HT': '',
        'HU': '',
        'ID': '',
        'IE': '',
        'IL': '',
        'IM': '',
        'IN': '',
        'IO': '',
        'IQ': '',
        'IR': '',
        'IS': '',
        'IT': '',
        'JE': '',
        'JM': '',
        'JO': '',
        'JP': '',
        'KE': '',
        'KG': '',
        'KH': '',
        'KI': '',
        'KM': '',
        'KN': '',
        'KP': '',
        'KR': '',
        'KW': '',
        'KY': '',
        'KZ': '',
        'LA': '',
        'LB': '',
        'LC': '',
        'LI': '',
        'LK': '',
        'LR': '',
        'LS': '',
        'LT': '',
        'LU': '',
        'LV': '',
        'LY': '',
        'MA': '',
        'MC': '',
        'MD': '',
        'ME': '',
        'MF': '',
        'MG': '',
        'MH': '',
        'MK': '',
        'ML': '',
        'MM': '',
        'MN': '',
        'MO': '',
        'MP': '',
        'MQ': '',
        'MR': '',
        'MS': '',
        'MT': '',
        'MU': '',
        'MV': '',
        'MW': '',
        'MX': '',
        'MY': '',
        'MZ': '',
        'NA': '',
        'NC': '',
        'NE': '',
        'NF': '',
        'NG': '',
        'NI': '',
        'NL': '',
        'NO': '',
        'NP': '',
        'NR': '',
        'NU': '',
        'NZ': '',
        'OM': '',
        'PA': '',
        'PE': '',
        'PF': '',
        'PG': '',
        'PH': '',
        'PK': '',
        'PL': '',
        'PM': '',
        'PN': '',
        'PR': '',
        'PS': '',
        'PT': '',
        'PW': '',
        'PY': '',
        'QA': '',
        'RE': '',
        'RO': '',
        'RS': '',
        'RU': '',
        'RW': '',
        'SA': '',
        'SB': '',
        'SC': '',
        'SD': '',
        'SE': '',
        'SG': '',
        'SH': '',
        'SI': '',
        'SJ': '',
        'SK': '',
        'SL': '',
        'SM': '',
        'SN': '',
        'SO': '',
        'SR': '',
        'ST': '',
        'SV': '',
        'SX': '',
        'SY': '',
        'SZ': '',
        'TC': '',
        'TD': '',
        'TF': '',
        'TG': '',
        'TH': '',
        'TJ': '',
        'TK': '',
        'TL': '',
        'TM': '',
        'TN': '',
        'TO': '',
        'TR': '',
        'TT': '',
        'TV': '',
        'TW': '',
        'TZ': '',
        'UA': '',
        'UG': '',
        'UM': '',
        'US': '',
        'UY': '',
        'UZ': '',
        'VA': '',
        'VC': '',
        'VE': '',
        'VG': '',
        'VI': '',
        'VN': '',
        'VU': '',
        'WF': '',
        'WS': '',
        'YE': '',
        'YT': '',
        'ZA': '',
        'ZM': '',
        'ZW': ''
    };

}(window.geomenum = window.geomenum || {}, jQuery));