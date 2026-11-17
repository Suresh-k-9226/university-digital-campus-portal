package com.university.udcp.core.models;

import java.util.Locale;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
    adaptables = Resource.class,
    adapters = FacultyModel.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class FacultyModelImpl implements FacultyModel {

    @ValueMapValue
    private String facultyName;

    @ValueMapValue
    private String designation;

    @ValueMapValue
    private String school;

    @ValueMapValue
    private String qualification;

    @ValueMapValue
    private String experience;

    @ValueMapValue
    private String facultyPhoto;

    @ValueMapValue
    private String biography;

    @ValueMapValue
    private String email;

    @Override
    public String getFacultyName() {
        return facultyName;
    }

    @Override
    public String getInitials() {
        if (facultyName == null || facultyName.trim().isEmpty()) {
            return "";
        }

        String[] nameParts = facultyName.trim().split("\\s+");
        String initials = nameParts[0].substring(0, 1);

        if (nameParts.length > 1) {
            initials += nameParts[1].substring(0, 1);
        }

        return initials.toUpperCase(Locale.ROOT);
    }

    @Override
    public String getDesignation() {
        return designation;
    }

    @Override
    public String getSchool() {
        return school;
    }

    @Override
    public String getQualification() {
        return qualification;
    }

    @Override
    public String getExperience() {
        return experience;
    }

    @Override
    public String getFacultyPhoto() {
        return facultyPhoto;
    }

    @Override
    public String getBiography() {
        return biography;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
