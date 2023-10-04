import java.util.*

object Main {
    private var presencePlayer = 0
    private var presenceMonster = 0
    var sc = Scanner(System.`in`)
    private val list: ArrayList<Creature> = ArrayList<Creature>()

    @JvmStatic
    fun main(args: Array<String>) {
        var type = 1
        while (type != 0) {
            type = menu()
        }
    }

    private fun menu(): Int {
        var type = 0
        println(" 1. Промотр существ.\n 2. Создать существо.\n 3. Начать бой\n Введите 0, чтобы завершить программу.")
        checkInput()
        type = sc.nextInt()
        when (type) {
            1 -> if (list.isEmpty()) println("\n Никого нет\n Создайте существ!\n") else printCreatures()
            2 -> {
                println(" 1.Создать игрока\n 2.Создать монстра\nВведите 0, чтобы вернуться назад.\n")
                checkInput()
                val input = sc.nextInt()
                if (input == 1 || input == 2) createCreature(input) else if (input != 0) println("Неправильный ввод!\n")
            }

            3 -> {
                println("Бой\n")
                if (presenceMonster >= 1 && presencePlayer >= 1) {
                    //Выбор существ, которые будут бородбся друг с другом.
                    var numSelectedMonster: Int
                    var numSelectedPlayer: Int
                    println("Выберите игрока и монстра указав их номер.\n")
                    printCreatures()
                    println("Введите номер игрока")
                    do {
                        checkInput()
                        numSelectedPlayer = sc.nextInt()
                        if (numSelectedPlayer > presencePlayer || numSelectedPlayer < 1) println(" Неправильный ввод")
                    } while (numSelectedPlayer > presencePlayer || numSelectedPlayer < 1)
                    println("Введите номер монстра")
                    do {
                        checkInput()
                        numSelectedMonster = sc.nextInt()
                        if (numSelectedMonster > presenceMonster || numSelectedMonster < 1) println(" Неправильный ввод")
                    } while (numSelectedMonster > presenceMonster || numSelectedMonster < 1)
                    var monster: Creature? = null
                    var player: Creature? = null
                    var countMonsters = 0
                    var countPlayers = 0
                    for (item in list) {
                        if (item is Monster) {
                            countMonsters++
                            if (countMonsters == numSelectedMonster) {
                                monster = item
                                println("Монстр выбран")
                            }
                        }
                        if (item is Player) {
                            countPlayers++
                            if (countPlayers == numSelectedPlayer) {
                                player = item
                                println("Игрок выбран")
                            }
                        }
                    }
                    //Бой пока у какого-нибудь существа здоровье станет меньше или равно 0
                    do {
                        monster!!.attack(player!!)
                        if (player.health <= 0) {
                            list.remove(player)
                            presencePlayer--
                            return type
                        }
                        player.attack(monster)
                        if (monster.health <= 0) {
                            list.remove(monster)
                            presenceMonster--
                            return type
                        }
                    } while (monster!!.health > 0 && player!!.health > 0)
                } else println("\n Некому биться\n Создайте существ!\n")
            }
        }
        return type
    }

    private fun createCreature(type: Int) {
        var input: Int
        println("Введите следующие параметры:  Атака, Защита, Здоровье, Урон\n")
        println("Введите значение параметра Защита. Число от 1 до 30")
        val attack = checkNum1to30()
        println("Введите значение параметра Аттака. Число от 1 до 30")
        val protection = checkNum1to30()
        println("Введите значение параметра Здоровье. Число больше 1")
        do {
            checkInput()
            input = sc.nextInt()
            if (input < 1) println("Неправильное значение!")
        } while (input < 1)
        val health = input
        println("Введите диапазон параметра Урон. Введите 2 числа. Первое число минимальный урон, второе число максимальный урон.")
        checkInput()
        val damageMin = sc.nextInt()
        checkInput()
        var damageMax: Int
        do {
            damageMax = sc.nextInt()
            if (damageMin > damageMax) println("Второе значение меньше предыдущего")
        } while (damageMin > damageMax)
        if (type == 1) {
            list.add(Player(attack, protection, health, damageMin, damageMax))
            println("\nИгрок создан.")
            presencePlayer++
        }
        if (type == 2) {
            list.add(Monster(attack, protection, health, damageMin, damageMax))
            println("\nМонстр создан.")
            presenceMonster++
        }
    }

    fun checkInput() {
        while (!sc.hasNextInt()) {
            sc.next()
            println("Неправильный ввод!")
        }
    }

    fun getRandom(min: Int, max: Int): Int {
        return (Math.random() * (max + 1)).toInt() + min
    }

    private fun checkNum1to30(): Int {
        var input: Int
        do {
            checkInput()
            input = sc.nextInt()
            if (input < 1 || input > 30) println("Неправильное значение!")
        } while (input < 1 || input > 30)
        return input
    }

    private fun printCreatures() {
        println("Игроки")
        var i = 1
        for (item in list) {
            if (item is Player) {
                item.printCreatures(i)
                i++
            }
        }
        println("\nМонстры")
        i = 1
        for (item in list) {
            if (item is Monster) {
                item.printCreatures(i)
                i++
            }
        }
        println("\n")
    }
}