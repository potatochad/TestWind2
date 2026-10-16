package com.productivity.testwind2.Screens.Task

import com.productivity.testwind2.Imports.Utils.Log.*
import com.productivity.testwind2.Imports.Utils.Renames.*
import com.productivity.testwind2.Imports.Utils.AppsAndDevice.*
import com.productivity.testwind2.Imports.Utils.String.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import kotlin.collections.*
import androidx.compose.material3.Text
import android.graphics.drawable.Drawable
import com.productivity.testwind2.*
import com.productivity.testwind2.Imports.*
import com.productivity.testwind2.Imports.Utils.*
import com.productivity.testwind2.Imports.UI_visible.*


object TskProp {
	
	@Composable
	fun PickApp(show: mStateBool = mState(yes), Do: DoStr ={}) {
		var appList by r<List<Pair<AppInfo, Drawable?>>>(emptyList())
		var loading by r(no)

		RunOnce {
			runHeavyTask(
				task = {
					getApps()
						.filter { it.pkg() != AppPkg }
						.map { app ->
							val icon = AppIcon(app.pkg())
							app to icon
						}
				},
				onResult = {
					appList = it
					loading = no
				}
			)
		}

		LazyPopup(
           show = show,
           showCancel = no,
           showConfirm = no,
           title = "Select App",
       ){
	    	Column(Mod.h(200).Yscroll()){
		    	appList.forEach{ (app, icon) ->
			    	LazzyRow(Mod.click {
			    		Do(app.name)
			    		show.it = no
			    	}) {
			    		LazyImage(icon)
			    		move(10)
			    		Text(app.name.white())
			    	}
 		    	}
    		}
    	}
    }



	fun UpdateAppTsk(){
        Bar.apps.each {
            it.edit { it.nowTime = TodayAppUsage(it.pkg) }

            if (it.nowTime > it.doneTime - 1 && !it.done) {
                Bar.funTime += it.worth
                it.edit { done = yes }
                Vlog("${it.name} completed")
            }
        }
	}
}
	  
