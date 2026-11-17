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
class FacultyProfilesModelImplTest {

    private final AemContext context = AppAemContext.newAemContext();

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(FacultyProfilesModelImpl.class, FacultyModelImpl.class);
    }

    @Test
    void returnsEveryConfiguredFacultyMember() {
        context.create().resource("/content/faculty-list/facultyMembers/item0",
            "facultyName", "Dr. Ananya Sharma");
        context.create().resource("/content/faculty-list/facultyMembers/item1",
            "facultyName", "Dr. Meera Reddy");

        FacultyProfilesModel model = context.resourceResolver()
            .getResource("/content/faculty-list")
            .adaptTo(FacultyProfilesModel.class);

        assertNotNull(model);
        assertEquals(2, model.getFacultyMembers().size());
        assertEquals("Dr. Ananya Sharma", model.getFacultyMembers().get(0).getFacultyName());
        assertEquals("Dr. Meera Reddy", model.getFacultyMembers().get(1).getFacultyName());
    }

    @Test
    void fallsBackToExistingSingleProfileProperties() {
        context.create().resource("/content/legacy-faculty",
            "facultyName", "Dr. Ananya Sharma");

        FacultyProfilesModel model = context.resourceResolver()
            .getResource("/content/legacy-faculty")
            .adaptTo(FacultyProfilesModel.class);

        assertNotNull(model);
        assertEquals(1, model.getFacultyMembers().size());
        assertEquals("Dr. Ananya Sharma", model.getFacultyMembers().get(0).getFacultyName());
    }
}
