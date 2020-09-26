package com.spark.poingpoing.util

abstract class PageResponse<T>(
        private val content: List<T>,

        private val hasNext: Boolean,

        private val totalCount: Long
)
