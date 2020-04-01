package com.pia.appetiser.test.presentation.common.arch

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ThreadPoolExecutor @Inject constructor() : ThreadExecutor {

    companion object {

        private const val INITIAL_POOL_SIZE = 3
        private const val MAX_POOL_SIZE = 5

        private const val KEEP_ALIVE_TIME = 10L
        private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
    }

    private val workQueue: BlockingQueue<Runnable>

    private val threadPoolExecutor: java.util.concurrent.ThreadPoolExecutor

    private val threadFactory: ThreadFactory

    init {
        this.workQueue = LinkedBlockingQueue<Runnable>()
        this.threadFactory = ExecutionThreadFactory()
        this.threadPoolExecutor = java.util.concurrent.ThreadPoolExecutor(
            INITIAL_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            this.workQueue,
            this.threadFactory
        )
    }

    override fun execute(runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    private class ExecutionThreadFactory : ThreadFactory {

        companion object {
            private val THREAD_NAME = "mCrewCore_"
        }

        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, THREAD_NAME + counter++)
        }
    }
}