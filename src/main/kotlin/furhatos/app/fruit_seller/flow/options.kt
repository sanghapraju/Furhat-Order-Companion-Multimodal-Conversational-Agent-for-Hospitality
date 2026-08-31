package furhatos.app.fruit_seller.flow

import furhatos.app.fruit_seller.flow.main.DessertOrder
import furhatos.app.fruit_seller.flow.main.DessertOrderReceived
import furhatos.app.fruit_seller.flow.main.wineOrderReceived
import furhatos.app.fruit_seller.flow.main.foodOrderReceived
import furhatos.app.fruit_seller.nlu.*
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.Yes

val Options = state(Parent) {
    onResponse<BuyFruit> {
        val fruits = it.intent.fruits
        if (fruits != null) {
            goto(wineOrderReceived(fruits))
        }
        else {
            propagate()
        }
    }

    onResponse<BuyFood> {
        val foods = it.intent.foods
        if (foods != null) {
            goto(foodOrderReceived(foods))
        } else {
            propagate()
        }
    }

    onResponse<BuyDessert> {
        val desserts = it.intent.desserts
        if (desserts != null) {
            goto(DessertOrderReceived(desserts))
        } else {
            propagate()
        }
    }

    onResponse<RequestOptions_Food> {
        furhat.say("We have ${Food().optionsToText()}")
        furhat.ask("Would like to place it order?")
    }

    onResponse<RequestOptions_Water> {
        furhat.say("Sure, Here is your glass of water !!")

    }

    onResponse<Yes> {
        random(
                { furhat.ask("What would you like to have?") },
                { furhat.ask("Would you like to have something?") }
        )
    }
}