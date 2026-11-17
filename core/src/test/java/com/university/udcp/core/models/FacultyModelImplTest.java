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
class FacultyModelImplTest {

    private final AemContext context = AppAemContext.newAemContext();

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(FacultyModelImpl.class);
    }

    @Test
    void initialsUseFirstTwoNameParts() {
        context.create().resource("/content/faculty",
            "facultyName", "Dr. Ananya Sharma");

        FacultyModel faculty = context.resourceResolver()
            .getResource("/content/faculty")
            .adaptTo(FacultyModel.class);

        assertNotNull(faculty);
        assertEquals("DA", faculty.getInitials());
    }

    @Test
    void initialsHandleSingleAndMissingNames() {
        context.create().resource("/content/single-faculty",
            "facultyName", "Meera");
        context.create().resource("/content/unnamed-faculty");

        FacultyModel singleNameFaculty = context.resourceResolver()
            .getResource("/content/single-faculty")
            .adaptTo(FacultyModel.class);
        FacultyModel unnamedFaculty = context.resourceResolver()
            .getResource("/content/unnamed-faculty")
            .adaptTo(FacultyModel.class);

        assertNotNull(singleNameFaculty);
        assertNotNull(unnamedFaculty);
        assertEquals("M", singleNameFaculty.getInitials());
        assertEquals("", unnamedFaculty.getInitials());
    }
}
