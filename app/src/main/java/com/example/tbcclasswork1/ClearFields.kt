package com.example.tbcclasswork1

import com.example.tbcclasswork1.databinding.ActivityMainBinding

class ClearFields {
    fun clearFields(binding: ActivityMainBinding){
        binding.etEnterAnagrams.text?.clear()
        binding.tvAnagramsNumber.text = ""
        binding.tvOutPut.text = ""
    }
}