package com.application.dictionaryclean.feature.dictionary.di

import android.content.Context
import androidx.room.Room
import com.application.dictionaryclean.feature.dictionary.data.local.TypeConverter
import com.application.dictionaryclean.feature.dictionary.data.local.WordInfoDao
import com.application.dictionaryclean.feature.dictionary.data.local.WordInfoDatabase
import com.application.dictionaryclean.feature.dictionary.data.remote.DictionaryApi
import com.application.dictionaryclean.feature.dictionary.data.remote.DictionaryApi.Companion.BASE_URL
import com.application.dictionaryclean.feature.dictionary.data.repository.WordInfosRepositoryImpl
import com.application.dictionaryclean.feature.dictionary.data.util.GsonParser
import com.application.dictionaryclean.feature.dictionary.domain.repository.WordInfosRepository
import com.application.dictionaryclean.feature.dictionary.domain.use_case.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGetWordInfoUseCases(
        repository: WordInfosRepository
    ): GetWordInfo {
        return GetWordInfo(repository = repository)
    }

    @Singleton
    @Provides
    fun provideRepositoryWordInfos(
        api: DictionaryApi,
        dao: WordInfoDao
    ): WordInfosRepository {
        return WordInfosRepositoryImpl(api = api, dao = dao)
    }

    @Singleton
    @Provides
    fun provideWordInfoDatabase(
        @ApplicationContext context: Context
    ): WordInfoDatabase {
        return Room.databaseBuilder(context, WordInfoDatabase::class.java, "word_db")
            .addTypeConverter(TypeConverter(GsonParser(Gson()))).build()
    }

    @Singleton
    @Provides
    fun provideWordInfoDao(
        database: WordInfoDatabase
    ):WordInfoDao = database.dao

    @Singleton
    @Provides
    fun provideDictionaryApi(): DictionaryApi =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build()
            .create(DictionaryApi::class.java)
}