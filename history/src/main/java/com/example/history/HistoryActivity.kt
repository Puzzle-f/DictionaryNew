package com.example.history


import android.os.Bundle
import android.view.View
import com.example.core.viewmodel.BaseActivity
import com.example.model.AppState
import com.example.model.DataModel
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.loading_layout_history.*
import org.koin.android.scope.currentScope

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (history_activity_recyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
//        injectDependenciesHistory()
        val vm: HistoryViewModel by currentScope.inject()
//        val viewModel: com.example.history.HistoryViewModel by viewModel()
//        model = viewModel
        model = vm
        model.subscribe()
            .observe(this@HistoryActivity, { renderData(it) })
    }

    private fun initViews() {
        history_activity_recyclerview.adapter = adapter
    }

    override fun showViewLoading(progress: Int?) {
        with(history_search_word_loading_layout) {
//            history_loading_frame_layout.visibility = View.VISIBLE
            if (progress != null) {
                progress_bar_horizontal.visibility = View.VISIBLE
                progress_bar_round.visibility = View.GONE
                progress_bar_horizontal.progress = progress
            } else {
                progress_bar_horizontal.visibility = View.GONE
                progress_bar_round.visibility = View.VISIBLE
            }
        }
    }

    override fun showViewWorking() {
//        main_loading_layout.history_loading_frame_layout.visibility = View.GONE
    }
}
