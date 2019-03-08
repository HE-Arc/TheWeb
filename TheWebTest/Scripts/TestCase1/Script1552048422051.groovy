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

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8080/')

WebUI.click(findTestObject('Object Repository/Page_/span_The Web_navbar-toggler-icon'))

WebUI.click(findTestObject('Object Repository/Page_/a_Add a card'))

WebUI.setText(findTestObject('Object Repository/Page_/input_Name_name'), 'Jolie')

WebUI.setText(findTestObject('Object Repository/Page_/input_Firstname_firstname'), 'Kimy')

WebUI.setText(findTestObject('Object Repository/Page_/input_Birthdate_birthdate'), '21.12.1991')

WebUI.setText(findTestObject('Object Repository/Page_/input_Localisation_localisation'), 'Kataloni')

WebUI.click(findTestObject('Object Repository/Page_/button_Save'))

