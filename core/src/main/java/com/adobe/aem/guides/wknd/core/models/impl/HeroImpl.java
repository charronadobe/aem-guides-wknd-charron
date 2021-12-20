package com.adobe.aem.guides.wknd.core.models.impl;

import javax.annotation.Resource;
import javax.inject.Inject;

import com.adobe.aem.guides.wknd.core.models.Hero;

import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;

@Model(adaptables = Resource.class, adapters = Hero.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeroImpl implements Hero {
    @Inject
    @Required
    @Default(values = "Hero Header")
    String header;

    @Inject
    @Default(values = "Descriptive tagline")
    String tagline;

    @Inject
    @Default(values = "false")
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
