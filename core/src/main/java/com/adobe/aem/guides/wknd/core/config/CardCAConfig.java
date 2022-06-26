package com.adobe.aem.guides.wknd.core.config;

import org.apache.sling.caconfig.annotation.Configuration;
import org.apache.sling.caconfig.annotation.Property;

@Configuration(label = "CAConfig", description = "Context Aware Config for demo")

public @interface CardCAConfig {
    @Property(label = "Country Site", description = "Country")
    String siteCountry();

    @Property(label = "Site Locale", description = "Locale")
    String siteLocale();

    @Property(label = "Site Section", description = "Section")
    String siteSection();

    @Property(label = "Site Admin", description = "Admin")
    String siteAdmin();
}
