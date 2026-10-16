package com.productivity.testwind2.Imports.Utils.SaveData

import com.productivity.testwind2.Imports.Utils.Renames.*
import kotlin.collections.*


object AppListData {
    private val lists = mutableMapOf<Str, LazyAppData>()

    operator fun get(listName: Str): LazyAppData {
        return lists.getOrPut(listName) {
            LazyAppData(listName)
        }
    }
}
