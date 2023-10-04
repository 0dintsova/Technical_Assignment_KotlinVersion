abstract class Creature(
    private var attack: Int,
    private var protection: Int,
    private var healthFinal: Int,
    private var damageMin: Int,
    private var damageMax: Int
) {
    fun getAttack(): Int{
        return attack
    }
    fun getProtection(): Int{
        return protection
    }
    fun getDamageMin(): Int{
        return damageMin
    }
    fun getDamageMax(): Int{
        return damageMax
    }
    fun getHealthFinal(): Int{
        return healthFinal
    }


    var health: Int = healthFinal
    abstract fun attack(creature: Creature)
    abstract fun printCreatures(i: Int)

}
