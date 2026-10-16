package com.productivity.testwind2.Screens.Widget

import com.productivity.testwind2.Imports.Utils.Renames.*
import com.productivity.testwind2.R
import android.util.*
import kotlin.collections.*
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
