package com.application.dictionaryclean.feature.dictionary.domain.use_case

import com.application.dictionaryclean.core.util.Resource
import com.application.dictionaryclean.feature.dictionary.domain.model.WordInfo
import com.application.dictionaryclean.feature.dictionary.domain.repository.WordInfosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
     val repository: WordInfosRepository
) {
    operator fun invoke(text: String): Flow<Resource<List<WordInfo>>> {
        if (text.isEmpty())
            return flow { }
        return repository.getTextInfos(word = text)
    }
}