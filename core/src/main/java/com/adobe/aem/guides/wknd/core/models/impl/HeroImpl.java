package com.adobe.aem.guides.wknd.core.models.impl;

import javax.annotation.Resource;
import javax.inject.Inject;

import com.adobe.aem.guides.wknd.core.models.Hero;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = SlingHttpServletRequest.class, adapters = Hero.class, resourceType = HeroImpl.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeroImpl implements Hero {
    protected static final String RESOURCE_TYPE = "wknd/components/hero";

    @ValueMapValue
    String header;

    @ValueMapValue
    String tagline;

    @ValueMapValue
    boolean isMarketing;

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public String getTagline() {
        return tagline;
    }

    @Override
    public boolean getIsMarketing() {
        return isMarketing;
    }

}
