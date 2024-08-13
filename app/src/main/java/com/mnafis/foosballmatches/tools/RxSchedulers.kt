package com.mnafis.foosballmatches.tools

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RxSchedulerProvider @Inject constructor() {
    val singleScheduler: Scheduler
        get() = Schedulers.single()
    val ioScheduler: Scheduler
        get() = Schedulers.io()
}