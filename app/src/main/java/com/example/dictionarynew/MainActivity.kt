package com.example.dictionarynew

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionarynew.databinding.ActivityMainBinding
import com.example.dictionarynew.interactor.MainInteractor
import com.example.dictionarynew.model.DataModel
import com.example.dictionarynew.view.BaseActivity
import com.example.dictionarynew.view.MainAdapter
import com.example.dictionarynew.view.SearchDialogFragment
import com.example.dictionarynew.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

//    override val model: MainViewModel by lazy {
//        ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
//    }
    override lateinit var model: MainViewModel

    private val observer = Observer<AppState> { renderData(it) }

    private var adapter: MainAdapter? = null // Адаптер для отображения списка

    private var _vb: ActivityMainBinding? = null
    private val vb: ActivityMainBinding get() = _vb!!

    // вариантов перевода
    // Обработка нажатия элемента списка
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        model = viewModelFactory.create(MainViewModel::class.java)
//        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })

        vb.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    model.getData(searchWord, true).observe(this@MainActivity, observer)
                }
            })
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

//        subscribeToViewModel()
    }


//    private fun subscribeToViewModel() {
//        // Подписываемся на уведомления от ViewModel
//        model.getData().observe(this, Observer { data ->
//            // Как только данные получены, делаем с data всё, что нам нужно
//        })
//    }

    // Переопределяем базовый метод
    override fun renderData(appState: AppState) {
        // В зависимости от состояния модели данных (загрузка, отображение,
        // ошибка) отображаем соответствующий экран
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        vb.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        vb.mainActivityRecyclerview.adapter =
                            MainAdapter(dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                // Задел на будущее, если понадобится отображать прогресс
                // загрузки
                if (appState.progress != null) {
                    vb.progressBarHorizontal.visibility = VISIBLE
                    vb.progressBarRound.visibility = GONE
                    vb.progressBarHorizontal.progress = appState.progress
                } else {
                    vb.progressBarHorizontal.visibility = GONE
                    vb.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        vb.errorTextview.text = error ?: getString(R.string.undefined_error)
        vb.reloadButton.setOnClickListener {
            model.getData("hi", true)
        }
    }

    private fun showViewSuccess() {
        vb.successLinearLayout.visibility = VISIBLE
        vb.loadingFrameLayout.visibility = GONE
        vb.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        vb.successLinearLayout.visibility = GONE
        vb.loadingFrameLayout.visibility = VISIBLE
        vb.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        vb.successLinearLayout.visibility = GONE
        vb.loadingFrameLayout.visibility = GONE
        vb.errorLinearLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

}
