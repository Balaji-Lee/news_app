package com.example.mykotlinapp.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mykotlinapp.BaseActivity
import com.example.mykotlinapp.MyApplication
import com.example.mykotlinapp.R
import com.example.mykotlinapp.model.dto.NewsResponceDTO

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class HomeActivity : BaseActivity() {

    private val compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fetchNews()
    }

    private fun fetchNews() {
        // Get newsApiService from MyApplication
        val apiService = (application as MyApplication).newsApiService

        val disposable = apiService.getNews(
            "apple",
            "2024-09-01",
            "popularity",
            "b5da8eca4ff548788f031e37c19bed7a"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val responseCode = response.code()
                if (response.isSuccessful) {
                    // Process the successful response
                    val NewsResponceDTO = response.body()
                    if (NewsResponceDTO != null) {
                        // Update UI with news data
                        updateTotalRecords(NewsResponceDTO)
                    } else {
                        // Handle case where newsData is null
                        showError("No news data available")
                    }
                } else {
                    // Handle unsuccessful response
                    showError("Error $responseCode: ${response.message()}")
                }
            }, { error ->
                // Handle error
                showError("An error occurred: ${error.message}")
            })

        compositeDisposable.add(disposable)
    }

    private fun updateTotalRecords(newsData: NewsResponceDTO) {
        findViewById<TextView>(R.id.total_record).text = "Total Records: ${newsData.totalResults}"
        // Code to update UI with the news data
    }

    private fun showError(message: String) {
        // Code to display error message to the user
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()  // Dispose disposables to avoid memory leaks
    }
}