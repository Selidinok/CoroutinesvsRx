package com.domain.core.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.koin.standalone.KoinComponent

/**
 * Created by User on 22:37 01.10.2018.

 */
class RemoteRequestWorker(val context: Context, val workerParameters: WorkerParameters) :
    Worker(context, workerParameters), KoinComponent {


    override fun doWork(): Result {
    }
}