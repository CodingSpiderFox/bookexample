package com.example.test.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.InstantFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.example.test.domain.Book} entity. This class is used
 * in {@link com.example.test.web.rest.BookResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /books?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BookCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DoubleFilter price;

    private StringFilter title;

    private InstantFilter writeStartTimestamp;

    private InstantFilter publishTimestamp;

    private LongFilter authorId;

    private Boolean distinct;

    public BookCriteria() {}

    public BookCriteria(BookCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.writeStartTimestamp = other.writeStartTimestamp == null ? null : other.writeStartTimestamp.copy();
        this.publishTimestamp = other.publishTimestamp == null ? null : other.publishTimestamp.copy();
        this.authorId = other.authorId == null ? null : other.authorId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BookCriteria copy() {
        return new BookCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public DoubleFilter getPrice() {
        return price;
    }

    public DoubleFilter price() {
        if (price == null) {
            price = new DoubleFilter();
        }
        return price;
    }

    public void setPrice(DoubleFilter price) {
        this.price = price;
    }

    public StringFilter getTitle() {
        return title;
    }

    public StringFilter title() {
        if (title == null) {
            title = new StringFilter();
        }
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public InstantFilter getWriteStartTimestamp() {
        return writeStartTimestamp;
    }

    public InstantFilter writeStartTimestamp() {
        if (writeStartTimestamp == null) {
            writeStartTimestamp = new InstantFilter();
        }
        return writeStartTimestamp;
    }

    public void setWriteStartTimestamp(InstantFilter writeStartTimestamp) {
        this.writeStartTimestamp = writeStartTimestamp;
    }

    public InstantFilter getPublishTimestamp() {
        return publishTimestamp;
    }

    public InstantFilter publishTimestamp() {
        if (publishTimestamp == null) {
            publishTimestamp = new InstantFilter();
        }
        return publishTimestamp;
    }

    public void setPublishTimestamp(InstantFilter publishTimestamp) {
        this.publishTimestamp = publishTimestamp;
    }

    public LongFilter getAuthorId() {
        return authorId;
    }

    public LongFilter authorId() {
        if (authorId == null) {
            authorId = new LongFilter();
        }
        return authorId;
    }

    public void setAuthorId(LongFilter authorId) {
        this.authorId = authorId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BookCriteria that = (BookCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(price, that.price) &&
            Objects.equals(title, that.title) &&
            Objects.equals(writeStartTimestamp, that.writeStartTimestamp) &&
            Objects.equals(publishTimestamp, that.publishTimestamp) &&
            Objects.equals(authorId, that.authorId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, title, writeStartTimestamp, publishTimestamp, authorId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (price != null ? "price=" + price + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (writeStartTimestamp != null ? "writeStartTimestamp=" + writeStartTimestamp + ", " : "") +
            (publishTimestamp != null ? "publishTimestamp=" + publishTimestamp + ", " : "") +
            (authorId != null ? "authorId=" + authorId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
