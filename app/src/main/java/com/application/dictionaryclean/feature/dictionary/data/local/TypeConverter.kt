package com.application.dictionaryclean.feature.dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.application.dictionaryclean.feature.dictionary.data.util.JsonParser
import com.application.dictionaryclean.feature.dictionary.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class TypeConverter(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromMeaningJson(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json = json,
            type = object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningJson(obj: List<Meaning>): String {
        return jsonParser.toJson(obj = obj,
            type = object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromStringJson(json: String): List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json = json,
            type = object : TypeToken<ArrayList<String>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toStringJson(obj: List<String>): String {
        return jsonParser.toJson(obj = obj,
            type = object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"
    }
}