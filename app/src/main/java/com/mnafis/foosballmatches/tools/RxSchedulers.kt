package com.mnafis.foosballmatches.tools

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RxSchedulerProvider @Inject constructor() {
    val androidMainThread: Scheduler
        get() = AndroidSchedulers.mainThread()
    val ioScheduler: Scheduler
        get() = Schedulers.io()
}