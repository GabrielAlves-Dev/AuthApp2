package com.gabriel.authapp2.data

// Oi, Victor :)
// Eu adicionei essa classe pq quando eu usava o botão de sair,
// ele não apagava os dados do usuario anterior, ai pra ter um
// algo mais consistente, fiz uma logica mais robusta
sealed class RegistrationResult {
    object Success : RegistrationResult()
    object ErrorEmailAlreadyInUse : RegistrationResult()
    object ErrorWeakPassword : RegistrationResult()
    object ErrorInvalidEmail : RegistrationResult()
    data class ErrorGeneric(val message: String?) : RegistrationResult()
}