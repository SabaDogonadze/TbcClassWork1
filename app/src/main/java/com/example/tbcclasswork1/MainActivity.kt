package com.example.tbcclasswork1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tbcclasswork1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var anagrams = mutableListOf<Anagram>()
    private var listedAnagram = mutableListOf<MutableList<Anagram>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp() {
        listeners()
    }

    private fun listeners() {
        binding.btnSave.setOnClickListener {
            val enteredWord = binding.etEnterAnagrams.text.toString()
            if (enteredWord.isNotEmpty()) {
                /*  enteredWords(enteredWord)    if we want to add anagram words one by one*/
                val anagramWords = enteredWord.split(",")
                for (words in anagramWords) {
                    enteredWords(words)
                }
            }
        }
        binding.btnOutPut.setOnClickListener {
            addingAnagramsInList()
            showAnagramWords()
            showGroupedAnagrams()
            listCounter()
        }

        binding.btnClear.setOnClickListener {
            ClearFields().clearFields(binding)
            anagrams.clear()
            listedAnagram.clear()

        }
    }


    private fun enteredWords(enteredWord: String): MutableList<Anagram> {
        val input = Anagram(enteredWord)
        anagrams.add(input)
        return anagrams
    }


    private fun addingAnagramsInList() {
        for (words in anagrams) {
            val anagramSize = words.text.length
            val hasSameWords = anagrams.filter {
                it.text.length == anagramSize && it.text.toCharArray()
                    .sorted() == words.text.toCharArray().sorted()
            }
            if (hasSameWords.isNotEmpty()) {
                listedAnagram.add(hasSameWords.toMutableList())
            }
        }
    }

    private fun listCounter() {
        val listCounter = listedAnagram.distinct().size
        binding.tvAnagramsNumber.text = listCounter.toString()
    }

    private fun showAnagramWords() {
        val outputText = buildString {
            append("Entered Words: ${anagrams.joinToString(", ") { it.text }}")
        }
        binding.tvOutPut.text = outputText
    }

    private fun showGroupedAnagrams() {
        val outputText = buildString {
            append("\n\nGrouped Words: \n")
            for (group in listedAnagram) {
                append("Group: ${group.joinToString(", ") { it.text }}\n")
            }
        }
        binding.tvOutPut.text = outputText
    }

}