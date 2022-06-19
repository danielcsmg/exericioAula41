package br.com.zup.aplicacao.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.zup.aplicacao.R
import br.com.zup.aplicacao.databinding.FragmentPrimeiroBinding
import br.com.zup.aplicacao.fragments.model.NumeroDouble

class PrimeiroFragment : Fragment() {
    private lateinit var binding: FragmentPrimeiroBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrimeiroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mostrarInformaoes()
        receberDados()
    }

    private fun mostrarInformaoes() {
        try {
            val args = PrimeiroFragmentArgs.fromBundle(requireArguments())
            binding.tvArgumentos.text = "Argumentos: ${args.texto} - ${args.texto2}"

        } catch (e: Exception) {
            binding.tvArgumentos.text = "Argumentos aparecerão aqui"
        }
    }

    private fun receberDados() {
        clickBotao(
            binding.btnFragmentDois,
            binding.etArgumentoUm,
            binding.etArgumentoDois,
            this::irParaFragmentDois
        )
        clickBotao(
            binding.btnFragmentTres,
            binding.etArgumentoUm,
            binding.etArgumentoDois,
            this::irParaFragmentTres
        )

    }

    private fun enviarDadosFragmentDois(inteiro: Int, boolean: Boolean) {
        val action =
            PrimeiroFragmentDirections.actionPrimeiroFragmentToSegundoFragment(inteiro, boolean)
        findNavController().navigate(action)
    }

    private fun enviarDadosFragmentTres(double1: Double, double2: Double) {
        val numeroDouble = NumeroDouble(double1, double2)

        val action =
            PrimeiroFragmentDirections.actionPrimeiroFragmentToTerceiroFragment(numeroDouble)
        findNavController().navigate(action)
    }

    private fun irParaFragmentDois(editText1: EditText, editText2: EditText) {
        val arg1 = editText1.text.toString()
        val arg2 = editText2.text.toString()
        try {
            enviarDadosFragmentDois(arg1.toInt(), arg2.toBoolean())
        } catch (e: Exception) {
            binding.etArgumentoUm.error = "Deve ser um inteiro"
            binding.etArgumentoDois.error = "Deve ser um booleano"
        }
    }

    private fun irParaFragmentTres(editText1: EditText, editText2: EditText) {
        val arg1 = editText1.text.toString()
        val arg2 = editText2.text.toString()
        try {
            enviarDadosFragmentTres(arg1.toDouble(), arg2.toDouble())
        } catch (e: Exception) {
            binding.etArgumentoUm.error = "Deve ser um double"
            binding.etArgumentoDois.error = "Deve ser um double"
        }
    }

    private fun clickBotao(
        btn: Button,
        arg1: EditText,
        arg2: EditText,
        function: (arg1: EditText, arg2: EditText) -> Unit
    ) {
        btn.setOnClickListener {
            function(arg1, arg2)
        }
    }
}