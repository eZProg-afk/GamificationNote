package spiral.bit.dev.gamificationnote.common.resolvers.hero

import org.junit.After
import org.junit.Before
import org.junit.Test
import spiral.bit.dev.gamificationnote.common.resolvers.hero.LevelResolver

class LevelResolverTest {

    private var heroResolver: LevelResolver? = null

    @Before
    fun setUp() {
        heroResolver = LevelResolver()
    }

    @After
    fun tearDown() {
        heroResolver = null
    }

    @Test
    fun `when user experience level more then maximum return true`() {
        val progressMoreThanMaximumLevel = 31000
        val userLevel = heroResolver?.resolveUserLevel(progressMoreThanMaximumLevel)
        assert(userLevel == 50)
    }

    @Test
    fun `when user experience level less than minimum return true`() {
        val progressMoreThanMaximumLevel = 1
        val userLevel = heroResolver?.resolveUserLevel(progressMoreThanMaximumLevel)
        assert(userLevel == 1)
    }
}