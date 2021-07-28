package com.gkredaction.interview.utils

import com.gkredaction.interview.entity.Interview
import com.gkredaction.interview.entity.Type
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

class Reader {
    @Throws(IOException::class)
    fun readerScv(file: String): ArrayList<Interview> {
        val result = ArrayList<Interview>()
        val reader = BufferedReader(FileReader(file))
        reader.use {
            val list = reader.readLines()
            for (i in 1 until list.size) {
                var line = list[i]
                val interview = Interview()
                var k = 0
                while (line.contains(";")) {
                    when (k) {
                        0 -> interview.type = Type.checkType(line.substringBefore(";"))
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

}