// From the AEM Geeks tutorials
// https://www.youtube.com/watch?v=FJe6Iu-v_ug

package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.day.cq.commons.jcr.JcrConstants;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "wknd/components/page", selectors = { "page", "render" }, extensions = {
        "txt", "xml" })

public class ResourceTypeGetServlet extends SlingSafeMethodsServlet {
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
}
