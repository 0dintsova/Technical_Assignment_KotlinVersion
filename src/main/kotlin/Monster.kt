import Main.checkInput
import Main.getRandom

class Monster(attack: Int, protection: Int, health: Int, damageMin: Int, damageMax: Int) :
    Creature(attack, protection, health, damageMin, damageMax) {

    override fun attack(creature: Creature) {
        //Рассчитываем модификатор атаки
        var modification = getAttack() - creature.getAttack() + 1
        if (modification <= 0) modification = 1
        //Определение успеха атаки
        for (i in 0 until modification) {
            val result = getRandom(1, 6)
            if (result == 5 || result == 6) {
                val hit = getRandom(getDamageMin(), getDamageMax())
                creature.health -= hit
                println("Монстр наносит удар с силой $hit")
                if (creature.health <= 0) {
                    println("Игрок теряет здоровье. Его здоровье стало ${creature.health}")
                    println("Игрок умер")
                    break
                } else {
                    println("Игрок теряет здоровье. Его здоровье стало ${creature.health}")
                    println(
                        """Желаете исцелить игрока? 1.Да 2. Нет Вы можете исцелить еще ${(creature as Player?)!!.count}раз(а)""")
                    checkInput()
                    when (Main.sc.nextInt()) {
                        1 -> (creature as Player?)!!.healing()
                        2 -> {}
                    }
                }
                break
            }
        }
    }

    override fun printCreatures(i: Int) {
        println(
            " Монстр $i Атака: ${getAttack()} Защита: ${getProtection()} Здоровье: $health Урон: [ ${getDamageMin()} - ${getDamageMax()} ]"
        )
    }
}
