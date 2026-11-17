package com.university.udcp.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(
    adaptables = Resource.class,
    adapters = CourseHighlightsModel.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CourseHighlightsModelImpl implements CourseHighlightsModel {

    @ChildResource(name = "courses")
    private List<Resource> courseResources;

    @Override
    public List<CourseModel> getCourses() {

        if (courseResources == null || courseResources.isEmpty()) {
            return Collections.emptyList();
        }

        List<CourseModel> courses = new ArrayList<>();

        for (Resource courseResource : courseResources) {
            CourseModel course = courseResource.adaptTo(CourseModel.class);

            if (course != null && course.getCourseName() != null
                    && !course.getCourseName().trim().isEmpty()) {
                courses.add(course);
            }
        }

        return Collections.unmodifiableList(courses);
    }
}
