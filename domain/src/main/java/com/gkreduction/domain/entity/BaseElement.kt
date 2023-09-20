package com.gkreduction.domain.entity

abstract class BaseElement(open val id: Long, open var name: String) {
    override fun equals(other: Any?): Boolean {
        if (other is BaseElement)
            return name == other.name
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return (31 * id + name.hashCode()).toInt()
    }
}
