package com.nickel.bpch.core.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.nickel.bpch.core.ui.theme.BPCHTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
    text: String,
    hint: String = "",
    error: String = "",
    onValueChange: (String) -> Unit,
    onTextVisibilityChange: () -> Unit = {},
    textVisible: Boolean = false,
    style: TextStyle = MaterialTheme.typography.bodySmall,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: ImageVector? = null
) {
    TextField(
        value = text,
        placeholder = {
            Text(
                text = hint,
                style = style
            )
        },
        onValueChange = onValueChange,
        textStyle = style,
        singleLine = singleLine,
        isError = error != "",
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        visualTransformation = if (keyboardType == KeyboardType.Password && !textVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        leadingIcon = if (leadingIcon != null) {
            val icon: @Composable () -> Unit = {
                Icon(imageVector = leadingIcon, contentDescription = null)
            }
            icon
        } else null,
        trailingIcon = if (keyboardType == KeyboardType.Password) {
            val icon: @Composable () -> Unit = {
                IconButton(
                    onClick = onTextVisibilityChange
                ) {
                    Icon(
                        imageVector = if (textVisible) {
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        },
                        contentDescription = null
                    )
                }
            }
            icon
        } else null
    )
}

@Preview
@Composable
private fun LightModePreview() {
    BPCHTheme {
        StandardTextField(
            text = "",
            hint = "example hint",
            onValueChange = {},
            leadingIcon = Icons.Filled.Mail
        )
    }

}
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DarkModePreview() {
    BPCHTheme {
        StandardTextField(
            text = "",
            hint = "example hint",
            onValueChange = {},
            leadingIcon = Icons.Filled.Mail
        )
    }
}