package com.university.udcp.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.university.udcp.core.testcontext.AppAemContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(AemContextExtension.class)
class UpcomingEventsModelImplTest {

    private final AemContext context = AppAemContext.newAemContext();

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(UpcomingEventsModelImpl.class);
    }

    @Test
    void returnsAllConfiguredEventCards() {
        context.create().resource("/content/events-component/events/item0",
            "eventTitle", "Annual Tech Fest",
            "eventCategory", "Conference",
            "eventDate", "December 10, 2026",
            "eventTime", "10:00 AM - 4:00 PM",
            "venue", "Main Auditorium",
            "description", "A university-wide technology symposium.",
            "registrationUrl", "https://example.edu/register");
        context.create().resource("/content/events-component/events/item1",
            "eventTitle", "National Science Symposium");

        UpcomingEventsModel model = context.resourceResolver()
            .getResource("/content/events-component")
            .adaptTo(UpcomingEventsModel.class);

        assertNotNull(model);
        assertEquals(2, model.getEvents().size());
        assertEquals("Annual Tech Fest", model.getEvents().get(0).getTitle());
        assertEquals("Conference", model.getEvents().get(0).getCategory());
        assertEquals("Main Auditorium", model.getEvents().get(0).getVenue());
        assertEquals("National Science Symposium", model.getEvents().get(1).getTitle());
    }
}
