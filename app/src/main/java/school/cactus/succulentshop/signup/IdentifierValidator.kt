package school.cactus.succulentshop.signup

import school.cactus.succulentshop.R
import school.cactus.succulentshop.validation.Validator

 class IdentifierValidator : Validator {
    override fun validate(field: String) = when {
        field.isEmpty() -> R.string.this_field_is_required
        field.length < 5 -> R.string.identifier_is_too_short
        else -> null
    }
    override fun signupValidate(field: String) = when {
        field.isEmpty() -> R.string.email_required
        field.length < 5 || field.length > 50 -> R.string.invalid_email
        (!field.contains("@") ) -> R.string.invalid_email
        else -> null
    }
}