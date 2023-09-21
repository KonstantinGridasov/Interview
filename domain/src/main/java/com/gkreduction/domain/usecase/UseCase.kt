package com.gkreduction.domain.usecase

interface UseCase<In, Out> {
    suspend fun execute(param: In? = null): Out

}