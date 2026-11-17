package com.university.udcp.core.models;

public class UpcomingEvent {

    private final String title;
    private final String category;
    private final String date;
    private final String time;
    private final String venue;
    private final String description;
    private final String registrationUrl;

    public UpcomingEvent(
            String title,
            String category,
            String date,
            String time,
            String venue,
            String description,
            String registrationUrl) {
        this.title = title;
        this.category = category;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.description = description;
        this.registrationUrl = registrationUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public String getDescription() {
        return description;
    }

    public String getRegistrationUrl() {
        return registrationUrl;
    }
}
