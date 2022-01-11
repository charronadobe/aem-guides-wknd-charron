// From AEM Workflow #4 | Custom Workflow Process in aem
// https://youtu.be/1oJytJAvWdM
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

import javax.jcr.Node;
import javax.jcr.Session;
import java.util.Iterator;
import java.util.Set;

@Component(service = WorkflowProcess.class, immediate = true, property = {
        "process.label" + " = Custom Workflow Process",
        Constants.SERVICE_VENDOR + "=sandbox/workflow-servlet on Sandbox",
        Constants.SERVICE_DESCRIPTION + " = Custom geeks workflow step"
})

public class CustomWorkflowProcess implements WorkflowProcess {
    private static final Logger log = LoggerFactory.getLogger(CustomWorkflowProcess.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) {
        log.info("\n ================================================================================= ");
        try {
            WorkflowData workflowData = workItem.getWorkflowData();

            if (workflowData.getPayloadType().equals("JCR_PATH")) {
                String path = workflowData.getPayload().toString() + "/jcr:content";
                Session session = workflowSession.adaptTo(Session.class);
                Node node = (Node) session.getItem(path);

                String[] processArgs = processArguments.get("PROCESS_ARGS", "string").toString().split(",");

                MetaDataMap wfd = workItem.getWorkflow().getWorkflowData().getMetaDataMap();

                // Here is where the args are pulled apart from the datamap and written to JCR
                for (String wfArgs : processArgs) {
                    String[] args = wfArgs.split(":");
                    String prop = args[0];
                    String value = args[1];
                    if (node != null) {
                        wfd.put(prop, value);
                        node.setProperty(prop, value);
                    }
                }

                // Functionally, this does nothing; it just logs workflow metadata (NOT args)
                Set<String> keyset = wfd.keySet();
                Iterator<String> i = keyset.iterator();
                while (i.hasNext()) {
                    String key = i.next();
                    log.info("\n  ITEM  key - {} , value - {}", key, wfd.get(key));
                }
            }
        } catch (Exception e) {

        }
    }
}
