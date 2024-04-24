# RuleNGN-Spring
RuleNGN for Spring implementation

# Description 
RuleNGN is a impementation of RuleChain design pattern. It provides ability to if/elese code blocks in class implementation as rules.

## Type of rule chains 
Rules can be chain with conditioins like 
*  one rule to match
*  all rules to match

## Type of rules
By default RuleNGNG will stop exuection of next rule if previous rule is failed. This behavour can be overriden with `@OptionRule` annotation.

# Diagram

![RuleNGN](https://github.com/lambovg/RuleNGN-Spring/assets/144920/7218f6f2-2e50-409f-82ef-60405057c151)
