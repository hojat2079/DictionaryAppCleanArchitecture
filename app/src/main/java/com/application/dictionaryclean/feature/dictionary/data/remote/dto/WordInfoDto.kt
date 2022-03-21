package com.application.dictionaryclean.feature.dictionary.data.remote.dto

import com.application.dictionaryclean.feature.dictionary.data.local.entity.WordInfoEntity
import com.application.dictionaryclean.feature.dictionary.domain.model.WordInfo


data class WordInfoDto(
    val license: LicenseDto,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
) {
    fun toWordInfo() = WordInfo(
        meanings = meanings.map { it.toMeaning() },
        phonetic = phonetic,
        sourceUrls = sourceUrls,
        word = word
    )
    fun toWordInfoEntity() = WordInfoEntity(
        meanings = meanings.map { it.toMeaning() },
        phonetic = phonetic,
        sourceUrls = sourceUrls,
        word = word
    )
}