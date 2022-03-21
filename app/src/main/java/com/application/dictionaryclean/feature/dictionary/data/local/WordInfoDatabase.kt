package com.application.dictionaryclean.feature.dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.application.dictionaryclean.feature.dictionary.data.local.entity.WordInfoEntity

@Database(
    version = 1,
    entities = [WordInfoEntity::class]
)
@TypeConverters(TypeConverter::class)
abstract class WordInfoDatabase : RoomDatabase() {
    abstract val dao: WordInfoDao
}