package br.com.zup.aplicacao.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import br.com.zup.aplicacao.databinding.FragmentTerceiroBinding

class TerceiroFragment : Fragment() {
    private lateinit var binding: FragmentTerceiroBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTerceiroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mostrarInformaoes()
        receberDados()
    }

    private fun mostrarInformaoes() {
        val args = TerceiroFragmentArgs.fromBundle(requireArguments())
        binding.tvArgumentos.text = "Argumentos: ${args.double.double1} - ${args.double.double2}"

    }

    private fun receberDados() {
        clickBotao(
            binding.btnFragmentUm,
            binding.etArgumentoUm,
            binding.etArgumentoDois,
            this::irParaFragmentUm
        )
        clickBotao(
            binding.btnFragmentDois,
            binding.etArgumentoUm,
            binding.etArgumentoDois,
            this::irParaFragmentDois
        )

    }

    private fun enviarDadosFragmentUm(texto1: String, texto2: String) {
        val action =
            TerceiroFragmentDirections.actionTerceiroFragmentToPrimeiroFragment(texto1, texto2)
        findNavController().navigate(action)
    }

    private fun enviarDadosFragmentDois(inteiro: Int, boolean: Boolean) {
        val action =
            TerceiroFragmentDirections.actionTerceiroFragmentToSegundoFragment(inteiro, boolean)
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