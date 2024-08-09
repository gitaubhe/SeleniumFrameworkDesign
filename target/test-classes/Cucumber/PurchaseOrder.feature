@tag
Feature: Purchase order from ecommerse website
  I want to use this template for my feature file

Background:
   Given I landed on ecommerse page
   
  @Regression
  Scenario: Submitting order
    Given logged in with username "<name>" and password "<password>"
    When I add product "<productName>" to cart 
    And Checkout "<productName>" and submit the order
    Then message "THANKYOU FOR THE ORDER." is displayed on confirmation page

    Examples: 
      | name 							| password 				| productName  |
      | gita123@gmail.com |     Sangamner_1	| ZARA COAT 3  |
     
