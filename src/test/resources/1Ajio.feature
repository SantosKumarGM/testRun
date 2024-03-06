@ajio
Feature: Ajio Mens Shopping

Background: Launch Ajio
Given Launch Ajio "https://www.ajio.com/"
And Navigate to men and click on Footware

Scenario: Mens Footware sorting
When User clicks on sort by and selects option in dropdown with one dim map
|sort1|highest|
|sort2|discount|
|sort3|lowest|
|sort4|what|
|sort6|rating|
|sort5|relavance|
Then Validate the price list