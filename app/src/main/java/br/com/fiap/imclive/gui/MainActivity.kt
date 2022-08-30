package br.com.fiap.imclive.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.imclive.Client
import br.com.fiap.imclive.R
import br.com.fiap.imclive.databinding.ActivityMainBinding
import br.com.fiap.imclive.viewmodel.MainActivityViewModel
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Associar a nossa view com a viewmodel
        mainActivityViewModel = ViewModelProvider
            .NewInstanceFactory()
            .create(MainActivityViewModel::class.java)

        // Adicionar um listener(ouvinte de evento) aos sliders
        binding.sliderPeso.addOnChangeListener { _, value, _ ->
            mainActivityViewModel.peso.value = value.toDouble()
        }

        binding.sliderAltura.addOnChangeListener { _, value, _ ->
            mainActivityViewModel.altura.value = value.toDouble()
        }

        // Adicionar observadores ao viemodel desta activity
        mainActivityViewModel.peso.observe(this) {
            mainActivityViewModel.calcularImc()
            //binding.textViewImc.text = mainActivityViewModel.imc.value.toString()
        }

        mainActivityViewModel.altura.observe(this) {
            mainActivityViewModel.calcularImc()
            //binding.textViewImc.text = mainActivityViewModel.imc.value.toString()
        }

        mainActivityViewModel.imc.observe(this) { imc ->
            binding.textViewImc.text = String.format("%.2f", imc)
        }

        mainActivityViewModel.status.observe(this) {
            //binding.textViewStatus.text = it
            binding.textViewStatus.text = it
        }

    }
}