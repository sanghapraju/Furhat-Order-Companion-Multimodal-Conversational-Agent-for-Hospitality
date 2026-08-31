package furhatos.app.fruit_seller.flow

import furhatos.app.fruit_seller.flow.main.Idle
import furhatos.app.fruit_seller.setting.distanceToEngage
import furhatos.app.fruit_seller.setting.maxNumberOfUsers
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice

val Init : State = state() {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(distanceToEngage, maxNumberOfUsers)
        furhat.voice = Voice("Matthew")
        /** start the interaction */
        goto(Idle)
    }
}