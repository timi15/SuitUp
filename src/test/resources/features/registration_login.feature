Feature: Regisztráció és Bejelentkezés

  Mint Stílusos Stella
  Azért, hogy saját fiókom legyen az alkalmazásban
  Szeretném, ha regisztrálhatnék és bejelentkezhetnék,
  hogy biztonságosan elérjem a személyes tartalmaimat.

  Scenario: Sikeres regisztráció
    Given a felhasználó a regisztrációs oldalon van
    When megadja a szükséges adatokat
    And rákattint a "Regisztráció" gombra
    Then a rendszer létrehozza a felhasználói fiókot
    And a felhasználó visszajelzést kap a sikeres regisztrációról

  Scenario: Sikeres bejelentkezés
    Given a felhasználó már regisztrált
    And a bejelentkezési oldalon van
    When megadja a helyes felhasználónevet és jelszavát
    And rákattint a "Bejelentkezés" gombra
    Then a rendszer bejelentkezteti a felhasználót
    And átirányítja a személyes tartalmak oldalára