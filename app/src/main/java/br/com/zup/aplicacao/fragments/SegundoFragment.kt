package br.com.zup.aplicacao.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.zup.aplicacao.databinding.FragmentSegundoBinding
import br.com.zup.aplicacao.fragments.model.NumeroDouble

class SegundoFragment : Fragment() {
    private lateinit var binding: FragmentSegundoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSegundoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mostrarInformaoes()
        receberDados()
    }

    private fun mostrarInformaoes() {
        val args = SegundoFragmentArgs.fromBundle(requireArguments())
        binding.tvArgumentos.text = "Argumentos: ${args.inteiro} - ${args.booleano}"

    }

    private fun receberDados() {
        clickBotao(
            binding.btnFragmentUm,
            binding.etArgumentoUm,
            binding.etArgumentoDois,
            this::irParaFragmentUm
        )
        clickBotao(
            binding.btnFragmentTres,
            binding.etArgumentoUm,
            binding.etArgumentoDois,
            this::irParaFragmentTres
        )

    }

    private fun enviarDadosFragmentUm(texto1: String, texto2: String) {
        val action =
            SegundoFragmentDirections.actionSegundoFragmentToPrimeiroFragment(texto1, texto2)
        findNavController().navigate(action)
    }

    private fun enviarDadosFragmentTres(double1: Double, double2: Double) {
        val numeroDouble = NumeroDouble(double1, double2)

        val action =
            SegundoFragmentDirections.actionSegundoFragmentToTerceiroFragment(numeroDouble)
        findNavController().navigate(action)
    }

    private fun irParaFragmentUm(editText1: EditText, editText2: EditText) {
        val arg1 = editText1.text.toString()
        val arg2 = editText2.text.toString()
        try {
            enviarDadosFragmentUm(arg1, arg2)
        } catch (e: Exception) {
            binding.etArgumentoUm.error = "Deve ser um texto"
            binding.etArgumentoDois.error = "Deve ser um texto"
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