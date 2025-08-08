Feature: Magento Sign-Up and Sign-In

  Scenario: User signs up and signs in successfully
    Given user launch the Magento website
    When user navigate to the Create Account page
    And user enter valid user details
    And user submit the registration form
    Then user should see a successful account creation message
    Given user launch the Magento login page
    When user enter the same credentials used during sign-up
    And user click the Sign In button
    Then user should be logged in successfully

