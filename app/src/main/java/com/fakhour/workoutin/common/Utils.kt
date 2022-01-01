package com.fakhour.workoutin.common

import android.content.res.Resources


    fun dp2px(resources: Resources, dp: Int): Float {
        val scale: Float = resources.getDisplayMetrics().density
        return dp * scale + 0.5f
    }

    fun sp2px(resources: Resources, sp: Int): Float {
        val scale: Float = resources.getDisplayMetrics().scaledDensity
        return sp * scale
    }
