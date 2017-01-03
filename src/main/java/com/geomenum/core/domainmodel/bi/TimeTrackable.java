/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.core.domainmodel.bi;

import org.joda.time.LocalDateTime;

/**
 * Any time-trackable domain object can be tracked from an "historical" point of view meaning it has at least a creation
 * date and possibly a last update date and/or a deletion date.
 */
public interface TimeTrackable {

    /**
     * Set the creation date to the current date.
     */
    void setCreationDateToNow();

    /**
     * Get the creation date.
     *
     * @return a {@link LocalDateTime}
     */
    LocalDateTime getCreationDate();

    /**
     * Set the last update date to the current date.
     */
    void setLastUpdateDateToNow();

    /**
     * Get the last update date.
     *
     * @return a {@link LocalDateTime}
     */
    LocalDateTime getLastUpdateDate();

    /**
     * Set the deletion date the the current date.
     */
    void setDeletionDateToNow();

    /**
     * Get the deletion date.
     *
     * @return a {@link LocalDateTime}
     */
    LocalDateTime getDeletionDate();
}
