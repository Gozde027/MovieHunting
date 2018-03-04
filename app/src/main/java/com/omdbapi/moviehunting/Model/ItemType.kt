package com.omdbapi.moviehunting.Model


/**
 * Created by Gozde Kaval on 3/4/2018.
 */

//type movie, series, episode, game
enum class ItemType(val type: String) {
    MOVIE("movie"),
    SERIES("series"),
    EPISODE("episode"),
    GAME("game");

    companion object {
        fun from(selectedType: String) : ItemType? = values().find { it.type == (selectedType) }
    }
}

/**
 * companion object {
fun from(s: String): TaskAction? = values().find { it.value == s }
}
 */


/*
* enum class TaskAction constructor(val value: String) {
    ARCHIVE("Archive"), UN_FLAG("Un flag"), FLAG("Flag"), REDO("Redo"),
    READY("Ready"), EDIT("Edit"), DND("dnd"), DELETE("Delete"),
    IN_PROGRESS("In progress"), DONE("Done");

    companion object {
        private val lookup = values().associateBy(TaskAction::value)
        fun fromValue(value: String):TaskAction = requireNotNull(lookup[value]) { "No TaskAction with value $value" }
    }
}
* */