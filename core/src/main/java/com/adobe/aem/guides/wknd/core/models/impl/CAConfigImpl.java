package com.adobe.aem.guides.wknd.core.models.impl;

import java.lang.annotation.Annotation;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.caconfig.ConfigurationResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.adobe.aem.guides.wknd.core.config.CardCAConfig;
import com.adobe.aem.guides.wknd.core.models.CAConfig;
import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class, adapters = CAConfig.class, resourceType = CAConfigImpl.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class CAConfigImpl implements CAConfig {
    protected static final String RESOURCE_TYPE = "wknd/components/card";

    @SlingObject
    ResourceResolver resourceResolver;

    @ScriptVariable
    Page currentPage;

    @OSGiService
    ConfigurationResolver configurationResolver;

    private String siteCountry;
    private String siteLocale;
    private String siteSection;
    private String siteAdmin;

    @Override
    public String getSiteCountry() {
        return siteCountry;
    }

    @Override
    public String getSiteLocale() {
        return siteLocale;
    }

    @Override
    public String getSiteSection() {
        return siteSection;
    }

    @Override
    public String getSiteAdmin() {
        return siteAdmin;
    }

    @PostConstruct
    public void PostConstruct() {
        CardCAConfig cardCaConfig = getContextAwareConfig(currentPage.getPath(), resourceResolver);
        siteCountry = cardCaConfig.siteCountry();
        siteLocale = cardCaConfig.siteLocale();
        siteSection = cardCaConfig.siteSection();
        siteAdmin = cardCaConfig.siteAdmin();
    }

    public CardCAConfig getContextAwareConfig(String currentPage, ResourceResolver resourceResolver) {
        String currentPath = StringUtils.isNotBlank(currentPage) ? currentPage : StringUtils.EMPTY;
        Resource contentResource = resourceResolver.getResource(currentPath);

        if (contentResource != null) {
            ConfigurationBuilder configurationBuilder = contentResource.adaptTo(ConfigurationBuilder.class);

            if (configurationBuilder != null) {
                return configurationBuilder.as(CardCAConfig.class);
            }
        }
        return null;
    }
}
