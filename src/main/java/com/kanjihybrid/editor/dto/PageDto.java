package com.kanjihybrid.editor.dto;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Frank Lloyd Teh
 */
public class PageDto<T> {

    private List<T> content;

    private Long totalElements;

    private Integer totalPages;

    private Boolean hasNext;

    public PageDto(Page page, List<T> content) {
        this.content = content;
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.hasNext = page.hasNext();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }
}
