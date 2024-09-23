package com.example.mykotlinapp.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mykotlinapp.BaseActivity

class TestActivity : BaseActivity() {

    private val TAG = "TestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"Some How it Coming to the TestActivity")
        setContent{
            MyApp()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(showBackground = true)
    @Composable
    fun MyApp() {
        // Scaffold gives a structure with a TopAppBar
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("My Action Bar") }, // Title of the action bar
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,  // Action bar background color
                        titleContentColor = Color.White  // Action bar text color
                    )
                )
            },
            content = { padding ->
                // Main content of the screen goes here
                Column(
                    modifier = Modifier.fillMaxSize().padding(padding)
                ) {
                    Text(
                        text = "Hi Balaji",
                        color = Color.Black
                    )
                }
            }
        )
    }
}
