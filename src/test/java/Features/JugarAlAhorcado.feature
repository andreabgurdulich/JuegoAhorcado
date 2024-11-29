Feature: Jugar al Ahorcado
  
  Scenario: Adivinar una letra correcta
    Given que inició el juego del ahorcado con la palabra "PELO"
    When el jugador intenta adivinar la letra "P"
    Then la palabra oculta debería ser "P___"
    
    Scenario: Adivinar una letra incorrecta
    Given que inició el juego del ahorcado con la palabra "SAPO"
    When el jugador intenta adivinar la letra "J"
    Then el jugador sumó un intento incorrecto
    
    Scenario: Adivinar la última letra correcta
    Given que inició el juego del ahorcado con la palabra "CASA"
    When el jugador intenta adivinar la letra "C"
    And el jugador intenta adivinar la letra "A"
    And el jugador intenta adivinar la letra "S"
    Then el estado del juego debería ser "CASA"
		And el jugador debería ganar el juego
    
		Scenario: Adivinar la última letra incorrecta
    Given que inició el juego del ahorcado con la palabra "GATO" y ya tiene cinco intentos incorrectos
    When el jugador intenta adivinar la letra "I"
    Then el estado del juego debería ser "GATO"
    And el jugador debería perder el juego
    
    