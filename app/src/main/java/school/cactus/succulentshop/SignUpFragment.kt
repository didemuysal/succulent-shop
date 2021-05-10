package school.cactus.succulentshop

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import school.cactus.succulentshop.signup.UsernameValidator
import com.google.android.material.textfield.TextInputLayout
import school.cactus.succulentshop.databinding.FragmentSignupBinding
import school.cactus.succulentshop.signup.IdentifierValidator
import school.cactus.succulentshop.signup.PasswordValidator


class SignUpFragment: Fragment(R.layout.fragment_signup) {

    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!

    private val identifierValidator = IdentifierValidator()
    private val usernameValidator = UsernameValidator()
    private val passwordValidator = PasswordValidator()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            SignUpButton.setOnClickListener {
                if (passwordSignupInputLayout.isValid() and signupEmailInputLayout.isValid() and signupUsernameInputLayout.isValid()) {
                    findNavController().navigate(R.id.action_signUpFragment_to_productListFragment)
                }
            }
        }
        binding.apply {
            alreadyHaveAccountButton.setOnClickListener {
                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment2)
            }
        }

        requireActivity().title = getString(R.string.sign_in)
    }

    private fun TextInputLayout.isValid(): Boolean {
        val errorMessage = validator().validate(editText!!.text.toString())
        error = errorMessage?.resolveAsString()
        isErrorEnabled = errorMessage != null
        return errorMessage == null
    }

    private fun Int.resolveAsString() = getString(this)

    private fun TextInputLayout.validator() = when (this) {
        binding.signupEmailInputLayout -> identifierValidator
        binding.signupUsernameInputLayout -> usernameValidator
        binding.passwordSignupInputLayout -> passwordValidator
        else -> throw IllegalArgumentException("Cannot find any validator for the given TextInputLayout")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


