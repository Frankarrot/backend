package com.frankarrot.itemarticle.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ItemArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "content", length = 2000, nullable = false)
    private String content;

    @Column(name = "price", nullable = false)
    private Long price = 0L;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "seller_id", nullable = false, updatable = false)
    private Long sellerId;

    @Enumerated(EnumType.STRING)
    private ItemArticleStatus itemArticleStatus;

    public ItemArticle(Long id, String title, String content, Long price, String imageUrl, Long sellerId,
                       ItemArticleStatus itemArticleStatus) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.imageUrl = imageUrl;
        this.sellerId = sellerId;
        this.itemArticleStatus = itemArticleStatus;
    }
}
