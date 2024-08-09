
@tag
Feature: Login error validation feature
  I want to use this template for my feature file
  
Background:
   Given I landed on ecommerse page
   
  @ErrorValidation
  Scenario: Error validation for login
   When logged in with username "<name>" and password "<password>"
   Then "Incorrect email or password." error message shall be displayed
 Examples: 
      | name 							| password 				| productName  |
      | gita123@gmail.com |     Sangamner_1	| ZARA COAT 3  |
     