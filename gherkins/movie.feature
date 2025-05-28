Feature: Movie management

  Scenario: Create a movie as ADMIN
    Given I am a user with role ADMIN
    When I send a POST request to /api/movies with body:
      | title       | description         | releaseYear | genres         | ageRating |
      | Inception   | Sci-fi thriller     | 2010        | Sci-Fi,Action  | PG-13     |
    Then the response code should be 200
    And the movie is created successfully

  Scenario: Create a movie as USER
    Given I am a user with role USER
    When I send a POST request to /api/movies with body:
      | title     | description     | releaseYear | genres   | ageRating |
      | Matrix    | Action movie    | 1999        | Action   | R         |
    Then the response code should be 403
    And the error message is "Only administrators can create movies."

  Scenario: Get an existing movie by ID
    Given a movie exists with id 1
    When I send a GET request to /api/movies/1
    Then the response code should be 200
    And the response contains:
      | id | title     | description     | releaseYear | genres   | ageRating |
      | 1  | Inception | Sci-fi thriller | 2010        | Sci-Fi   | PG-13     |

  Scenario: Get a non-existing movie by ID
    Given no movie exists with id 999
    When I send a GET request to /api/movies/999
    Then the response code should be 404
    And the error message is "Movie not found with id: 999"

  Scenario: Update a movie as ADMIN
    Given I am a user with role ADMIN
    And a movie exists with id 2
    When I send a PUT request to /api/movies/2 with body:
      | title         | description         | releaseYear | genres      | ageRating |
      | Interstellar  | Space exploration   | 2014        | Sci-Fi      | PG-13     |
    Then the response code should be 200
    And the movie is updated

  Scenario: Update a movie as USER
    Given I am a user with role USER
    And a movie exists with id 2
    When I send a PUT request to /api/movies/2 with body:
      | title         | description         | releaseYear | genres      | ageRating |
      | Interstellar  | Space exploration   | 2014        | Sci-Fi      | PG-13     |
    Then the response code should be 403
    And the error message is "Only administrators can update movies."

  Scenario: Delete a movie as ADMIN
    Given I am a user with role ADMIN
    And a movie exists with id 3
    When I send a DELETE request to /api/movies/3
    Then the response code should be 204

  Scenario: Delete a non-existing movie as ADMIN
    Given I am a user with role ADMIN
    And no movie exists with id 999
    When I send a DELETE request to /api/movies/999
    Then the response code should be 404
    And the error message is "Movie not found with id: 999"

  Scenario: Delete a movie as USER
    Given I am a user with role USER
    And a movie exists with id 3
    When I send a DELETE request to /api/movies/3
    Then the response code should be 403
    And the error message is "Only administrators can delete movies."