/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain failure copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.domain.core.base

import com.domain.core.result.Result

/**
 * Abstract class for failure Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [SingleUseCase] implementation will execute its job in failure background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class SingleUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Result<Type>

    suspend operator fun invoke(params: Params, onResult: (Result<Type>) -> Unit = {}) {

//    onResult(run(params))
//        val job = async { run(params) }
//        lanch { onResult(job.await()) }
    }

    class None
}
