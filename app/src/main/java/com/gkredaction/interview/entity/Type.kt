package com.gkredaction.interview.entity

enum class Type(var info: String) {
    NONE(""),
    ANDROID("Android"),
    JAVA("java"),
    OOP("oop");

    companion object {
        fun checkType(name: String): Type {
            var type = Type.NONE
            for (k in Type.values())
                if (k.info == name) {
                    type = k
                    break
                }
            return type
        }
    }
}