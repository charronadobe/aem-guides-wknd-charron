// From AAEM Workflow #5 | Custom Workflow Step with dialog in aem
// https://youtu.be/o1cV_xs2jGg
// Code sourced from
// https://github.com/aemgeeks1212/aemgeeks
// for dev on localhost:4502 env in ~/Documents/Demos/AEM Demos/AEM Local Instances/SDK Sandbox'

package com.adobe.aem.guides.wknd.core.workflows;

//import javax.jcr.Node;
import javax.jcr.Session;

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
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) {
        log.info("\n===========================Custom Workflow Step===========================");

        try {
            WorkflowData workflowData = workItem.getWorkflowData();

            if (workflowData.getPayloadType().equals("JCR_PATH")) {
                // String path = workflowData.getPayload().toString() + "/jcr:content";
                // Session session = workflowSession.adaptTo(Session.class);
                // Node node = (Node) session.getItem(path);

                String brand = processArguments.get("BRAND", "");
                boolean multinational = processArguments.get("MULTINATIONAL", false);
                String[] countries = processArguments.get("COUNTRIES", new String[] {});

                log.info("\n BRAND : {} , MULTINATIONAL : {} ", brand, multinational);
                for (String country : countries) {
                    log.info("\n Countries {} ", country);
                }
            }
        } catch (Exception e) {
            log.error("\nError {}", e.getMessage());
        }
    }
}
