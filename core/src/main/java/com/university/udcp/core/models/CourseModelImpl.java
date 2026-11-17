package com.university.udcp.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
    adaptables = Resource.class,
    adapters = CourseModel.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CourseModelImpl implements CourseModel {

    @ValueMapValue
    private String courseName;

    @ValueMapValue
    private String school;

    @ValueMapValue
    private String duration;

    @ValueMapValue
    private String fees;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String eligibility;

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public String getSchool() {
        return school;
    }

    @Override
    public String getDuration() {
        return duration;
    }

    @Override
    public String getFees() {
        return fees;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getEligibility() {
        return eligibility;
    }
}
