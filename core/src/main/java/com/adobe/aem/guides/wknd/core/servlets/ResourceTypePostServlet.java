// From the AEM Geeks tutorial
// https://www.youtube.com/watch?v=FJe6Iu-v_ug

package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.day.cq.commons.jcr.JcrConstants;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class)
@SlingServletResourceTypes(methods = { HttpConstants.METHOD_POST,
        HttpConstants.METHOD_GET }, resourceTypes = "wknd/components/page", selectors = { "page",
                "render" }, extensions = { "txt", "xml" })

public class ResourceTypePostServlet extends SlingAllMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ResourceTypeGetServlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
            throws ServletException, IOException {
        final Resource resource = req.getResource();

        // The next line outpus Page Title = WKND Adventures and Travel when you go to
        // http://localhost:4502/content/wknd/language-masters/en/jcr:content.page.txt
        // (or you can also use .render.xml or .render.txt or .page.xml)
        resp.setContentType("text/plain");
        resp.getWriter().write("Page Title = " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
    }

    @Override
    protected void doPost(SlingHttpServletRequest req, SlingHttpServletResponse resp)
            throws ServletException, IOException {
        try {
            LOG.info("\n----------------------STARTED POST----------------------");

            // You'd need to populate a list; AEM Geek does it with a custom form component;
            // see vid
            // You can fire this by taking the doGet() request above and firing it as a POST
            // in Postman
            List<RequestParameter> requestParameterList = req.getRequestParameterList();

            for (RequestParameter requestParameter : requestParameterList) {
                LOG.info("\n==PARAMETERS== {} : {}", requestParameter.getName(), requestParameter.getString());
            }
        } catch (Exception e) {
            LOG.info("Error in request {}", e.getMessage());
        }

        resp.getWriter().write("DATA SUBMITTED");
    }
}
