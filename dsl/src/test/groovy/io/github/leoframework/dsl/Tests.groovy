package io.github.leoframework.dsl

import org.testng.annotations.*

interface TestSuits {
    @BeforeSuite
    void setUpSuite()

    @AfterSuite
    void cleanUpSuite()
}

interface TestClass {
    @BeforeClass
    void setUpClass()

    @AfterClass
    cleanUpClass()
}

interface TestGroups {
    @BeforeGroups
    void setUpGroups()

    @AfterGroups
    cleanUpGroups()
}

interface TestMethod {
    @BeforeMethod
    void setUpMethod()

    @AfterMethod
    cleanUpMethod()
}

interface TestFactory {
    @Factory
	public Object[] createInstances()
}


class Test {}
