package com.example.mykotlinapp.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.mykotlinapp.BaseActivity
import com.example.mykotlinapp.MyApplication
import com.example.mykotlinapp.model.dto.ArticleDTO
import com.example.mykotlinapp.model.dto.NewsResponceDTO
import com.example.mykotlinapp.ui.screens.homeScreenComposable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class HomeActivity : BaseActivity() {

    private val compositeDisposable = CompositeDisposable()
    var totalRecordsState = mutableStateOf("Loading...")
    private var record by mutableStateOf<List<ArticleDTO>>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            homeScreenComposable(totalRecordsState.value, record)
        }
        fetchNews()
    }

    private fun fetchNews() {
        val apiService = (application as MyApplication).newsApiService
        val disposable = apiService.getNews(
            "tesla", "2024-09-01", "publishedAt", "b5da8eca4ff548788f031e37c19bed7a"
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val responseCode = response.code()
                if (response.isSuccessful) {
                    val NewsResponceDTO = response.body()
                    if (NewsResponceDTO != null) {
                        Log.i("HomeActivity", "NewsResponceDTO: $NewsResponceDTO.")
                        updateTotalRecords(NewsResponceDTO)
                    } else {
                        showError("No news data available")
                    }
                } else {
                    showError("Error $responseCode: ${response.message()}")
                }
            }, { error ->
                showError("An error occurred: ${error.message}")
            })

        compositeDisposable.add(disposable)
    }

    private fun updateTotalRecords(newsData: NewsResponceDTO) {
        var result = "Total Records: ${newsData.totalResults}"
        Log.i("HomeActivity", "updateTotalRecords: $result.")
        totalRecordsState.value = result
        record = newsData.articles

    }

    private fun showError(message: String) {
        // Code to display error message to the user
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()  // Dispose disposables to avoid memory leaks
    }

}