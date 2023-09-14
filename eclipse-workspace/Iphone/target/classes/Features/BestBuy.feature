Feature: BestBuy Website Automation

  Scenario: Search for iPhone 13 and add to cart
    Given I open the BestBuy website
    When I search for 'Iphone 13' in the top search bar and click the search icon
    And I wait for the search results to load
    And I find an item with description 'Apple - Pre-Owned iPhone 13 Pro 5G 128GB (Unlocked) - Graphite', model 'A2483-GRY', and price '$879.99'
    And I click on 'Add to Cart'
    Then I verify that a popup appears with the text 'Added to cart'

  Scenario: Search for iPhone 12 and add to cart
    Given I open the BestBuy website
    When I search for 'iphone 12' in the top search bar and click the search icon
    And I wait for the search results to load
    And I find an item with description 'Apple - iPhone 12 5G 64GB (Unlocked) - Black', model 'MGF43LL/A', and price '$629.99'
    And I click on 'Add to Cart'
    Then I verify that a popup appears with the text 'Added to cart'
    And I verify that I am on the Cart page by validating the presence of 'Order Summary' and the 'Check out' button
