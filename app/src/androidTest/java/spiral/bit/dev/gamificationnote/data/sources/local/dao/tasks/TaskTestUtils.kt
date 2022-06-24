package spiral.bit.dev.gamificationnote.data.sources.local.dao.tasks

import spiral.bit.dev.gamificationnote.data.dto.task.TaskAutoCreationInterval
import spiral.bit.dev.gamificationnote.data.dto.task.TaskDifficult
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.CompositeSubTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.CompositeTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.RepeatingTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.SimpleTaskEntity
import spiral.bit.dev.gamificationnote.data.sources.local.entities.tasks.inFolder.*

object TaskTestUtils {

    fun createRepeatingTask(name: String = "Task title") = RepeatingTaskEntity(
        id = 1,
        name = name,
        description = "Description Of Task",
        difficult = TaskDifficult.EPIC,
        autoCreationInterval = TaskAutoCreationInterval.EVERY_DAY,
        nextAutoCreationDateInMillisTask = 0
    )

    fun createSimpleTask(name: String = "Task title") = SimpleTaskEntity(
        id = 1,
        name = name,
        description = "Description Of Task",
        difficult = TaskDifficult.EPIC
    )

    fun createCompositeTask(
        name: String = "Composite task name",
        currentProgress: Int = 0
    ) = CompositeTaskEntity(
        id = 1,
        name = name,
        description = "In Folder repeating task description",
        currentProgress = currentProgress
    )

    fun createCompositeSubTask(name: String = "Composite sub task") = CompositeSubTaskEntity(
        id = 1,
        parentCompositeTaskId = 1,
        name = name
    )

    fun createFolder(name: String = "Folder Name") = FolderEntity(
        id = 1,
        name = name,
        description = "Folder description",
        iconResource = 0
    )

    fun createInFolderSimpleTask(name: String = "In Folder Task title") = InFolderSimpleTaskEntity(
        id = 1,
        parentFolderId = 1,
        name = name,
        description = "In Folder description Of Task",
        difficult = TaskDifficult.COMMON
    )

    fun createInFolderRepeatingTask(name: String = "Repeating task name") =
        InFolderRepeatingTaskEntity(
            id = 1,
            parentFolderId = 1,
            name = name,
            description = "In Folder repeating task description",
            autoCreationInterval = TaskAutoCreationInterval.EVERY_MONTH
        )

    fun createInFolderCompositeTask(
        name: String = "Composite task name",
        currentProgress: Int = 0
    ) = InFolderCompositeTaskEntity(
        id = 1,
        parentFolderId = 1,
        name = name,
        description = "In Folder repeating task description",
        currentProgress = currentProgress
    )

    fun createInFolderCompositeSubTask(name: String = "In Folder composite sub task") =
        InFolderCompositeSubTaskEntity(
            id = 1,
            parentCompositeTaskId = 1,
            name = name
        )
}