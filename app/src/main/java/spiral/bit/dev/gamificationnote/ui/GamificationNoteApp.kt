package spiral.bit.dev.gamificationnote.ui

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware
import spiral.bit.dev.gamificationnote.di.databaseModule
import spiral.bit.dev.gamificationnote.di.repositoryModule

class GamificationNoteApp : Application(), DIAware {

    override val di: DI by DI.lazy {
        importAll(
            databaseModule,
            repositoryModule
        )
    }
}