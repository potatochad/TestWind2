package com.productivity.testwind2.Imports.Utils.SaveData

//‼️ISSUES
//DEAL LATER WITH TYPE CHANGED OR NAME CHANGED in class
//only handles 3 types (bool, str, int)
//Currently the hole list updates on a tiny change.
//THERE IS A bunch of data saved data (that doesnt get cleaned up, after years, massive issue)
//It doesnt handle Delete or clear (saved doesnt change)
// CustomAlertList may habe way more inputs that needs do (like onAdd  but you know others)
//Cause doesnt save in order, it doesnt onBuild in orderrr. not 1,2,3 indexes, but 5,1,3

import com.productivity.testwind2.Imports.Utils.Log.*
import com.productivity.testwind2.Imports.Utils.Generic_list.*
import com.productivity.testwind2.Imports.Utils.Renames.*
import com.productivity.testwind2.Imports.Utils.AppsAndDevice.*
import com.productivity.testwind2.Imports.Utils.ToX.*
import com.productivity.testwind2.Imports.Utils.String.*
import androidx.compose.runtime.*
import kotlin.collections.*
import androidx.compose.runtime.getValue


private fun hasUnsupportedTypes(
    varList: List<VarInfo<*>>
): Bool {
    for (it in varList) {
        if (it.typeStr !in supportedTypes) {
            Vlog("Unsupported type: ${it.typeStr} (${it.name})")
            return yes
        }
    }

    return no
}

private var supportedTypes = listOf(
    "java.lang.String",
    "java.lang.Boolean",
    "java.lang.Integer"
)

fun getLazyDataVar(key: Str, varName: Str, listName: Str): Any? {
    if (key.empty) return null
    val data = AppListData[listName].get(key, "") ?: return null

    val regex = Regex("""$varName:([^:]+):("[^"]*"|[^,}]+)""")
    val match = regex.find(data) ?: return null

    val type = match.groupValues[1]
    val raw = match.groupValues[2]

    return when (type) {
        "java.lang.String" -> raw.removeSurrounding("\"")
        "java.lang.Integer" -> raw.toInt()
        "java.lang.Boolean" -> raw.toBoolean()
        else -> null
    }
}



fun < T : LazyData> TrackList(
    createItem: () -> T,
    defaultItems: List<T> = emptyList()
): By<EasyList<T>> {
    var list = EasyList<T>()


    var times by mState(0)
    val save = IgnoreRepeatedCalls(1000, appNonUIScope) {
        list.each {
            if (it.changed) it.save()
        }
    }

    return By(list)
        .onBuild { prop, listName, mValue -> 
            val data = AppListData[listName].all

            val items = data.map { (id, _) ->
                createItem().apply {
                    this.id = id
                    this.onChanged = save::run
                }
            }
            
            list.addAll(items)
            
            list.onAdd { 
                it.changed = yes
                it.onChanged = save::run
                it.id = "$listName:${Id()}"
                save.run() 
            }
            list.onAddAll { 
                Vlog("DONT USE onAddALL !!!")
                //‼️ its not implemented yet!!
            }
            list.onRemove{
                AppData.remove(it.id)
            }
                
            mValue.it = list

            
        }
        .onSet { _, _, _ -> VlogOne("LIST MUST BE VAL") }
}

//nothing can be private
abstract class LazyData {
    var id = ""
    var listName = ""
    
    var changed by mState(no)
    var onChanged: Do = {}
    
    val clazzName = this.className
    
    
    val vars = mutableMapOf<Str, VarInfo<*>>()
    
    inline fun <reified T> lazyS(x: T): By<T> {
        return By(x)
            .onFirstGetOrSet{ prop, name, mValue -> 
                var savedValue: Any? = null
                savedValue = getLazyDataVar(id, name, listName)
                if (savedValue != null) mValue.it = savedValue as T
                vars[name] = VarInfo(name, mValue.it)
            }
            .onSet { prop, name, value ->
                vars[name] = VarInfo(name, value)
                changed = yes
                onChanged()
            }
    }


    open fun save(){
        var varList = vars.values.toList()

        
        if (hasUnsupportedTypes(varList)) return
        
        var customStr = VarInfoListToStr(varList)

        AppListData[listName].put(id, customStr) 
        changed = no
    }
    
    
}



class Schedule6(
    val name: Str
) {
    var time = 10
    val created = System.currentTimeMillis()

    fun reset() {
        time = 0
    }
}


class TestData : LazyData() {

    var name by lazyS("item")

    var stringVar by lazyS("hello")
    var intVar by lazyS(2)
    var boolVar by lazyS(yes)
    var stringVAL by lazyS("hello")
}





