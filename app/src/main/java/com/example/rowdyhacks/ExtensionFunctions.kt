package com.example.rowdyhacks

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View

/** Fades a view in or out **/
fun View.fade(fadeIn: Boolean, animDuration: Long? = null) {

    val view = this

    if (fadeIn) {
        //if view is gone make it visible
        if(this.visibility == View.GONE) {
            this.visibility = View.VISIBLE
        }

        ObjectAnimator.ofFloat(this, View.ALPHA, 1f).apply {

            duration = animDuration ?: duration
            setAutoCancel(true)
            start()
        }
    } else {
        ObjectAnimator.ofFloat(this, View.ALPHA, 0f).apply {

            duration = animDuration ?: duration

            //make view completely disappear after completion
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator) {
                    //NOTHING
                }

                override fun onAnimationEnd(p0: Animator) {
                    view.visibility = View.GONE
                }

                override fun onAnimationCancel(p0: Animator) {
                    view.visibility = View.GONE
                }

                override fun onAnimationRepeat(p0: Animator) {
                    //NOTHING
                }

            })

            setAutoCancel(true)
            start()
        }
    }
}
//END OF FADE