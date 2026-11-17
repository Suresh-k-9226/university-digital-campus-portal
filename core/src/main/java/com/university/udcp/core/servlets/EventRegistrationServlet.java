
package com.university.udcp.core.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

@Component(service = { Servlet.class })
@SlingServletPaths(value = "/bin/udcp/event-registration")
@ServiceDescription("UDCP Event Registration Servlet")
public class EventRegistrationServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    private static final String STORAGE_PATH =
            "/var/udcp/event-registrations";

    private static final String SUBSERVICE =
            "udcp-registration-service";

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+?[0-9]{10,15}$");

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    protected void doPost(
            SlingHttpServletRequest request,
            SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String eventId = getParameter(request, "eventId");
        String fullName = getParameter(request, "fullName");
        String email = getParameter(request, "email");
        String phone = getParameter(request, "phone");

        if (eventId.isEmpty()
                || eventId.length() > 200
                || fullName.length() < 2
                || fullName.length() > 100
                || !EMAIL_PATTERN.matcher(email).matches()
                || !PHONE_PATTERN.matcher(phone).matches()) {

            response.setStatus(
                    SlingHttpServletResponse.SC_BAD_REQUEST);

            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Please provide valid registration details.\"}");
            return;
        }

        Map<String, Object> authInfo = Collections.singletonMap(
                ResourceResolverFactory.SUBSERVICE,
                SUBSERVICE
        );

        try (ResourceResolver resolver =
                     resolverFactory.getServiceResourceResolver(authInfo)) {

            Resource storage = resolver.getResource(STORAGE_PATH);

            if (storage == null) {
                response.setStatus(
                        SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);

                response.getWriter().write(
                        "{\"success\":false,\"message\":\"Registration storage is unavailable.\"}");
                return;
            }

            String registrationId = UUID.randomUUID().toString();

            Map<String, Object> properties = new HashMap<>();

            properties.put("jcr:primaryType", "nt:unstructured");
            properties.put("eventId", eventId);
            properties.put("fullName", fullName);
            properties.put("email", email);
            properties.put("phone", phone);
            properties.put("submittedAt", Calendar.getInstance());

            resolver.create(
                    storage,
                    registrationId,
                    properties
            );

            resolver.commit();

            response.setStatus(
                    SlingHttpServletResponse.SC_CREATED);

            response.getWriter().write(
                    "{\"success\":true,\"registrationId\":\""
                            + registrationId
                            + "\",\"message\":\"Registration successful.\"}");

        } catch (LoginException | PersistenceException exception) {

            response.setStatus(
                    SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Unable to save registration.\"}");
        }
    }

    private String getParameter(
            SlingHttpServletRequest request,
            String parameterName) {

        String value = request.getParameter(parameterName);

        return value == null ? "" : value.trim();
    }
}
