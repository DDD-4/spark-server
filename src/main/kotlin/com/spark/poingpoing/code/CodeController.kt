package com.spark.poingpoing.code

import com.spark.poingpoing.vocabulary.every.SortType
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["Code"])
@RestController
class CodeController {
    companion object {
        private val sortTypeEnumList = SortType::class.java.enumConstants
                .map { EnumValue(it.name, it.text) }
                .toList()
    }

    @ApiOperation("모두의 단어장 정렬옵션 목록")
    @GetMapping("v1/every-vocabularies/sort-types")
    fun vocabularySortType(): List<EnumValue> {
        sortTypeEnumList.forEach{
            println(it)
        }
        return sortTypeEnumList
    }
}