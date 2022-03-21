package com.application.dictionaryclean.feature.dictionary.data.repository

import android.util.Log
import com.application.dictionaryclean.core.util.Resource
import com.application.dictionaryclean.feature.dictionary.data.local.WordInfoDao
import com.application.dictionaryclean.feature.dictionary.data.remote.DictionaryApi
import com.application.dictionaryclean.feature.dictionary.domain.model.WordInfo
import com.application.dictionaryclean.feature.dictionary.domain.repository.WordInfosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfosRepositoryImpl(
     val api: DictionaryApi,
     val dao: WordInfoDao
) : WordInfosRepository {
    override  fun getTextInfos(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())
        Log.i("WordInfosRepository","Loading")


        val wordInfos = dao.getWordInfos(word = word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word = word)

            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (ex: HttpException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, please check network connection.",
                    data = wordInfos
                )
            )

        } catch (ex: IOException) {
            emit(
                Resource.Error(
                    message = "oops, something went wrong",
                    data = wordInfos
                )
            )

        }
        val newWordInfos = dao.getWordInfos(word = word).map { it.toWordInfo() }
        emit(Resource.Success(data = newWordInfos))



    }
}