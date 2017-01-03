/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common.validation.constraintvalidators;

import com.geomenum.common.validation.constraints.NoDisposableEmail;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.testng.annotations.Test;

import javax.validation.ConstraintValidator;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.geomenum.config.TestingLevels.UNIT;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test(groups = {UNIT})
public class NoDisposableEmailValidatorTests {

    private final ConstraintValidator<NoDisposableEmail, CharSequence> validator = new NoDisposableEmailValidator();

    public void validEmail() {
        assertTrue(validator.isValid("geomenum@gmail.com", null));
        assertTrue(validator.isValid("contact@geomenum.com", null));
    }

    public void nullIsValid() {
        assertTrue(validator.isValid(null, null));
    }

    public void blankIsValid() {
        assertTrue(validator.isValid("", null));
    }

    public void invalidEmail() {
        assertFalse(validator.isValid("h4ck3r@zoemail.org", null));
    }

    public void caseInsensitivity() {
        assertFalse(validator.isValid("h4ck3r@YOPmAIl.com", null));
    }

    //~ Adding domain names ============================================================================================

    /**
     * Put your DEA domains to add in a "DEADomainListAddition.txt" next to the existing "DEADomainList.txt".<br/>
     * Copy/paste the result...
     */
    @Test(enabled = false)
    public void addDEADomains() {
        List<String> DEADomainList;
        try {
            DEADomainList = Files.readAllLines(
                    Paths.get(NoDisposableEmailValidator.class.getClassLoader().getResource("validation/DEADomainList.txt").getPath()),
                    Charset.defaultCharset());
            // add new DEA domains
            DEADomainList.addAll(
                    Files.readAllLines(
                            Paths.get(NoDisposableEmailValidator.class.getClassLoader().getResource("validation/DEADomainListAddition.txt").getPath()),
                            Charset.defaultCharset()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Set<String> DEADomainSet = Sets.newHashSet(); // remove duplicates
        for(String DEADomain : DEADomainList) {
            DEADomainSet.add(DEADomain.toLowerCase()); // domain names are not case sensitive
        }
        DEADomainList = Lists.newArrayList(DEADomainSet);
        Collections.sort(DEADomainList);
        for(String DEADomain : DEADomainList) {
            System.out.println(DEADomain);
        }
    }
}
