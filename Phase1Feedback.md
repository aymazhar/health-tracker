### Phase 1 Feedback

#### General Comments
- You're off to a great start!
There are a few things to focus on as outlined below.  
Keep up the good work.

#### Checkstyle
- passes :-)

#### Code coverage
- 70.5% - a bit low - please take more time to carefully test your code!

#### Tests
- all tests pass :-)

#### Documentation
Generally very good but a few errors in `UserProfile` - some setter methods were
not documented as modifying `this`.


#### Implementation
Note that you have an unused local variable in the `BloodPressure` constructor.

We're about to learn how to test two objects for equality.  Be sure to 
update methods such as `BloodPressure.sameBP` and `Vitamins.sameVit` before 
submitting your code in the next phase (as well as making updates to the code that uses these
methods).

Be a little more careful with the visibility of your fields - the `Vaccination` 
class has two public fields which should be private.


**Important Note**: you should have credited the CPSC 210 standard project in
*every* class that you copied - not just the first!
