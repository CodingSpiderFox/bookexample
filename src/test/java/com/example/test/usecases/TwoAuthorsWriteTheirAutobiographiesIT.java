package com.example.test.usecases;

import com.example.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.shaded.org.apache.commons.lang.NotImplementedException;

@IntegrationTest
@WithMockUser
@AutoConfigureMockMvc
public class TwoAuthorsWriteTheirAutobiographiesIT {

    @Test
    void twoAuthorsAreBornAndLaterWriteTheirAutobiographiesSuccessfullyTest() {

        //TODO add birthdate to author entity
        //TODO add writeStartDate to book
        //TODO add publishDate to book
        //TODO implement check that writestartdate and writepublishdate must be at least n years after birthdate where n is a variable (use 30 years as default)
        throw new NotImplementedException("TODO add command-controller for writing autobiography");
    }
}
