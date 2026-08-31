package furhatos.app.fruit_seller

import furhatos.app.fruit_seller.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class Fruit_sellerSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}