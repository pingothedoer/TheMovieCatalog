package com.pingo.tmdb.app

import androidx.test.espresso.IdlingResource

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-27.
 * ----------------------------------------------
 *
 *
 * Utility for Idle Resources
 */
class DelayedIdlingResource(private val waitingTime: Long) : IdlingResource {

    private val startTime: Long = System.currentTimeMillis()
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return DelayedIdlingResource::class.java.name + ":" + waitingTime
    }

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime
        val idle = elapsed >= waitingTime
        if (idle) {
            resourceCallback!!.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(
        resourceCallback: IdlingResource.ResourceCallback
    ) {
        this.resourceCallback = resourceCallback
    }
}