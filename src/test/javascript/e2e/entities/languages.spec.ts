import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Languages e2e test', () => {

    let navBarPage: NavBarPage;
    let languagesDialogPage: LanguagesDialogPage;
    let languagesComponentsPage: LanguagesComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Languages', () => {
        navBarPage.goToEntity('languages');
        languagesComponentsPage = new LanguagesComponentsPage();
        expect(languagesComponentsPage.getTitle()).toMatch(/refplattformOneApp.languages.home.title/);

    });

    it('should load create Languages dialog', () => {
        languagesComponentsPage.clickOnCreateButton();
        languagesDialogPage = new LanguagesDialogPage();
        expect(languagesDialogPage.getModalTitle()).toMatch(/refplattformOneApp.languages.home.createOrEditLabel/);
        languagesDialogPage.close();
    });

    it('should create and save Languages', () => {
        languagesComponentsPage.clickOnCreateButton();
        languagesDialogPage.setLanguageNameInput('languageName');
        expect(languagesDialogPage.getLanguageNameInput()).toMatch('languageName');
        languagesDialogPage.save();
        expect(languagesDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class LanguagesComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-languages div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class LanguagesDialogPage {
    modalTitle = element(by.css('h4#myLanguagesLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    languageNameInput = element(by.css('input#field_languageName'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setLanguageNameInput = function (languageName) {
        this.languageNameInput.sendKeys(languageName);
    }

    getLanguageNameInput = function () {
        return this.languageNameInput.getAttribute('value');
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
