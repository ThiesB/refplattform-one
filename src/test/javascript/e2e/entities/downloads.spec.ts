import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Downloads e2e test', () => {

    let navBarPage: NavBarPage;
    let downloadsDialogPage: DownloadsDialogPage;
    let downloadsComponentsPage: DownloadsComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Downloads', () => {
        navBarPage.goToEntity('downloads');
        downloadsComponentsPage = new DownloadsComponentsPage();
        expect(downloadsComponentsPage.getTitle()).toMatch(/refplattformOneApp.downloads.home.title/);

    });

    it('should load create Downloads dialog', () => {
        downloadsComponentsPage.clickOnCreateButton();
        downloadsDialogPage = new DownloadsDialogPage();
        expect(downloadsDialogPage.getModalTitle()).toMatch(/refplattformOneApp.downloads.home.createOrEditLabel/);
        downloadsDialogPage.close();
    });

    it('should create and save Downloads', () => {
        downloadsComponentsPage.clickOnCreateButton();
        downloadsDialogPage.setTitelInput('titel');
        expect(downloadsDialogPage.getTitelInput()).toMatch('titel');
        downloadsDialogPage.setBereichInput('bereich');
        expect(downloadsDialogPage.getBereichInput()).toMatch('bereich');
        downloadsDialogPage.setAnlageURLInput('anlageURL');
        expect(downloadsDialogPage.getAnlageURLInput()).toMatch('anlageURL');
        downloadsDialogPage.customerReferencesSelectLastOption();
        downloadsDialogPage.doctypeSelectLastOption();
        downloadsDialogPage.languageSelectLastOption();
        downloadsDialogPage.save();
        expect(downloadsDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class DownloadsComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-downloads div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class DownloadsDialogPage {
    modalTitle = element(by.css('h4#myDownloadsLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    titelInput = element(by.css('input#field_titel'));
    bereichInput = element(by.css('input#field_bereich'));
    anlageURLInput = element(by.css('input#field_anlageURL'));
    customerReferencesSelect = element(by.css('select#field_customerReferences'));
    doctypeSelect = element(by.css('select#field_doctype'));
    languageSelect = element(by.css('select#field_language'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setTitelInput = function (titel) {
        this.titelInput.sendKeys(titel);
    }

    getTitelInput = function () {
        return this.titelInput.getAttribute('value');
    }

    setBereichInput = function (bereich) {
        this.bereichInput.sendKeys(bereich);
    }

    getBereichInput = function () {
        return this.bereichInput.getAttribute('value');
    }

    setAnlageURLInput = function (anlageURL) {
        this.anlageURLInput.sendKeys(anlageURL);
    }

    getAnlageURLInput = function () {
        return this.anlageURLInput.getAttribute('value');
    }

    customerReferencesSelectLastOption = function () {
        this.customerReferencesSelect.all(by.tagName('option')).last().click();
    }

    customerReferencesSelectOption = function (option) {
        this.customerReferencesSelect.sendKeys(option);
    }

    getCustomerReferencesSelect = function () {
        return this.customerReferencesSelect;
    }

    getCustomerReferencesSelectedOption = function () {
        return this.customerReferencesSelect.element(by.css('option:checked')).getText();
    }

    doctypeSelectLastOption = function () {
        this.doctypeSelect.all(by.tagName('option')).last().click();
    }

    doctypeSelectOption = function (option) {
        this.doctypeSelect.sendKeys(option);
    }

    getDoctypeSelect = function () {
        return this.doctypeSelect;
    }

    getDoctypeSelectedOption = function () {
        return this.doctypeSelect.element(by.css('option:checked')).getText();
    }

    languageSelectLastOption = function () {
        this.languageSelect.all(by.tagName('option')).last().click();
    }

    languageSelectOption = function (option) {
        this.languageSelect.sendKeys(option);
    }

    getLanguageSelect = function () {
        return this.languageSelect;
    }

    getLanguageSelectedOption = function () {
        return this.languageSelect.element(by.css('option:checked')).getText();
    }

    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
