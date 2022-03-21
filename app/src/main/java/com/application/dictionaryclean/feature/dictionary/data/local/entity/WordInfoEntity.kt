package com.application.dictionaryclean.feature.dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.application.dictionaryclean.feature.dictionary.domain.model.Meaning
import com.application.dictionaryclean.feature.dictionary.domain.model.WordInfo

@Entity(tableName = "wordinfoentity")
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val phonetic: String,
    val sourceUrls: List<String>,
    val word: String,
    @PrimaryKey var id: Int? = null
) {
    fun toWordInfo() = WordInfo(
        meanings = meanings,
        phonetic = phonetic,
        sourceUrls = sourceUrls,
        word = word
    )
}
