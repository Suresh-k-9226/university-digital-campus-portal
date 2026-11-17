
package com.university.udcp.core.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.university.udcp.core.services.EventsConfigurationService;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AemContextExtension.class)
class EventsConfigurationServiceImplTest {

    private final AemContext context = new AemContext();

    private EventsConfigurationService service;

    @BeforeEach
    void setUp() {
        context.registerInjectActivateService(
            new EventsConfigurationServiceImpl(),
            "eventsRootPath", "/content/udcp/us/en/events",
            "maxEvents", 5
        );

        service = context.getService(EventsConfigurationService.class);
    }

    @Test
    void serviceShouldBeRegistered() {
        org.junit.jupiter.api.Assertions.assertNotNull(service);
    }

    @Test
    void shouldReturnConfiguredEventsRootPath() {
        assertEquals(
            "/content/udcp/us/en/events",
            service.getEventsRootPath()
        );
    }

    @Test
    void shouldReturnConfiguredMaximumEvents() {
        assertEquals(5, service.getMaxEvents());
    }
}
