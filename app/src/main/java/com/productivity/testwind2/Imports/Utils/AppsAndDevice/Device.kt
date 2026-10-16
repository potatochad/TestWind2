package com.productivity.testwind2.Imports.Utils.AppsAndDevice

import com.productivity.testwind2.Imports.Utils.Log.*
import com.productivity.testwind2.Imports.Utils.Renames.*
import android.os.*
import android.content.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.platform.*
import kotlin.collections.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import android.content.Intent
import com.productivity.testwind2.Imports.Utils.*
import android.content.ClipData
import android.content.ClipboardManager
import android.provider.Settings



@Composable
fun StatusBarHeight(): Int {
    val insets = WindowInsets.statusBars.asPaddingValues()
    val density = LocalDensity.current
    return with(density) { insets.calculateTopPadding().toPx().toInt() }
}

object Device {
    @Composable
    fun bottomBarHeight(): Dp {
        val insets = WindowInsets.navigationBars // includes bottom system bar
        val density = LocalDensity.current
        return with(density) { insets.getBottom(this).toDp() }
    }

}

fun CopyToClipboard(txt: Str) {
	val clipboard = App.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
	val clip = ClipData.newPlainText("label", txt)
	clipboard.setPrimaryClip(clip)
}


fun SendEmail(
	recipient: Str = "productivity.shield@gmail.com",
	subject: Str = "Support Request – App Issue",
	includePhoneInfo: Bool = yes,
	prefillMessage: Str = "I'm experiencing the following issue with the app:\n\n",
) {
	val body = buildString {
		appendLine()
		if (includePhoneInfo) {
			appendLine("Phone Info:")
			appendLine("• Manufacturer: ${Build.MANUFACTURER}")
			appendLine("• Model: ${Build.MODEL}")
			appendLine("• Android Version: ${Build.VERSION.RELEASE} (SDK ${Build.VERSION.SDK_INT})")
			appendLine()
		}
		append(prefillMessage)
	}

	val intent = Intent(Intent.ACTION_SEND).apply {
		type = "message/rfc822"
		putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
		putExtra(Intent.EXTRA_SUBJECT, subject)
		putExtra(Intent.EXTRA_TEXT, body)
	}

	val chooser = Intent.createChooser(intent, "Send Email").apply {
		flags = Intent.FLAG_ACTIVITY_NEW_TASK
	}

	startActivity(chooser)
}


//THISS MIGHT BE INVERTING COLOURS (BASED ON USER SETTINGS THAT ACTUALLY MAKING DEVICE MONOCROME, (IDKKK)
fun DeviceGray(enabled: Bool = true) {

}

fun DeviceGray(amount: Float) {
}






