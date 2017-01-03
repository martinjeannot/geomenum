/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.config;

/**
 * Constants representing all the different testing levels used in the application,
 * each of them represents a corresponding TestNG group.<br/>
 * <b>Every</b> test in this application <b>must</b> identify itself to one (and only one) of those test levels.
 * It can additionally identify itself to any other group.
 */
public final class TestingLevels {

    private TestingLevels() {}

    // The 3 test levels below are advocated by the SWEBOK

    /**
     * "Unit testing, also known as component testing, refers to tests that verify the functionality of a specific section
     * of code, usually at the function level. In an object-oriented environment, this is usually at the class level, and
     * the minimal unit tests include the constructors and destructors.", Wikipedia
     */
    public static final String UNIT = "unit";

    /**
     * "Integration testing is any type of software testing that seeks to verify the interfaces between components against
     * a software design. Software components may be integrated in an iterative way or all together ("big bang"). Normally
     * the former is considered a better practice since it allows interface issues to be located more quickly and fixed.",
     * Wikipedia
     */
    public static final String INTEGRATION = "integration";

    /**
     * "System testing tests a completely integrated system to verify that it meets its requirements.", Wikipedia
     */
    public static final String SYSTEM = "system";
}
