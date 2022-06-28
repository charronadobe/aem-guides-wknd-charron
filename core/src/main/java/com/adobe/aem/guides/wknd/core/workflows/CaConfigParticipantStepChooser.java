package com.adobe.aem.guides.wknd.core.workflows;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.config.CaConfigParticipantStepChooserConfig;
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.ParticipantStepChooser;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component()
public class CaConfigParticipantStepChooser implements ParticipantStepChooser {
    final static Logger LOG = LoggerFactory.getLogger(CaConfigParticipantStepChooser.class);
    final static String DEFAULT_APPROVER = "admin";

    @Override
    public String getParticipant(WorkItem item, WorkflowSession session, MetaDataMap map) throws WorkflowException {
        if (item.getWorkflowData().getPayloadType().equals("JCR_PATH")) {
            final String path = item.getWorkflowData().getPayload().toString();
            ResourceResolver resourceResolver = session.adaptTo(ResourceResolver.class);
            Resource contentResource = resourceResolver.getResource(path);

            if (contentResource != null) {
                CaConfigParticipantStepChooserConfig config = contentResource.adaptTo(ConfigurationBuilder.class)
                        .as(CaConfigParticipantStepChooserConfig.class);
                return config.approverGroup();
            } else {
                LOG.warn("Path {} does not exist", path);
            }
        }

        return DEFAULT_APPROVER;
    }

}
