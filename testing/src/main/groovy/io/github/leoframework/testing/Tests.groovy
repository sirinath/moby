package io.github.leoframework.testing

import org.testng.annotations.AfterClass
import org.testng.annotations.AfterGroups
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeGroups
import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Factory
import org.testng.annotations.Test

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
    void cleanUpClass()
}

interface TestGroups {
    @BeforeGroups
    void setUpGroups()

    @AfterGroups
    void cleanUpGroups()
}

interface TestMethod {
    @BeforeMethod
    void setUpMethod()

    @AfterMethod
    void cleanUpMethod()
}

interface TestFactory {
    @Factory
    Object[] createInstances()
}

@Test
interface TestCases {
}

interface TestSetUpAndCleanUp extends TestSuits, TestClass, TestGroups, TestMethod {
}
