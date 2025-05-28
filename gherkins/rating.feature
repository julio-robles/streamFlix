Feature: Rating management

  Scenario: Add a rating to a movie
    Given I am an authenticated user
    And a movie exists with id 1
    When I send a POST request to /api/ratings/movie/1 with body:
      | score | comment         |
      | 5     | Excellent film! |
    Then the response code should be 200
    And the rating is added successfully

  Scenario: Get ratings for a movie
    Given a movie exists with id 1 and has ratings
    When I send a GET request to /api/ratings/movie/1
    Then the response code should be 200
    And the response contains a list of ratings

  Scenario: Get ratings by user
    Given a user exists with id 2 and has ratings
    When I send a GET request to /api/ratings/user/2
    Then the response code should be 200
    And the response contains a list of ratings

  Scenario: Delete my own rating
    Given I am an authenticated user
    And a rating exists with id 10 created by me
    When I send a DELETE request to /api/ratings/10
    Then the response code should be 204

  Scenario: Delete another user's rating
    Given I am an authenticated user
    And a rating exists with id 11 created by another user
    When I send a DELETE request to /api/ratings/11
    Then the response code should be 403
    And the error message is "You cannot delete ratings from other users."