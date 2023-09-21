package com.gkreduction.domain.entity

import java.io.Serializable

class Subtopic(override var id: Long, override var name: String) : BaseItem(id, name), Serializable