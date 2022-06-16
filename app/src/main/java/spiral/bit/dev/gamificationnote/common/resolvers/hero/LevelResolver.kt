package spiral.bit.dev.gamificationnote.common.resolvers.hero

class LevelResolver {

    fun resolveUserLevel(progress: Int): Int {
        return when {
            progress <= 5 -> 1
            progress <= 10 -> 2
            progress <= 15 -> 3
            progress <= 20 -> 4
            progress <= 25 -> 5
            progress <= 30 -> 6
            progress <= 35 -> 7
            progress <= 40 -> 8
            progress <= 45 -> 9
            progress <= 50 -> 10
            progress <= 100 -> 11
            progress <= 200 -> 12
            progress <= 300 -> 13
            progress <= 400 -> 14
            progress <= 500 -> 15
            progress <= 600 -> 16
            progress <= 700 -> 17
            progress <= 800 -> 18
            progress <= 900 -> 19
            progress <= 1000 -> 20
            progress <= 1250 -> 21
            progress <= 1500 -> 22
            progress <= 1750 -> 23
            progress <= 2000 -> 24
            progress <= 2250 -> 25
            progress <= 2500 -> 26
            progress <= 2750 -> 27
            progress <= 3000 -> 28
            progress <= 3250 -> 29
            progress <= 3500 -> 30
            progress <= 3750 -> 31
            progress <= 4000 -> 32
            progress <= 5000 -> 33
            progress <= 6000 -> 34
            progress <= 7000 -> 35
            progress <= 8000 -> 36
            progress <= 9000 -> 37
            progress <= 10000 -> 38
            progress <= 11000 -> 39
            progress <= 12000 -> 40
            progress <= 13000 -> 41
            progress <= 14000 -> 42
            progress <= 15000 -> 40
            progress <= 16000 -> 41
            progress <= 17000 -> 42
            progress <= 18000 -> 43
            progress <= 19000 -> 44
            progress <= 20000 -> 45
            progress <= 21000 -> 46
            progress <= 22000 -> 47
            progress <= 23000 -> 48
            progress <= 24000 -> 49
            progress <= 30000 -> 50
            else -> MAX_LEVEL
        }
    }

    private companion object {
        private const val MAX_LEVEL = 50
    }
}