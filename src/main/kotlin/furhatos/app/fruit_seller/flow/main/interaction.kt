package furhatos.app.fruit_seller.flow.main

import furhatos.app.fruit_seller.flow.Options
import furhatos.app.fruit_seller.nlu.*
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.Language
import kotlinx.coroutines.*
import furhatos.app.fruit_seller.nlu.BuyFood
import furhatos.app.fruit_seller.nlu.BuyDessert
import furhatos.app.fruit_seller.nlu.FoodList
import furhatos.gestures.Gestures

val SeatingArrangment = state(parent=Options){
    onEntry {
        furhat.gesture(Gestures.BigSmile, async = false)
        random(
            {furhat.ask("Tonight, you have the option of seating near the balcony with a scenic lake view or on the other side with a stunning city skyline.")},
            {furhat.ask("Tonight, you can choose between lakeside seating with a balcony view or city skyline seating.")}
        )
    }
}

val TakingOrder = state(parent = Options) {
    onEntry {
        furhat.gesture(Gestures.BigSmile, async = false)
        random(
                { furhat.ask("Tonight, we're offering a complimentary wine tasting. Would you be interested in trying some?") },
                { furhat.ask("We have a complimentary wine served tonight,would you like to try some?") }
        )
    }
    onReentry {
        furhat.ask("Sorry I didn't quite get you, We are serving complimentary wine today, Would you like to try some?")
    }
    onResponse<Yes> {
        goto(wineOrder())
    }

    onResponse<No> {
        goto(Specails())
    }
}


fun wineOrder() :State = state(Options) {
    onEntry {
        furhat.gesture(Gestures.BigSmile, async = false)
        random(
                { furhat.ask("We have a 2009 Cabernet Sauvignon Red Wine, aged for 15 years, and a 2012 Chardonnay White Wine, aged for 12 years.Which would you prefer to try?") },
                { furhat.ask("Would you like to sample our 15-year-aged 2009 Cabernet Sauvignon Red Wine or our 12-year-aged 2012 Chardonnay White Wine?") }
        )
    }
    onReentry {
        furhat.ask("Sorry, didn't quite catch you , We are only serving Red wine and White wine tonight, would you like some? ")
    }

    onResponse<BuyFruit> {
        val fruits = it.intent.fruits
        println(fruits)
        if (fruits != null) {
            goto(wineOrderReceived(fruits))
        }
        else {
            propagate()
        }
    }
}


fun wineOrderReceived(fruits:FruitList):State = state(Options){
    onEntry {
        furhat.gesture(Gestures.Nod,async = false)
        furhat.gesture(Gestures.BigSmile, async = false)
        furhat.say("${fruits.text}, What a lovely choice!, Here is your drink !!")
        fruits.list.forEach {
            users.current.fruitOrder.fruits.list.add(it)
        }
        random(
            {furhat.ask("Would you like to order some food now?")},
            {furhat.ask("Would you like me to take your food order now?")}
        )
    }
    onReentry {
        furhat.ask("Would you like me to take your food order now?")
    }
    onResponse<Yes>{
        goto(Specails())
    }
    onResponse<No> {
        furhat.say("Okay,Enjoy your drink!, Let me know if I can assist with anything !!")
        goto(Specails())

    }
}
fun Specails() :State = state(Options){

    onEntry {
        furhat.gesture(Gestures.BigSmile, async = false)
        random(
            {furhat.ask("Would you be interested in learning about our specials for this evening?")},
            {furhat.ask("Would you like to to have for  our specials for tonight?")}
        )
    }
    onReentry {
        furhat.ask{"Can I interset you in our specials for tonight?"}
    }
    onResponse<No>{
        goto(foodOrder())
    }
    onResponse<BuyFood>{
        val foods = it.intent.foods
        val foodText = foods?.text ?: ""
        when {
            foodText.equals("Butter Chicken", ignoreCase = true) -> {
                furhat.say("Our Butter Chicken is a true culinary delight, featuring tender pieces of chicken marinated in a rich blend of yogurt and spices, slow-cooked in a creamy tomato sauce with a hint of butter and garnished with fresh coriander leaves. It's a beloved classic that never fails to impress.")
            }
            foodText.equals("Palak Paneer", ignoreCase = true) -> {
                furhat.say("For those seeking a vegetarian option, our Palak Paneer is a must-try. This dish showcases cubes of paneer cheese simmered in a velvety spinach gravy, infused with aromatic spices such as cumin, garam masala, and a touch of cream for richness. It's both nutritious and flavorful, a perfect balance of texture and taste.")
            }
            foodText.equals("Rogan Josh", ignoreCase = true) -> {
                furhat.say("Indulge in the robust flavors of our Rogan Josh, a traditional Kashmiri dish that will tantalize your taste buds. Tender lamb pieces are marinated in a blend of yogurt and spices, then slowly cooked in a rich gravy infused with Kashmiri red chilies, garlic, and aromatic herbs. The result is a dish that is rich, aromatic, and full of depth, perfect for those craving something bold and satisfying.")
            }
            foodText.equals("Biryani", ignoreCase = true) -> {
                furhat.say("Our Biryani is a culinary masterpiece, featuring fragrant Basmati rice cooked with succulent pieces of chicken or lamb, delicately spiced and layered with caramelized onions, saffron-infused milk, and fresh mint leaves. Each bite is a symphony of flavors and textures, with the tender meat and aromatic rice perfectly complementing each other. It's a dish that embodies the essence of Indian cuisine, and a favorite among our patrons.")
            }
            else -> {
                furhat.say("Sorry, We don't have that on our menu for tonight ")
            }
        }
        goto(foodOrder())
    }
    onResponse<Yes>{
        furhat.ask("Tonight, our specials feature Butter Chicken, Palak Paneer, Rogan Josh, and Biryani. Would you like more details on any of these exquisite dishes?")
    }
}

fun foodOrder() :State = state(Options) {
    onEntry {
        furhat.gesture(Gestures.BigSmile, async = false)
        random(
            {furhat.ask("What dish would you like to order for tonight?")},
            {furhat.ask("What dish would you like to order?")}
        )
    }
    onReentry {
        random(
            {furhat.ask("Sorry we don't serve that for tonight, would you be interested in learning more about our special signature dishes?")}
        )
    }
    onResponse<BuyFood> {
        val foods = it.intent.foods
        println(foods)
        if (foods != null) {
            goto(foodOrderReceived(foods))
        }
        else {
            propagate()
        }
    }
}


fun foodOrderReceived(foodList: FoodList):State = state(Options){
    onEntry {
        furhat.gesture(Gestures.Nod,async = false)
        furhat.gesture(Gestures.BigSmile, async = false)
        random(
            {furhat.say("${foodList.text}, That's an excellent choice!")},
            {furhat.say("${foodList.text}, That's an amazing choice!, Personally That's my favorite")}
        )
        random(
            {furhat.say("That's our chef's signature dish as he is also an Indian chef!!")},
            {furhat.say("That dish bears the distinctive touch of our chef, who happens to be an expert in Indian cuisine and has crafted it as his signature masterpiece!")},
            {furhat.say("This particular dish showcases the culinary expertise of our chef, who, being of Indian descent, has infused it with his unique flair and expertise, making it a standout among our offerings.")}
        )
        foodList.list.forEach {
            users.current.foodOrder.foods.list.add(it)
        }
        furhat.ask("Can I interest you in a dessert today?")
    }
    onResponse<Yes>{
        goto(DessertSpecials())

    }
    onResponse<No> {
        val emptyDessertsList = DessertList()
        goto(EndConversation(foodList,emptyDessertsList))
    }
}

fun DessertSpecials() :State = state(Options){
    onEntry {
        furhat.gesture(Gestures.BigSmile, async = false)
        random(
            {furhat.say("Tonight, we are serving few Indian Desserts - Rasgulla, Gulab Jamun, Rasamalai and Kheer")},
            {furhat.say("This evening, we're offering a selection of delightful Indian desserts, including Rasgulla, Gulab Jamun, Rasamalai, and Kheer.")},
            {furhat.say("Tonight, our dessert menu features a variety of Indian sweets, such as Rasgulla, Gulab Jamun, Rasamalai, and Kheer.")},
        )
        furhat.ask("Would you like to hear more about any particular dessert?")
    }
    onReentry {
        furhat.ask{"Rasgulla, Gulab Jamun, Rasmalai and Kheer is on our dessert menu today"}
    }
    onResponse<BuyDessert>{
        val desserts = it.intent.desserts
        val foodText = desserts?.text ?: ""
        when {
            foodText.equals("Rasgulla", ignoreCase = true) -> {
                furhat.say("Our Rasgulla is a quintessential Indian sweet delicacy, crafted with precision and care. These soft and spongy cottage cheese balls are delicately cooked and then soaked in a light, sweet syrup infused with hints of cardamom and rose water. Each bite offers a burst of sweetness and a melt-in-your-mouth texture that is sure to delight your palate.")}
            foodText.equals("Gulab Jamun", ignoreCase = true) -> {
                furhat.say("Indulge in the luxurious flavors of our Gulab Jamun, a timeless Indian dessert cherished for its richness and decadence. These golden-brown dumplings, made from reduced milk solids and flour, are deep-fried until golden and then soaked in a fragrant syrup infused with rose water, saffron, and cardamom. With each bite, experience a symphony of sweet, floral, and aromatic notes that leave a lasting impression.")
            }
            foodText.equals("Rasamalai", ignoreCase = true) -> {
                furhat.say("Our Rasmalai is a sublime treat that exemplifies the artistry of Indian dessert-making. Delicate patties of paneer (Indian cottage cheese) are gently simmered in a luscious, creamy milk mixture, infused with the flavors of saffron, cardamom, and pistachios. Garnished with slivers of almonds and pistachios, this dessert offers a luxurious and velvety texture, complemented by a delicate balance of sweetness and spice.")
            }
            foodText.equals("Kheer", ignoreCase = true) -> {
                furhat.say("Savor the elegance of our Kheer, a classic Indian rice pudding that captivates with its simplicity and richness. Basmati rice is simmered in milk until tender, creating a creamy and comforting pudding infused with aromatic spices such as cardamom, cinnamon, and nutmeg. Sweetened with sugar and enriched with the nutty crunch of toasted almonds and pistachios, each spoonful of this dessert is a delightful journey through the flavors of India.")
            }
            else -> {
                furhat.say("Sorry, We don't have that on our menu for tonight ")
            }
        }
        goto(DessertOrder())
    }


}

fun DessertOrder() :State = state(Options) {
    onEntry {
        furhat.gesture(Gestures.BigSmile, async = false)
        random(
            {furhat.ask("What dessert would you like to order for tonight?")},
            {furhat.ask("What dessert would you like to order?")}
        )
    }
    onReentry {
        random(
            {furhat.ask("Sorry we don't serve that for tonight, would you like to try something from our menu??")}
        )
    }
    onResponse<BuyDessert> {
        val desserts = it.intent.desserts
        println(desserts)
        if (desserts != null) {
            goto(DessertOrderReceived(desserts))
        }
        else {
            propagate()
        }
    }
}

fun DessertOrderReceived(dessertsList: DessertList):State = state(Options){
    onEntry {
        furhat.gesture(Gestures.Nod,async = false)
        furhat.gesture(Gestures.BigSmile, async = false)
        random(
            {furhat.say("${dessertsList.text}, That's an amazing choice!")},
            {furhat.say("${dessertsList.text}, That's an extraordinary choice!")}
        )
        dessertsList.list.forEach {
            users.current.dessertOrder.desserts.list.add(it)
        }

        goto(EndConversation(users.current.foodOrder.foods,dessertsList))
    }
}

fun EndConversation(foodList: FoodList,dessertsList: DessertList): State=state(Options){
    onEntry {
        furhat.gesture(Gestures.BigSmile, async = false)
        furhat.say("Your order consists of ${foodList}and${dessertsList}")
        furhat.say("Your meal will be ready shortly, Let me know if you want my assistant!!")
    }
}

