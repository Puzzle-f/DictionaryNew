package com.example.dictionarynew

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionarynew.databinding.ActivityMainBinding
import com.example.dictionarynew.interactor.MainInteractor
import com.example.dictionarynew.model.DataModel
import com.example.dictionarynew.utils.network.isOnline
import com.example.dictionarynew.view.BaseActivity
import com.example.dictionarynew.view.MainAdapter
import com.example.dictionarynew.view.SearchDialogFragment
import com.example.dictionarynew.view.description.DescriptionActivity
import com.example.dictionarynew.viewmodel.MainViewModel
import com.example.dictionarynew.viewmodel.convertMeaningsToString
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

//class MainActivity : BaseActivity<AppState, MainInteractor>() {
//
//    override lateinit var model: MainViewModel
//
//    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) } // Адаптер для отображения списка
//    private val fabClickListener: View.OnClickListener =
//        View.OnClickListener {
//            val searchDialogFragment = SearchDialogFragment.newInstance()
//            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
//            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
//
//        }
//
//    private var _vb: ActivityMainBinding? = null
//    private val vb: ActivityMainBinding get() = _vb!!
//
//    // вариантов перевода
//    // Обработка нажатия элемента списка
//    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
//        object : MainAdapter.OnListItemClickListener {
//            override fun onItemClick(data: DataModel) {
//                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_main)
//        _vb = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(vb.root)
//        iniViewModel()
//        initViews()
//    }
//
//    private fun iniViewModel() {
//        if (vb.mainActivityRecyclerview.adapter != null) {
//            throw IllegalStateException("The ViewModel should be initialised first")
//        }
//        val viewModel: MainViewModel by viewModel()
//        model = viewModel
//        model.subscribe().observe(this@MainActivity,{ renderData(it) })
//    }
//
//    private fun initViews() {
//        vb.searchFab.setOnClickListener {
//            val searchDialogFragment = SearchDialogFragment.newInstance()
//            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
//            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
//
//        }
//        vb.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
////        main_activity_recyclerview.layoutManager = LinearLayoutManager(applicationContext)
//        vb.mainActivityRecyclerview.adapter = adapter
////        main_activity_recyclerview.adapter = adapter
//    }
//
//    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
//        object : SearchDialogFragment.OnSearchClickListener {
//            override fun onClick(searchWord: String) {
//                isNetworkAvailable = isOnline(applicationContext)
//                if (isNetworkAvailable) {
//                    model.getData(searchWord, isNetworkAvailable)
//                } else {
//                    showNoInternetConnectionDialog()
//                }
//            }
//        }
//
//
////    private fun subscribeToViewModel() {
////        // Подписываемся на уведомления от ViewModel
////        model.getData().observe(this, Observer { data ->
////            // Как только данные получены, делаем с data всё, что нам нужно
////        })
////    }
//
//    // Переопределяем базовый метод
//    override fun renderData(appState: AppState) {
//        when (appState) {
//            is AppState.Success -> {
//                showViewWorking()
//                val data = appState.data
//                if (data.isNullOrEmpty()) {
//                    showAlertDialog(
//                        getString(R.string.dialog_title_sorry),
//                        getString(R.string.empty_server_response_on_success)
//                    )
//                } else {
//                    adapter.setData(data)
//                }
//            }
//            is AppState.Loading -> {
//                showViewLoading()
//                if (appState.progress != null) {
//                    vb.progressBarHorizontal.visibility = VISIBLE
//                    vb.progressBarRound.visibility = GONE
//                    vb.progressBarHorizontal.visibility = appState.progress
//                } else {
//                    vb.progressBarHorizontal.visibility = GONE
//                    vb.progressBarRound.visibility = VISIBLE
//                }
//            }
//            is AppState.Error -> {
//                showViewWorking()
//                showAlertDialog(getString(R.string.error_stub), appState.error.message)
//            }
//        }
//    }
//
//    private fun showErrorScreen(error: String?) {
//        showViewError()
//        vb.errorTextview.text = error ?: getString(R.string.undefined_error)
//        vb.reloadButton.setOnClickListener {
//            model.getData("hi", true)
//        }
//    }
//
//    private fun showViewSuccess() {
//        vb.successLinearLayout.visibility = VISIBLE
//        vb.loadingFrameLayout.visibility = GONE
//        vb.errorLinearLayout.visibility = GONE
//    }
//
//    private fun showViewLoading() {
//        vb.successLinearLayout.visibility = GONE
//        vb.loadingFrameLayout.visibility = VISIBLE
//        vb.errorLinearLayout.visibility = GONE
//    }
//
//    private fun showViewError() {
//        vb.successLinearLayout.visibility = GONE
//        vb.loadingFrameLayout.visibility = GONE
//        vb.errorLinearLayout.visibility = VISIBLE
//    }
//
//    private fun showViewWorking() {
//        vb.loadingFrameLayout.visibility = GONE
//    }
//
//    companion object {
//        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
//            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
//    }
//
//}


// !!! *** lesson3 *** !!!

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    override lateinit var model: MainViewModel

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

//    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
//        object : MainAdapter.OnListItemClickListener {
//            override fun onItemClick(data: DataModel) {
//                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
//            }
//        }

    // Слушатель получает от адаптера необходимые данные и запускает новый экран
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings[0].imageUrl
                    )
                )
            }
        }


    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniViewModel()
        initViews()
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    showAlertDialog(
                        getString(R.string.dialog_title_sorry),
                        getString(R.string.empty_server_response_on_success)
                    )
                } else {
                    adapter.setData(data)
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    progress_bar_horizontal.visibility = VISIBLE
                    progress_bar_round.visibility = GONE
                    progress_bar_horizontal.progress = appState.progress
                } else {
                    progress_bar_horizontal.visibility = GONE
                    progress_bar_round.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    private fun iniViewModel() {
        if (main_activity_recyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        search_fab.setOnClickListener(fabClickListener)
        main_activity_recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        main_activity_recyclerview.adapter = adapter
    }

    private fun showViewWorking() {
        loading_frame_layout.visibility = GONE
    }

    private fun showViewLoading() {
        loading_frame_layout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}
