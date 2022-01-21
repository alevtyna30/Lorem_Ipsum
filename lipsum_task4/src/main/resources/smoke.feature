Feature: Smoke
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check that current word appears in the first paragraph
    Given User opens '<mainPage>' page
    And User choose language
    When User open generated page
    Then User checks that current page contains '<searchKeyword>'

    Examples:
      | mainPage                | searchKeyword |
      | https://www.lipsum.com/ | рыба          |

  Scenario Outline: Check that default setting result according to expectations
    Given User opens '<homePage>' page
    And User clicks submit button
    When User open generated page
    Then User checks that first paragraph starts with '<searchText>'

    Examples:
      | homePage                | searchText                                             |
      | https://www.lipsum.com/ | Lorem ipsum dolor sit amet, consectetur adipiscing elit|

  Scenario Outline: Check that results in checkbox no stars with Lorem Ipsum
    Given User opens '<homePage>' page
    And User clicks checkbox button
    When User open generated page
    Then User checks that text not starts with '<searchWords>'

    Examples:
      | homePage                | searchWords|
      | https://www.lipsum.com/ | Lorem ipsum|

  Scenario Outline: Check generation words
    Given User opens '<homePage>' page
    And User choose element '<searchKeyword>' in the radio button group
    And User input '<number>' of chosen element
    And User clicks submit button
    When User open generated link
    Then User checks that generated text contains '<number>'

    Examples:
      | homePage                |searchKeyword|number|
      | https://www.lipsum.com/ |bytes        |10    |


