package com.application.dictionaryclean.feature.dictionary.data.remote.dto

import com.application.dictionaryclean.feature.dictionary.domain.model.Meaning


data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
) {
    fun toMeaning() = Meaning(
        definitions = definitions.map { it.toDefinition() },
        partOfSpeech = partOfSpeech
    )
}