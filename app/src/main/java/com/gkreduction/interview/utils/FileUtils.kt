package com.gkreduction.interview.utils

import android.content.Context
import android.content.res.AssetManager
import com.gkreduction.interview.entity.Category
import com.gkreduction.interview.entity.DataInfo
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


@Throws(IOException::class)
fun readerScv(context: Context): ArrayList<DataInfo> {
    val assetManager: AssetManager = context.resources.assets
    val result = ArrayList<DataInfo>()
    val reader = BufferedReader(InputStreamReader(assetManager.open("interview.csv")))
    reader.use {
        val list = reader.readLines()
        for (i in 1 until list.size) {
            var line = list[i]
            val dataInfo = DataInfo()
            var k = 0
            while (line.contains(";")) {
                when (k) {
                    0 -> dataInfo.category = line.substringBefore(";")
                    1 -> dataInfo.question = line.substringBefore(";")
                    2 -> dataInfo.answer = line.substringBefore(";")
                }
                line = line.substringAfter(";")
                k++
            }
            dataInfo.id = i
            dataInfo.prioretyCategory = getPriority(dataInfo.category)
            result.add(dataInfo)
        }
    }
    return result
}


fun getPriority(cat: String): Int {
    return when (cat) {
        "ООП" -> return 1
        "Java core." -> return 2
        "Collections" -> return 3
        "Java 8." -> return 4
        "Потоки ввода/вывода в java." -> return 5
        "Multithreading." -> return 6
        "Сериализация." -> return 7
        "Design patterns." -> return 8
        "Kotlin" -> return 9
        "Базы данных." -> return 10
        "JDBC." -> return 11
        "SQL." -> return 12
        "MongoDB" -> return 13
        "Тестирование. JUNIT." -> return 14
        "Log4j." -> return 15
        "UML." -> return 16
        "XML." -> return 17
        "Test." -> return 18
        "Android" -> return 19
        else -> 99
    }
}
