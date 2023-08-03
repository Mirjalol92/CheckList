package com.mirsalim.managing_checklist.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mirsalim.managing_checklist.databinding.FragmentMainBinding
import com.mirsalim.managing_checklist.ui.base.BaseFragment
import com.mirsalim.managing_checklist.ui.main.adapter.FruitAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate){

    private val viewModel by viewModels<MainViewModel>()

    private val adapter = FruitAdapter{
        viewModel.updateList(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRv()
        initObservers()
        initLayout()
    }

    private fun initLayout(){
        binding.checkAll.setOnClickListener {
            if (binding.checkAll.isChecked){
                binding.unAll.isChecked = false
            }
            viewModel.checkAll(binding.checkAll.isChecked)
        }

        binding.unAll.setOnClickListener{
            if(binding.unAll.isChecked)
                viewModel.unCheckAll()
        }
    }

    private fun initObservers(){
        viewModel.fruitList.observe(viewLifecycleOwner){
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }

        viewModel.fruitsListText.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                binding.unAll.isChecked = false
            }
            binding.tvSelectedFruits.text = it.removeSuffix(", ")
        }

        viewModel.isAllSelected.observe(viewLifecycleOwner){
            binding.checkAll.isChecked = it
        }
    }

    private fun setRv(){
        binding.checkboxList.adapter = adapter
    }
}