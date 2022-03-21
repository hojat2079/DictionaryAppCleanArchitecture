package com.application.dictionaryclean.feature.dictionary.data.remote.dto

import com.application.dictionaryclean.feature.dictionary.domain.model.Definition


data class DefinitionDto(
    val definition: String,
    val example: String?,
    val synonyms: List<String>,
    val antonyms: List<String>
) {
    fun toDefinition() = Definition(
        definition = definition,
        example = example,
        synonyms = synonyms,
        antonyms = antonyms
    )
}