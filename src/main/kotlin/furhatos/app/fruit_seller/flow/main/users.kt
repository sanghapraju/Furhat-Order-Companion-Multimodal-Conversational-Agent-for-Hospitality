package furhatos.app.fruit_seller.flow.main

import furhatos.app.fruit_seller.nlu.FruitList
import furhatos.app.fruit_seller.nlu.FoodList
import furhatos.app.fruit_seller.nlu.DessertList
import furhatos.records.User

class FruitData {
    var fruits : FruitList = FruitList()
}

class FoodData {
    var foods : FoodList = FoodList()
}

class DessertData {
    var desserts : DessertList = DessertList()
}

val User.fruitOrder : FruitData
    get() = data.getOrPut(FruitData::class.qualifiedName, FruitData())

val User.foodOrder : FoodData
    get() = data.getOrPut(FoodData::class.qualifiedName, FoodData())

val User.dessertOrder : DessertData
    get() = data.getOrPut(DessertData::class.qualifiedName, DessertData())
