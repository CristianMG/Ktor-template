package com.example.domain.usecase


/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means that any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    abstract fun run(params: Params):  Type

    operator fun invoke(
        params: Params,
        onResult: (Either<Throwable, Type>) -> Unit = {}
    ) {
        try {
            onResult(Either.Right(run(params)))
        }catch (e:Exception){
            onResult(Either.Left(e))
        }
    }
}