package com.kanjihybrid.editor.model;

import com.kanjihybrid.editor.model.base.AuditedEntity;

import javax.persistence.*;

/**
 * @author Frank Lloyd Teh
 */
@Entity
public class Article extends AuditedEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Lob
    private String body;

    @Column
    private String translatedTitle;

    @Lob
    private String translatedBody;

    @ManyToOne
    private User editor;

    @Version
    private Integer version;

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

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }
}
