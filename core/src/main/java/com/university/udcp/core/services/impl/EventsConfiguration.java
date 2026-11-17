package com.university.udcp.core.services.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
    name = "UDCP Upcoming Events Configuration",
    description = "Configuration for retrieving upcoming university events."
)
public @interface EventsConfiguration {

    @AttributeDefinition(
        name = "Events Root Path",
        description = "Repository path containing university event pages."
    )
    String eventsRootPath() default "/content/udcp/us/en/events";

    @AttributeDefinition(
        name = "Maximum Events",
        description = "Maximum number of events displayed."
    )
    int maxEvents() default 5;
}
