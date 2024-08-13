package com.mnafis.foosballmatches.tools

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RxSchedulerProvider @Inject constructor() {
    val mainThreadScheduler: Scheduler
        get() = Schedulers.single()
    val ioScheduler: Scheduler
        get() = Schedulers.io()
}