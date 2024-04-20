package com.example.testovoezadanie.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import com.example.testovoezadanie.R
import com.example.testovoezadanie.presentation.helper.MyEpController
import com.example.testovoezadanie.presentation.helper.fragmentCustomModel
import kotlinx.android.synthetic.main.epoxy_fragment_main.*


class MainFragment : BaseMvRxFragment(){

    private val viewModel: MainFragmentViewModel by fragmentViewModel()
    private val epoxyController: MyEpController by lazy{MyEpController(requireContext())}

    companion object{
        private const val TAG = "MainFragment"
    }


    override fun invalidate() {
        withState(viewModel) {
            epoxyController.withModels {
                Log.d(TAG, "invalidate: called")
                hour_text_view.text = it.hour.toString()
                fragmentCustomModel {
                    id("custom")
                    hours(it.hour.toString())
                    minutes(it.min.toString())
                    seconds(it.sec.toString())
                }

            }
        }

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.epoxy_fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toast_button.setOnClickListener {
            viewModel.showToastWithTime(requireContext())
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposables.clear()
    }


}