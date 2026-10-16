package com.productivity.testwind2.Imports.Utils

import com.productivity.testwind2.Imports.Utils.Renames.*
import com.productivity.testwind2.Imports.Utils.ToX.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import android.content.Intent

fun AndroidSettings(action: Str) {
    startActivity(
		Intent(action).apply {
			addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
		}
	)
}


@Composable
fun getW(): Float {
    return toF(BoxWithConstraints {
        toInt(maxWidth)
    })
}

@Composable
fun getH(): Float {
    return toF(BoxWithConstraints {
        toInt(maxHeight)
    })
}




fun ProgressColor(progress: Float): Color {
	return when {
		progress < 0.33f -> Color.Red
		progress < 0.66f -> Color.Yellow
		else -> Color.Green
	}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeCanBeTiny(ui: ui) {
	CompositionLocalProvider(
		LocalMinimumInteractiveComponentEnforcement provides false
	) {
		ui()
	}
}
