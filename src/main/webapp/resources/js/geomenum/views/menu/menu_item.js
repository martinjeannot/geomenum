/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

function init() {
    var isViewMode = $('#mode').val() === 'view';

    // image retrieval
    if (isViewMode) {
        //geomenum.retrieveImage($('#imageURL').val(), 'imageContainer');
        geomenum.displayImage();
    }
}

window.onload = init;