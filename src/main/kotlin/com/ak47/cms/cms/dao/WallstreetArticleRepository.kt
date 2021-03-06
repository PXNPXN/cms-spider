package com.ak47.cms.cms.dao

import com.ak47.cms.cms.entity.WallstreetArticle
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

interface WallstreetArticleRepository : PagingAndSortingRepository<WallstreetArticle, Long> {

    @Query("select count(*) from wallstreet_article where item_id = :item_id", nativeQuery = true)
    fun countByItemId(@Param("item_id") item_id: String): Int

    @Query("SELECT a from #{#entityName} a order by a.id desc")
    fun findWallstreetArticlePage(pageable: Pageable): Page<WallstreetArticle>

}