package com.application.dictionaryclean.feature.dictionary.domain.model

data class WordInfoState(
    val words: List<WordInfo> = emptyList(),
    val isLoading :Boolean = false
)