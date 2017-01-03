/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

function init() {
    var viewMode = $('#mode').val() === 'view'; //$('#map-canvas').length !== 0;

    // enable the google map code only on view mode
    if (viewMode) {
        var locationPanelLink = $('a[href="#locationPanel"]');
        var locationPanel = $('#locationPanel');
        var restaurantLatLng = new google.maps.LatLng($('#location\\.latitude').val(), $('#location\\.longitude').val());
        var map = null;
        var mapOptions = {
            center: restaurantLatLng,
            zoom: 16
        };
        var restaurantMarker = new google.maps.Marker({
            position: restaurantLatLng,
            title:'Your restaurant'
        });


        locationPanelLink.click(function() {
            if (!map) {
                map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            }
            restaurantMarker.setMap(map);
            // timeout needed for the location panel to appear (otherwise map is not resized properly)
            setTimeout(function() {
                google.maps.event.trigger(map, 'resize');
            }, 100);
        });
    } else { // else (edit mode) enable the geocoding code
        geomenum.enableGeocoding(
            'restaurantForm',
            'location\\.countryCode',
            'location\\.address',
            'location\\.postalCode',
            'location\\.city',
            'location\\.latitude',
            'location\\.longitude',
            'location\\.formattedAddress',
            'saveButton');
    }

    // add language
    if (!viewMode) {
        var addLanguageSubmitButton = $('#addLanguageSubmitButton');
        addLanguageSubmitButton.click(function() {
            var addedLanguage = $('input[name="addedLanguage"]');
            addedLanguage.prop('disabled', false);
            addedLanguage.val($('#tempAddedLanguage').val());
            // submit the form
            $('#saveButton').click();
        });
    }

    // remove language
    if (!viewMode) {
        var removeLanguageSubmitButton = $('#removeLanguageSubmitButton');
        removeLanguageSubmitButton.click(function() {
            var removedLanguage = $('input[name="removedLanguage"]');
            removedLanguage.prop('disabled', false);
            removedLanguage.val($('#tempRemovedLanguage').val());
            // submit the form
            $('#saveButton').click();
        });
    }

    // phone number
    var internationalDialingCodeSelect = $('#internationalDialingCountryCode');
    var localPhoneNumberInput = $('#localPhoneNumber');

    internationalDialingCodeSelect.change(function() {
        var phoneNumberMask = geomenum.phoneNumberMasks[internationalDialingCodeSelect.val()];
        if (phoneNumberMask) {
            localPhoneNumberInput.mask(phoneNumberMask);
        } else {
            localPhoneNumberInput.unmask();
        }
    });

    if (!viewMode) {
        localPhoneNumberInput.closest('form').submit(function() {
            var phoneNumberMask = geomenum.phoneNumberMasks[internationalDialingCodeSelect.val()];
            // in case of mask, only post the real phone number (without the mask)
            if(phoneNumberMask) {
                localPhoneNumberInput.val(localPhoneNumberInput.mask());
            }
        });
    }

    // event triggering
    if (internationalDialingCodeSelect) {
        internationalDialingCodeSelect.change();
    }

    // image retrieval
    if (viewMode) {
        //geomenum.retrieveImage($('#imageURL').val(), 'imageContainer');
        geomenum.displayImage();
    }
}

window.onload = init;