package com.gkreduction.domain.usecase

interface UseCase<In, Out> {
    suspend fun execute(request: In? = null): Out

}