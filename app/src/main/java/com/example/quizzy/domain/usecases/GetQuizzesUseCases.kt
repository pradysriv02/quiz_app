package com.example.quizzy.domain.usecases

import com.example.quizzy.common.Resource
import com.example.quizzy.domain.model.Quiz
import com.example.quizzy.domain.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

class GetQuizzesUseCases(
    val quizRepository: QuizRepository
) {

    fun getQuizzes(
        amount:Int,
        category:Int,
        difficulty:String,
        type:String
    ) : Flow<Resource<List<Quiz>>> = flow{

        emit(Resource.Loading())

        try {
           emit(Resource.Success(data = quizRepository.getQuizzes(amount, category, difficulty, type)))
        }
        catch (e:Exception){
            emit(Resource.Error(message = e.message.toString()))
        }

    }.flowOn(Dispatchers.IO)
}