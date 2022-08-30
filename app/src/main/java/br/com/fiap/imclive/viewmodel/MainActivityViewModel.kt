package br.com.fiap.imclive.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.imclive.Client
import kotlin.math.pow

class MainActivityViewModel: ViewModel() {

    private val mPeso = MutableLiveData<Double>()
    var peso = mPeso

    private val mAltura = MutableLiveData<Double>()
    var altura = mAltura

    private val mImc = MutableLiveData<Double>()
    var imc = mImc

    private val mStatus = MutableLiveData<String>()
    var status = mStatus

    //private val mClient = MutableLiveData<Client>()

    init {
        mPeso.value = 0.0
        mAltura.value = 1.0
        mImc.value = 0.0
        mStatus.value = "Sem status"
    }

    fun calcularImc() {
        mImc.value = peso.value!! / (altura.value!!/100).pow(2)
        determinarStatus()
    }

    fun determinarStatus() {
        if (mImc.value!! <= 18.5) {
            mStatus.value = "Abaixo do peso!"
        } else if (mImc.value!! > 18.5 && mImc.value!! < 25.0) {
            mStatus.value = "Peso ideal!"
        } else {
            mStatus.value = "Acima do peso!"
        }
    }

}