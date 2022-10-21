package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFirstBinding
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var surnameTxt: TextInputEditText
    private lateinit var nameTxt: TextInputEditText
    private lateinit var addressTxt: TextInputEditText
    private lateinit var btn: Button
    private lateinit var radioBtn1: RadioButton
    private lateinit var radioBtn2: RadioButton
    private lateinit var radioBtn3: RadioButton
    private lateinit var check1: CheckBox
    private lateinit var check2: CheckBox
    private lateinit var check3: CheckBox
    private var orderSummary:String=""

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn = requireView().findViewById(R.id.button_first)
        radioBtn1 = requireView().findViewById(R.id.radio_button_1)
        radioBtn2 = requireView().findViewById(R.id.radio_button_2)
        radioBtn3 = requireView().findViewById(R.id.radio_button_3)
        check1=requireView().findViewById(R.id.checkbox1)
        check2=requireView().findViewById(R.id.checkbox2)
        check3=requireView().findViewById(R.id.checkbox3)
        surnameTxt = requireView().findViewById(R.id.surname)
        nameTxt = requireView().findViewById(R.id.name)
        addressTxt = requireView().findViewById(R.id.address)

        btn.setOnClickListener {
            val surname = surnameTxt.text.toString()
            val name = nameTxt.text.toString()
            val address = addressTxt.text.toString()
            var options=""

            val size:String = when {
                radioBtn1.isChecked -> "Small"
                radioBtn2.isChecked -> "Medium"
                else -> "Big"
            }
            if (check1.isChecked) options += " Cheese"
            if (check2.isChecked) options += " Pineapple"
            if (check3.isChecked) options += " Pepperoni"
            orderSummary="$name $surname \n$size Pizza\nOptions: $options\nWill be delivered to $address"
            val toast = Toast.makeText(requireContext(), orderSummary, Toast.LENGTH_LONG)
            toast.show()
            val uri= Uri.parse("smsto:12345678")
            val intent= Intent(Intent.ACTION_SENDTO,uri)
            intent.putExtra("sms_body",orderSummary)
            startActivity(intent)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}