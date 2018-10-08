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

import com.domain.core.result.State

/**
 * Abstract class for failure Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [SuspendUseCase] implementation will execute its job in failure background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class SuspendUseCase<out Type, in Params> where Type : Any {

    suspend abstract fun run(params: Params): Type

    suspend operator fun invoke(params: Params, onResult: (Type) -> Unit = {}) {
        onResult(run(params))
    }

    class None
}
