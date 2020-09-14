package com.spark.poingpoing.vocabulary.every

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.spark.poingpoing.folder.QFolder.folder
import com.spark.poingpoing.user.QUser.user
import com.spark.poingpoing.vocabulary.QVocabulary.vocabulary
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository


@Repository
class EveryVocabularyDao(private val jpaQueryFactory: JPAQueryFactory) {

    fun findEveryVocabularies(userId: Long, pageable: Pageable): Page<EveryVocabularyResponse> {
        val vocabularies = jpaQueryFactory
                .select(Projections.constructor(EveryVocabularyResponse::class.java,
                        folder.name,
                        user.name,
                        vocabulary.photoPath.max(),
                        vocabulary.id.count())
                )
                .from(folder)
                .innerJoin(folder.user, user)
                .on(folder.active.eq(true)
                        .and(folder.sharable.eq(true))
                        .and(folder.user.id.ne(userId)))
                .innerJoin(folder.vocabularies, vocabulary)
                .on(vocabulary.active.eq(true))
                .groupBy(folder.name, user.name)
                .orderBy(folder.updatedAt.desc())
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .fetch()

        return PageImpl<EveryVocabularyResponse>(vocabularies, pageable, vocabularies.size.toLong())
    }
}