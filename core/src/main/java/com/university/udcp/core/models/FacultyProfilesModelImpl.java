package com.university.udcp.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(
    adaptables = Resource.class,
    adapters = FacultyProfilesModel.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class FacultyProfilesModelImpl implements FacultyProfilesModel {

    @ChildResource(name = "facultyMembers")
    private List<Resource> facultyMemberResources;

    @Self
    private Resource resource;

    @Override
    public List<FacultyModel> getFacultyMembers() {
        List<FacultyModel> facultyMembers = new ArrayList<>();

        if (facultyMemberResources != null) {
            for (Resource facultyResource : facultyMemberResources) {
                addFacultyMember(facultyMembers, facultyResource);
            }
        }

        if (facultyMembers.isEmpty() && resource != null) {
            addFacultyMember(facultyMembers, resource);
        }

        return Collections.unmodifiableList(facultyMembers);
    }

    private void addFacultyMember(List<FacultyModel> facultyMembers, Resource facultyResource) {
        if (facultyResource == null) {
            return;
        }

        FacultyModel faculty = facultyResource.adaptTo(FacultyModel.class);
        if (faculty != null && faculty.getFacultyName() != null
                && !faculty.getFacultyName().trim().isEmpty()) {
            facultyMembers.add(faculty);
        }
    }
}
