package com.gkreduction.domain.usecase

interface UseCase<In, Out> {
    fun execute(request: In? = null): Out

}