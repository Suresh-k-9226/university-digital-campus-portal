package com.university.udcp.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.university.udcp.core.services.EventsConfigurationService;

@Model(
    adaptables = Resource.class,
    adapters = UpcomingEventsModel.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class UpcomingEventsModelImpl implements UpcomingEventsModel {

    @ValueMapValue
    private String eventsRootPath;

    @ValueMapValue
    private Integer maxEvents;

    @ChildResource(name = "events")
    private List<Resource> eventResources;

    @OSGiService
    private EventsConfigurationService eventsConfigurationService;

    @SlingObject
    private ResourceResolver resourceResolver;

    private List<UpcomingEvent> events = Collections.emptyList();

    @PostConstruct
    protected void init() {

        List<UpcomingEvent> configuredEvents = getConfiguredEvents();
        if (!configuredEvents.isEmpty()) {
            events = Collections.unmodifiableList(configuredEvents);
            return;
        }

        String rootPath = eventsRootPath;
        int eventLimit = maxEvents != null ? maxEvents : 5;

        if (eventsConfigurationService != null) {

            if (rootPath == null || rootPath.trim().isEmpty()) {
                rootPath = eventsConfigurationService.getEventsRootPath();
            }

            if (maxEvents == null) {
                eventLimit = eventsConfigurationService.getMaxEvents();
            }
        }

        if (rootPath == null || rootPath.trim().isEmpty()
                || resourceResolver == null || eventLimit <= 0) {
            return;
        }

        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

        if (pageManager == null) {
            return;
        }

        Page rootPage = pageManager.getPage(rootPath);

        if (rootPage == null) {
            return;
        }

        List<UpcomingEvent> eventPages = new ArrayList<>();
        Iterator<Page> childPages = rootPage.listChildren();

        while (childPages.hasNext() && eventPages.size() < eventLimit) {

            Page eventPage = childPages.next();

            if (eventPage != null) {
                ValueMap properties = eventPage.getProperties();
                eventPages.add(new UpcomingEvent(
                    eventPage.getTitle(),
                    properties.get("eventCategory", String.class),
                    properties.get("eventDate", String.class),
                    properties.get("eventTime", String.class),
                    properties.get("venue", String.class),
                    eventPage.getDescription(),
                    properties.get("registrationUrl", String.class)
                ));
            }
        }

        events = Collections.unmodifiableList(eventPages);
    }

    @Override
    public List<UpcomingEvent> getEvents() {
        return events;
    }

    private List<UpcomingEvent> getConfiguredEvents() {
        if (eventResources == null || eventResources.isEmpty()) {
            return Collections.emptyList();
        }

        List<UpcomingEvent> configuredEvents = new ArrayList<>();
        for (Resource eventResource : eventResources) {
            if (eventResource == null) {
                continue;
            }

            ValueMap properties = eventResource.getValueMap();
            String title = properties.get("eventTitle", String.class);
            if (title == null || title.trim().isEmpty()) {
                continue;
            }

            configuredEvents.add(new UpcomingEvent(
                title,
                properties.get("eventCategory", String.class),
                properties.get("eventDate", String.class),
                properties.get("eventTime", String.class),
                properties.get("venue", String.class),
                properties.get("description", String.class),
                properties.get("registrationUrl", String.class)
            ));
        }

        return configuredEvents;
    }
}
