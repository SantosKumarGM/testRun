@facebook
Feature: Create profile in Facebook 

Background: Launch Facebook
Given Launch Facebook "https://www.facebook.com/"
And Navigate to create new account

Scenario Outline: Create new Account
When Enter the name "<First name>"
When Enter the surname "<Surname>"
When Enter the number "<Mobile number>"
When Enter the password "<New password>"
When Enter the Date "<Date>"
When Enter the Month "<Month>"
When Enter the Year "<Year>"
When Click On Sigh Up
Then Take screenshot "<First name>"
And Validate the url

Examples:
|First name|Surname|Mobile number|New password|Date|Month|Year|
|veni   |v   |58465875  |j12345i|11|Aug|1999|
|Santos   |G.M   |58465875  |j12345i|11|Aug|1999|
|KArthik   |D   |58465875  |j12345i|11|Aug|1997|
|azhagu   |R   |58465875  |j12345i|11|Aug|1995|



