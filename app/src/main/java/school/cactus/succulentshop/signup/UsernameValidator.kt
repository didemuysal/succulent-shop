package school.cactus.succulentshop.signup

import school.cactus.succulentshop.R

import school.cactus.succulentshop.validation.Validator

class UsernameValidator : Validator {
    override fun validate(field: String) = when {
        field.isEmpty() -> R.string.required_username
        field.length < 4 -> R.string.username_short
        else -> null
    }


    override fun signupValidate(field: String) : Int? {
        val a = field.replace("_","")
        var b : Int? = null
        when {
            field.isEmpty() -> R.string.this_field_is_required
            !a.all { it.isLetterOrDigit() } -> b = R.string.username_rules
            field.isEmpty() -> b = R.string.required_username
            field.length < 4 -> b = R.string.username_short
            field.length > 20 -> b = R.string.username_long
            else -> null
        }
        return b
    }



}