# j-screenplay
This is an attempt to learn Screenplay pattern for web UI test automation. The tests are utilizing [XYZ Bank](https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login) application.

Project is using Java with Serenity BDD.

## Executing the tests
To run the sample project, you can either just run the `Tests` test runner class, use maven from the command line or run feature/ scenarioi individually from e.g. IntelliJ.

By default, the tests will run using Chrome. This and other configuration values can be changed in `serenity.conf`
```
$ mvn clean verify
```


## Useful links

### Web pages
https://serenity-bdd.github.io/docs/tutorials/first_test

https://johnfergusonsmart.com/serenity-bdd/

https://medium.com/@simplymahmoud/screenplay-design-pattern-in-test-automation-a9f21ec3ddd3

https://janmolak.com/serenity-bdd-and-the-screenplay-pattern-27819d0db780

### GitHub repositories

https://github.com/serenity-bdd/screenplay-pattern-todomvc

https://github.com/serenity-bdd/serenity-cucumber-starter

https://github.com/jan-molak/jenkins-build-monitor-plugin/tree/master/build-monitor-acceptance/src/main/java/com/smartcodeltd/jenkinsci/plugins/build_monitor

https://github.com/serenity-bdd/bdd-trader
