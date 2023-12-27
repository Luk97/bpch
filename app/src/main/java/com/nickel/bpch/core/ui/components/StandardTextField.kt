package com.nickel.bpch.core.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import com.nickel.bpch.core.ui.theme.carlito

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
    text: String,
    hint: String = "",
    error: String = "",
    onValueChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit = {},
    passwordVisible: Boolean = false,
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
        visualTransformation = if (keyboardType == KeyboardType.Password && !passwordVisible) {
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
                    onClick = onPasswordVisibilityChange
                ) {
                    Icon(
                        imageVector = if (passwordVisible) {
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
private fun StandardTextFieldPreview() {
    StandardTextField(
        text = "",
        hint = "example hint",
        style = TextStyle(fontFamily = carlito),
        onValueChange = {},
    )
}