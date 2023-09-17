package com.gkreduction.interview.utils

import android.content.Context
import android.content.res.AssetManager
import android.util.JsonReader
import com.gkreduction.interview.entity.DataInfo
import java.io.IOException
import java.io.InputStreamReader


@Throws(IOException::class)
fun readerJson(context: Context): ArrayList<DataInfo> {
    val assetManager: AssetManager = context.resources.assets

    var lists = ArrayList<DataInfo>()
    val reader = JsonReader(InputStreamReader(assetManager.open("interview_1.json")))
    try {
        lists = readerBaseEntity(reader)

    } catch (ex: IOException) {
        ex.printStackTrace()
    } finally {
        reader.close()
    }
    return lists
}

@Throws(IOException::class)
private fun readerBaseEntity(reader: JsonReader): ArrayList<DataInfo> {
    val baseEntity = ArrayList<DataInfo>()
    reader.beginArray()
    var k = 0
    while (reader.hasNext()) {
        val item = readerLetsPlay(reader)
        item.id = k
        item.prioretyCategory = getPriority(item.category)
        baseEntity.add(item)
        k++
    }
    reader.endArray()
    return baseEntity
}

private fun readerLetsPlay(reader: JsonReader): DataInfo {
    val item = DataInfo()
    reader.beginObject()
    while (reader.hasNext())
        when (reader.nextName()) {
            "category" -> item.category = reader.nextString()
            "question" -> item.question = reader.nextString()
            "answer" -> item.answer = reader.nextString()
            else -> reader.skipValue()
        }
    reader.endObject()
    return item
}

fun getPriority(cat: String): Int {
    return when (cat) {
        "ООП" -> 1
        "Design patterns." -> 2
        "Java Core." -> 3
        "Java" -> 4
        "Java 8" -> 5
        "Kotlin" -> 6
        "RxJava" -> 7
        "Android" -> 8
        else -> 99
    }
}
