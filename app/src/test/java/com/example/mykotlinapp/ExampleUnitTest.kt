package com.example.mykotlinapp

import ProgressDialog
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    // Create a Compose Test Rule
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testProgressDialog_isDisplayed() {
        // Set the content with the ProgressDialog Composable
        composeTestRule.setContent {
            ProgressDialog()
        }

        // Use a testing tag or description for identification
        composeTestRule
            .onNodeWithTag("progress_dialog") // Use the test tag assigned to your composable
            .assertIsDisplayed() // Verify if the dialog is displayed
    }
}