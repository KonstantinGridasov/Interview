package com.gkreduction.data.utils

import com.gkreduction.domain.entity.QuestionAnswer

fun randomWithSize(
    list: ArrayList<QuestionAnswer>,
    random: Boolean,
    size: Int
): List<QuestionAnswer> {

    if (random)
        list.shuffle()
    else
        list.sortBy { it.position }

    val result = ArrayList<QuestionAnswer>()
    if (size > 0 && size < list.size) {
        for (i in list.indices) {
            if (i < size)
                result.add(list[i])
            else
                break
        }
    } else
        result.addAll(list)

    return result


}