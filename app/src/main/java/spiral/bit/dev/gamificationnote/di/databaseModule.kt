package spiral.bit.dev.gamificationnote.di

import android.app.Application
import androidx.room.Room
import org.kodein.di.*
import org.kodein.di.bindings.WeakContextScope
import spiral.bit.dev.gamificationnote.R
import spiral.bit.dev.gamificationnote.data.sources.local.database.ShopDatabase
import spiral.bit.dev.gamificationnote.data.sources.local.database.TaskDatabase

val databaseModule = DI.Module(name = "database") {
    bindDao()
    bindDatabase()
}

fun DI.Builder.bindDatabase() {
    bind<TaskDatabase>() with scoped(WeakContextScope.of<Application>()).singleton {
        Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            context.getString(R.string.task_database_name)
        ).build()
    }

    bind<ShopDatabase>() with scoped(WeakContextScope.of<Application>()).singleton {
        Room.databaseBuilder(
            context,
            ShopDatabase::class.java,
            context.getString(R.string.shop_database_name)
        ).build()
    }
}

fun DI.Builder.bindDao() {
    bindInFolderTasksDao()
    bindTasksDao()
    bindShopDao()
}

fun DI.Builder.bindShopDao() {
    bindMultiton(tag = "weapons_dao") { shopDatabase: ShopDatabase ->
        shopDatabase.weaponDao()
    }

    bindMultiton(tag = "armor_dao") { shopDatabase: ShopDatabase ->
        shopDatabase.armorDao()
    }

    bindMultiton(tag = "background_dao") { shopDatabase: ShopDatabase ->
        shopDatabase.backgroundDao()
    }

    bindMultiton(tag = "theme_dao") { shopDatabase: ShopDatabase ->
        shopDatabase.themeDao()
    }

    bindMultiton(tag = "music_dao") { shopDatabase: ShopDatabase ->
        shopDatabase.musicDao()
    }

    bindMultiton(tag = "effect_dao") { shopDatabase: ShopDatabase ->
        shopDatabase.effectDao()
    }

    bindMultiton(tag = "app_feature_dao") { shopDatabase: ShopDatabase ->
        shopDatabase.appFeatureDao()
    }
}

fun DI.Builder.bindInFolderTasksDao() {
    bindMultiton(tag = "in_folder_simple_tasks_dao") { taskDatabase: TaskDatabase ->
        taskDatabase.inFolderSimpleTaskDao()
    }

    bindMultiton(tag = "in_folder_repeating_tasks_dao") { taskDatabase: TaskDatabase ->
        taskDatabase.inFolderRepeatingTaskDao()
    }

    bindMultiton(tag = "in_folder_composite_tasks_dao") { taskDatabase: TaskDatabase ->
        taskDatabase.inFolderCompositeTaskDao()
    }

    bindMultiton(tag = "in_folder_composite_sub_tasks_dao") { taskDatabase: TaskDatabase ->
        taskDatabase.inFolderCompositeSubTaskDao()
    }
}

fun DI.Builder.bindTasksDao() {
    bindMultiton(tag = "simple_tasks_dao") { taskDatabase: TaskDatabase ->
        taskDatabase.simpleTaskDao()
    }

    bindMultiton(tag = "repeating_tasks_dao") { taskDatabase: TaskDatabase ->
        taskDatabase.repeatingTaskDao()
    }

    bindMultiton(tag = "composite_tasks_dao") { taskDatabase: TaskDatabase ->
        taskDatabase.compositeTaskDao()
    }

    bindMultiton(tag = "composite_sub_tasks_dao") { taskDatabase: TaskDatabase ->
        taskDatabase.compositeSubTaskDao()
    }
}