import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('http://localhost:8080')

WebUI.click(findTestObject('Page_/span_The Web_navbar-toggler-icon (2)'))

WebUI.delay(1)

WebUI.click(findTestObject('Page_/a_Login_1'))

WebUI.setText(findTestObject('Page_Please sign in/input_Username_username'), 'user')

WebUI.setEncryptedText(findTestObject('Page_Please sign in/input_Password_password'), '8SQVv/p9jVScEs4/2CZsLw==')

WebUI.click(findTestObject('Page_Please sign in/button_Sign in'))

WebUI.waitForPageLoad(1)

WebUI.click(findTestObject('Page_/span_The Web_navbar-toggler-icon'))

WebUI.delay(1)

WebUI.click(findTestObject('Page_/a_Add a card'))

WebUI.setText(findTestObject('Page_/input_Name_name'), 'Katalon')

WebUI.setText(findTestObject('Page_/input_Firstname_firstname'), 'Add')

WebUI.setText(findTestObject('Page_/input_Birthdate_birthdate'), '01.01.1990')

WebUI.setText(findTestObject('Page_/input_Localisation_localisation'), 'Kataland')

WebUI.click(findTestObject('Page_/button_Save'))

WebUI.click(findTestObject('Page_/a_Details'))

WebUI.closeBrowser()

