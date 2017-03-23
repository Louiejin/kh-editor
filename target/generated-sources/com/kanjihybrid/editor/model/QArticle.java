package com.kanjihybrid.editor.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QArticle extends EntityPathBase<Article> {

    private static final long serialVersionUID = -1744089000L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArticle article = new QArticle("article");

    public final com.kanjihybrid.editor.model.base.QAuditedEntity _super = new com.kanjihybrid.editor.model.base.QAuditedEntity(this);

    public final StringPath body = createString("body");

    //inherited
    public final DateTimePath<org.joda.time.DateTime> created = _super.created;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final QUser editor;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final StringPath translatedBody = createString("translatedBody");

    public final StringPath translatedTitle = createString("translatedTitle");

    //inherited
    public final DateTimePath<org.joda.time.DateTime> updated = _super.updated;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QArticle(String variable) {
        this(Article.class, forVariable(variable), INITS);
    }

    public QArticle(Path<? extends Article> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QArticle(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QArticle(PathMetadata<?> metadata, PathInits inits) {
        this(Article.class, metadata, inits);
    }

    public QArticle(Class<? extends Article> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.editor = inits.isInitialized("editor") ? new QUser(forProperty("editor")) : null;
    }

}

