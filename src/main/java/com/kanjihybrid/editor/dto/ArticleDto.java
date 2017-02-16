package com.kanjihybrid.editor.dto;

import org.joda.time.DateTime;

/**
 * @author Frank Lloyd Teh
 */
public class ArticleDto {

    private Long id;

    private String title;

    private String body;

    private String translatedTitle;

    private String translatedBody;

    private UserDto editor;

    private Integer version;

    private DateTime updated;

    public String getTranslatedTitle() {
        return translatedTitle;
    }

    public void setTranslatedTitle(String translatedTitle) {
        this.translatedTitle = translatedTitle;
    }

    public String getTranslatedBody() {
        return translatedBody;
    }

    public void setTranslatedBody(String translatedBody) {
        this.translatedBody = translatedBody;
    }

    public DateTime getUpdated() {
        return updated;
    }

    public void setUpdated(DateTime updated) {
        this.updated = updated;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public UserDto getEditor() {
        return editor;
    }

    public void setEditor(UserDto editor) {
        this.editor = editor;
    }
}
