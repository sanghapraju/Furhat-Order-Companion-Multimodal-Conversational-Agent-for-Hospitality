package furhatos.app.fruit_seller.flow.main

import furhatos.app.fruit_seller.flow.Parent
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.gestures.Gestures

val Greeting = state(Parent) {
    onEntry {
        furhat.gesture(Gestures.Thoughtful, async = false)
        furhat.gesture(Gestures.BigSmile, async = false)
        furhat.say {
            random {
                +"Hi there, Welcome to the Restaurant, We are pleased to have you here tonight !! "
                +"Good Evening, Welcome to the Restaurant!!"
            }
        }
        goto(TakingOrder)
    }
}

