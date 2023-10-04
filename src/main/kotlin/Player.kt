import Main.getRandom

class Player internal constructor(attack: Int, protection: Int, health: Int, damageMin: Int, damageMax: Int) :
    Creature(attack, protection, health, damageMin, damageMax) {
    var count = 4
    fun healing() {
        // проверка сколько раз исцелялился игрок
        if (count != 0) {
            health += (getHealthFinal() * 0.3).toInt()
            count--
            System.out.println("Игрок исцелился. Его здоровье стало $health")
        } else println("Вам не доступно исцеление!")
    }


    override fun attack(creature: Creature) {
        //Рассчитываем модификатор атаки
        var modification: Int = getAttack() - creature.getAttack() + 1
        if (modification <= 0) modification = 1
        var result: Int
        //Определение успеха атаки
        for (i in 0 until modification) {
            result = getRandom(1, 6)
            if (result == 5 || result == 6) {
                val hit = getRandom(getDamageMin(), getDamageMax())
                creature.health -= hit
                println("Игрок наносит удар с силой $hit")
                if (creature.health <= 0) {
                    System.out.println("Монстр теряет здоровье. Его здоровье стало  ${creature.health}" )
                    println("Монстр умер")
                } else System.out.println("Монстр теряет здоровье. Его здоровье стало ${ creature.health}")
                break
            }
        }
    }


    override fun printCreatures(i: Int) {
        println(" Игрок $i - Атака: ${getAttack()} Защита: ${getProtection()} Здоровье: $health Урон: [ ${getDamageMin()} - ${getDamageMax()} ]")
    }
}
