package com.productivity.testwind2.Screens.Widget

import com.productivity.testwind2.Imports.Utils.Log.*
import com.productivity.testwind2.Imports.Utils.Generic_list.*
import com.productivity.testwind2.Imports.Utils.Renames.*
import com.productivity.testwind2.Imports.Utils.SaveData.*
import com.productivity.testwind2.Imports.Utils.AppsAndDevice.*
import com.productivity.testwind2.Imports.Utils.NavControl.*
import com.productivity.testwind2.Imports.Utils.ToX.*
import com.productivity.testwind2.Imports.Utils.String.*
import java.text.*
import androidx.compose.foundation.interaction.*
import android.app.*
import androidx.core.app.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.unit.*
import androidx.compose.material3.*
import androidx.compose.foundation.text.*
import androidx.compose.ui.text.input.*
import androidx.compose.runtime.*
import android.os.*
import android.content.*
import android.util.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.font.*
import kotlinx.coroutines.*
import kotlin.reflect.*
import com.productivity.testwind2.*
import kotlin.collections.*
import com.productivity.testwind2.Screens.*
import androidx.compose.ui.layout.*
import android.content.pm.*
import com.productivity.testwind2.Imports.*
import java.util.*
import kotlin.reflect.full.*
import androidx.compose.ui.focus.*
import androidx.compose.foundation.gestures.*
import androidx.compose.ui.input.pointer.*
import java.io.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.text.style.*
import androidx.compose.foundation.lazy.*
import kotlin.concurrent.*
import androidx.core.view.*
import kotlin.reflect.jvm.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import androidx.compose.foundation.text.selection.*
import kotlin.system.*
import androidx.navigation.*
import android.webkit.*
import com.productivity.testwind2.Imports.Utils.*
import android.location.*
import androidx.core.content.*
import androidx.compose.ui.text.*
import androidx.navigation.compose.*
import com.google.android.gms.maps.model.*
import com.google.android.gms.location.*
import android.net.*
import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import androidx.compose.ui.window.*
import com.productivity.testwind2.Imports.UI_visible.*
import android.os.Process.*
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews


class HelloWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        try {

            appWidgetIds.forEach { widgetId ->

                val views = RemoteViews(
                    context.packageName,
                    R.layout.widget_hello
                )

                views.onClick(
                    R.id.widget_root,
                    goTo("wind://web", context)
                )

                appWidgetManager.updateAppWidget(
                    widgetId,
                    views
                )
            }

        } catch (e: Exception) {
            Log.e("HelloWidgetProvider", "Widget update failed", e)
        }
    }
}
