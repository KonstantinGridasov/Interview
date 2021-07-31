package com.gkreduction.interview.utils

import com.gkreduction.interview.entity.DataInfo
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

@Throws(IOException::class)
fun readerScv(): ArrayList<DataInfo> {
    val result = ArrayList<DataInfo>()
    val reader = BufferedReader(FileReader("file"))
    reader.use {
        val list = reader.readLines()
        for (i in 1 until list.size) {
            var line = list[i]
            val interview = DataInfo()
            var k = 0
            while (line.contains(";")) {
                when (k) {
                    0 -> interview.type = line.substringBefore(";")
                    1 -> interview.question = line.substringBefore(";")
                }
                line = line.substringAfter(";")
                k++
            }
            interview.answer = line
            result.add(interview)
        }
    }
    return result
}

