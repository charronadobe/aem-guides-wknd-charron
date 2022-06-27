package com.adobe.aem.guides.wknd.core.config;

import org.apache.sling.caconfig.annotation.Configuration;
import org.apache.sling.caconfig.annotation.Property;

@Configuration(label = "Workflow Participant Choose CA Configuration")
public @interface CaConfigParticipantStepChooserConfig {
    @Property(label = "Approver Group")
    String approverGroup() default "admin";
}
