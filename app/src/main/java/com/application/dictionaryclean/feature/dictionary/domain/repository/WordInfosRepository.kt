package com.application.dictionaryclean.feature.dictionary.domain.repository

import com.application.dictionaryclean.core.util.Resource
import com.application.dictionaryclean.feature.dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfosRepository {
     fun getTextInfos(word:String): Flow<Resource<List<WordInfo>>>
}