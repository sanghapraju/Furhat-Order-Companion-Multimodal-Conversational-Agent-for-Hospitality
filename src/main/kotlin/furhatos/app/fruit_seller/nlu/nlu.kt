
package furhatos.app.fruit_seller.nlu

import furhatos.nlu.ComplexEnumEntity
import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.nlu.ListEntity
import furhatos.nlu.common.Number
import furhatos.util.Language
import furhatos.app.fruit_seller.nlu.BuyDessert
import furhatos.gestures.Gestures

class Fruit : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("White wine", "Red wine","Cabernet Sauvignon", "Chardonnay")
    }
}

class Food : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("Butter Chicken", "Palak Paneer", "Rogan Josh",  "Biryani")
    }
}

class Dessert : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("Rasagulla", "Gulab Jamun", "Rasmalai",  "Kheer")
    }
}

class BuyFood(var foods : FoodList? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("@foods", "I want @foods", "I would like @foods", "I want to try @foods")
    }
}
class BuyFruit(var fruits : FruitList? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("@fruits", "I want @fruits", "I would like @fruits", "I want to try @fruits", "Both")
    }
}

class BuyDessert(var desserts: DessertList?  = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("@desserts", "I want @desserts", "I would like @desserts", "I want to try @desserts")
    }
}

class RequestOptions_Food: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("What options do you have?",
                "What would you suggest?",
                "What are your specials?",
                "Would you suggest me some signature dish?")
    }
}

class RequestOptions_Water: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Could you get a glass of water",
            "Could you get some water",
            "Can I have a glass of water",
            "Can we have a glass of water")
    }
}


class FoodList : ListEntity<QuantifiedFood>()
class FruitList : ListEntity<QuantifiedFruit>()
class DessertList : ListEntity<QuantifiedDessert>()

class QuantifiedFruit(
        var count : Number? = Number(1),
        var fruit : Fruit? = null) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@count @fruit", "@fruit")
    }

    override fun toText(): String {
        return generate("$count " + if (count?.value == 1) fruit?.value else "${fruit?.value}" + "s")
    }
}
class QuantifiedFood(
    var count : Number? = Number(1),
    var foods : Food? = null) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@count @foods", "@foods")
    }

    override fun toText(): String {
        return generate("$count " + if (count?.value == 1) foods?.value else "${foods?.value}" + "s")
    }
}

class QuantifiedDessert(
    var count : Number? = Number(1),
    var desserts : Dessert? = null) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@count @desserts", "@desserts")
    }

    override fun toText(): String {
        return generate("$count " + if (count?.value == 1) desserts?.value else "${desserts?.value}" + "s")
    }
}

