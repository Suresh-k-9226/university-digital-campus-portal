package com.university.udcp.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.university.udcp.core.services.EventsConfigurationService;

@Component(service = EventsConfigurationService.class)
@Designate(ocd = EventsConfiguration.class)
public class EventsConfigurationServiceImpl implements EventsConfigurationService {

    private String eventsRootPath;
    private int maxEvents;

    @Activate
    protected void activate(EventsConfiguration configuration) {
        this.eventsRootPath = configuration.eventsRootPath();
        this.maxEvents = configuration.maxEvents();
    }

    @Override
    public String getEventsRootPath() {
        return eventsRootPath;
    }

    @Override
    public int getMaxEvents() {
        return maxEvents;
    }
}
