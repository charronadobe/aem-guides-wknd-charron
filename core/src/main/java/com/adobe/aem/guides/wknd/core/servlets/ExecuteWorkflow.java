// From AEM Geeks Sling Servlet Tutorial
// https://youtu.be/VNXGF6p0zdM
// Code sourced from
// https://github.com/aemgeeks1212/aemgeeks
// for dev on localhost:4502 env in ~/Documents/Demos/AEM Demos/AEM Local Instances/SDK Sandbox'

package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.model.WorkflowModel;
import com.adobe.granite.workflow.exec.WorkflowData;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class)
@SlingServletPaths(value = { "/bin/executeworkflow", "/andy/executeworkflow" })

public class ExecuteWorkflow extends SlingSafeMethodsServlet {
    protected static final Logger LOG = LoggerFactory.getLogger(ExecuteWorkflow.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
            throws ServletException, IOException {
        String workflowModelPath = "/var/workflow/models/custom-process";
        String status = "Workflow executing";
        final ResourceResolver resourceResolver = req.getResourceResolver();
        String payload = req.getRequestParameter("page").getString();
        try {
            if (StringUtils.isNotBlank(payload)) {
                WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);
                WorkflowModel workflowModel = workflowSession.getModel(workflowModelPath);
                WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payload);
                status = workflowSession.startWorkflow(workflowModel, workflowData).getState();
            }
        } catch (Exception e) {
            LOG.error("\nError in Workflow {} ", e.getMessage());
        }
        resp.setContentType("application/json");
        resp.getWriter().write(status);
    }
}
// You can execute the above servlet with
// http://localhost:4502/bin/executeworkflow?page=/content/wknd/language-masters/en/magazine