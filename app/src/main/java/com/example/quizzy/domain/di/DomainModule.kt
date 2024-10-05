package com.example.quizzy.domain.di

import com.example.quizzy.domain.repository.QuizRepository
import com.example.quizzy.domain.usecases.GetQuizzesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideGetQuizzesUseCases(quizRepository: QuizRepository) : GetQuizzesUseCases{
        return GetQuizzesUseCases(quizRepository)
    }
}