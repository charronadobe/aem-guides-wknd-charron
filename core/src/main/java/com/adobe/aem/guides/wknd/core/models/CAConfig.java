package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.caconfig.annotation.Configuration;
import org.apache.sling.caconfig.annotation.Property;

@Configuration(label = "CAConfig", description = "Context Aware Config for demo")

public @interface CAConfig {
    @Property(label = "Country Site", description = "Country")
    String siteCountry();

    @Property(label = "Site Locale", description = "Locale")
    String siteLocale();

    @Property(label = "Site Section", description = "Section")
    String siteSection();
}
