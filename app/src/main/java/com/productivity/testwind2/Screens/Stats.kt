package com.productivity.testwind2

import com.productivity.testwind2.Imports.Utils.ToX.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.productivity.testwind2.Imports.UI_visible.*



@Composable
fun Achievements() {
    LazyScreen("Achievements") {
            
        LazyItem(
            icon = { Icon.Edit() },
            title = "Typed letters",
            endUI = {
                Text("${toHumanReadableAmountWritten(Bar.LettersTyped)}")
            }
        )
    }
}
