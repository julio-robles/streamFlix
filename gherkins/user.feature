Feature: User management

  Scenario: Create a new user
    When I send a POST request to /api/users with body:
      | username | email           | password  |
      | juan     | juan@mail.com   | secret123 |
    Then the response code should be 200
    And the user is created successfully

  Scenario: Get an existing user by ID
    Given a user exists with id 1
    When I send a GET request to /api/users/1
    Then the response code should be 200
    And the response contains:
      | id | username | email         |
      | 1  | juan     | juan@mail.com |

  Scenario: Get a non-existing user by ID
    Given no user exists with id 999
    When I send a GET request to /api/users/999
    Then the response code should be 404

  Scenario: List all users
    Given several users are registered
    When I send a GET request to /api/users
    Then the response code should be 200
    And the response contains a list of users