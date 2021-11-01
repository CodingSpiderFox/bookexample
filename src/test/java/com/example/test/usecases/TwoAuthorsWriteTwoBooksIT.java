package com.example.test.usecases;

import com.example.test.IntegrationTest;
import com.example.test.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.org.apache.commons.lang.NotImplementedException;

@IntegrationTest
@WithMockUser
@AutoConfigureMockMvc
public class TwoAuthorsWriteTwoBooksIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void letTwoBooksBeWrittenBySameTwoAuthors() {

        Author author1 = new Author();
        author1.setFirstName("Hans");
        author1.setLastName("Hutmann");

        throw new NotImplementedException("Need to implement");

    }
}
