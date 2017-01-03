/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.web.domainmodel.menu;

import com.geomenum.common.integration.Mappable;
import com.geomenum.core.domainmodel.menu.CoreMenuItem;
import com.geomenum.web.domainmodel.WebDomainModelMapper;
import com.google.common.collect.Maps;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.MessageSource;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * This class is used to initiate the creation of a new menu item. It serves as a form backing-bean for our controllers.
 */
public class NewMenuItem implements Mappable {

    @NotEmpty
    private final Map<Locale, String> localizedNames;
    @NotNull
    private Boolean enabled;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private final Currency currency;
    @NotEmpty
    private final Map<Locale, String> localizedDescriptions;

    // image upload
    private MultipartFile image;

    public NewMenuItem(Set<Locale> supportedLanguages, MessageSource messageSource, Currency currency) {
        localizedNames = Maps.newLinkedHashMap();
        localizedDescriptions = Maps.newLinkedHashMap();
        for (Locale language : supportedLanguages) {
            localizedNames.put(language,
                    messageSource.getMessage(CoreMenuItem.DEFAULT_LOCALIZED_NAME_KEY, null, language));
            localizedDescriptions.put(language,
                    messageSource.getMessage(CoreMenuItem.DEFAULT_LOCALIZED_DESCRIPTION_KEY, null, language));
        }
        enabled = true;
        amount = BigDecimal.ZERO;
        this.currency = currency;
    }

    protected NewMenuItem(Map<Locale, String> localizedNames,
                          Currency currency,
                          Map<Locale, String> localizedDescriptions) {
        this.localizedNames = localizedNames;
        this.currency = currency;
        this.localizedDescriptions = localizedDescriptions;
    }

    //~ Mappable =======================================================================================================

    @Override
    public Map<Object, Object> toMap() {
        return WebDomainModelMapper.toMap(this);
    }

    //~ Getters & Setters ==============================================================================================

    public Map<Locale, String> getLocalizedNames() {
        return localizedNames;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Map<Locale, String> getLocalizedDescriptions() {
        return localizedDescriptions;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
