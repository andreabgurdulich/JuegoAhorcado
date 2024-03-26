Feature: Jugar al Ahorcado
  
  Scenario: Adivinar una letra correcta
    Given que inició el juego del ahorcado con la palabra "CASA"
    When el jugador intenta adivinar la letra "A"
    Then el estado del juego debería ser "_A_A"
    
    Scenario: Adivinar una letra incorrecta
    Given que inició el juego del ahorcado con la palabra "CASA"
    When el jugador intenta adivinar la letra "J"
    Then el estado del juego debería ser "_ _ _ _"
    And el jugador sumó un intento incorrecto
    
    Scenario: Adivinar la última letra correcta
    Given que inició el juego del ahorcado con la palabra "CASA"
    When el jugador intenta adivinar la letra "C"
    And el jugador intenta adivinar la letra "A"
    And el jugador intenta adivinar la letra "S"
    Then el estado del juego debería ser "CASA"
    And el jugador debería ganar el juego
    
    Scenario: Adivinar la última letra incorrecta
    Given que inició el juego del ahorcado con la palabra "CASA"
    When el jugador intenta adivinar la letra "E"
    And el jugador intenta adivinar la letra "R"
    And el jugador intenta adivinar la letra "T"
    And el jugador intenta adivinar la letra "P"
    And el jugador intenta adivinar la letra "L"
    And el jugar intenta adivinar la letra "I"
    Then el estado del juego debería ser "_ _ _ _"
    And el jugador debería perder el juego
    
    