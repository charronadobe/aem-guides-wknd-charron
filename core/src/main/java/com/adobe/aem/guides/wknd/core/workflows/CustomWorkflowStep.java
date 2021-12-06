// From AAEM Workflow #5 | Custom Workflow Step with dialog in aem
// https://youtu.be/o1cV_xs2jGg
// Code sourced from
// https://github.com/aemgeeks1212/aemgeeks
// for dev on localhost:4502 env in ~/Documents/Demos/AEM Demos/AEM Local Instances/SDK Sandbox'

package com.adobe.aem.guides.wknd.core.workflows;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = WorkflowProcess.class, property = {
        "process.label" + " = Custom Workflow Step",
        Constants.SERVICE_VENDOR + "=sandbox/workflow-servlet on Sandbox",
        Constants.SERVICE_DESCRIPTION + " = Custom geeks workflow step"
})

public class CustomWorkflowStep implements WorkflowProcess {
    private static final Logger log = LoggerFactory.getLogger(CustomWorkflowProcess.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) {
        log.info("\n===========================Custom Workflow Step===========================");

        try {
            WorkflowData workflowData = workItem.getWorkflowData();

            if (workflowData.getPayloadType().equals("JCR_PATH")) {
                // TODO
            }
        } catch (Exception e) {
            log.info("\nError {}", e.getMessage());
        }
    }
}
