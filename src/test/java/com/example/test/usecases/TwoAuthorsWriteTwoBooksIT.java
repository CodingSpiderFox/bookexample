package com.example.test.usecases;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.test.IntegrationTest;
import com.example.test.domain.Author;
import com.example.test.domain.Book;
import com.example.test.repository.AuthorRepository;
import com.example.test.repository.BookRepository;
import com.example.test.service.dto.AuthorDTO;
import com.example.test.service.dto.BookDTO;
import com.example.test.service.mapper.AuthorMapper;
import com.example.test.service.mapper.BookMapper;
import com.example.test.web.rest.TestUtil;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@IntegrationTest
@WithMockUser
@AutoConfigureMockMvc
public class TwoAuthorsWriteTwoBooksIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    void letTwoBooksBeWrittenBySameTwoAuthors() throws Exception {
        int authorsCountBefore = authorRepository.findAll().size();
        int booksCountBefore = bookRepository.findAll().size();

        Author author1 = new Author();
        author1.setFirstName("Hans");
        author1.setLastName("Hutmann");
        AuthorDTO author1DTO = authorMapper.toDto(author1);

        Author author2 = new Author();
        author2.setFirstName("Laura");
        author2.setLastName("Lewis");
        AuthorDTO author2DTO = authorMapper.toDto(author2);

        mockMvc
            .perform(post("/api/authors").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(author1DTO)))
            .andExpect(status().isCreated());
        mockMvc
            .perform(post("/api/authors").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(author2DTO)))
            .andExpect(status().isCreated());

        Book book1 = newBook("Book 1");
        Book book2 = newBook("Book 2");

        BookDTO book1DTO = bookMapper.toDto(book1);
        BookDTO book2DTO = bookMapper.toDto(book2);

        mockMvc
            .perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(book1DTO)))
            .andExpect(status().isCreated());
        mockMvc
            .perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(book2DTO)))
            .andExpect(status().isCreated());

        assertEquals(authorsCountBefore + 2, authorRepository.findAll().size());
        assertEquals(booksCountBefore + 2, bookRepository.findAll().size());
    }

    private Book newBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        book.setPrice(1.99D);
        if (!TestUtil.findAll(em, Author.class).isEmpty()) {
            book.addAuthor(TestUtil.findAll(em, Author.class).get(0));
        }

        return book;
    }
}
