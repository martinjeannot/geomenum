/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.validation.constraintvalidators;

import com.geomenum.common.validation.constraints.NoDisposableEmail;
import com.google.common.collect.Lists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Validator for the {@link NoDisposableEmail} constraint.
 */
public class NoDisposableEmailValidator implements ConstraintValidator<NoDisposableEmail, CharSequence> {

    private static final List<Pattern> DEADomainPatterns;

    // TODO : remove geoboot
    private static final List<String> geobootEmailAddresses;

    static {
        List<String> DEADomainList;
        try {
            DEADomainList = Files.readAllLines(
                    Paths.get(NoDisposableEmailValidator.class.getClassLoader().getResource("validation/DEADomainList.txt").getPath()),
                    Charset.defaultCharset());
        } catch (IOException e) {
            throw new Error("[FATAL ERROR] NoDisposableEmailValidator INITIALIZATION FAILED !", e); // abort mission !
        }
        DEADomainPatterns = Lists.newArrayListWithCapacity(DEADomainList.size());
        for(String DEADomain : DEADomainList) {
            DEADomainPatterns.add(Pattern.compile("\\S+?" + DEADomain, Pattern.CASE_INSENSITIVE));
        }

        // TODO : remove geoboot
        try {
            geobootEmailAddresses = Files.readAllLines(
                    Paths.get(NoDisposableEmailValidator.class.getClassLoader().getResource("validation/geobootEmails.txt").getPath()),
                    Charset.defaultCharset());
        } catch (IOException e) {
            throw new Error("[FATAL ERROR] NoDisposableEmailValidator INITIALIZATION FAILED !", e); // abort mission !
        }
    }

    @Override
    public void initialize(NoDisposableEmail constraintAnnotation) {}

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }

        // TODO : remove geoboot
        if (geobootEmailAddresses.contains(value.toString())) {
            return true;
        }

        for (Pattern DEADomainPattern : DEADomainPatterns) {
            if (DEADomainPattern.matcher(value).matches()) {
                return false;
            }
        }

        return true;
    }
}
