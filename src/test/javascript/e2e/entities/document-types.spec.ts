import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('DocumentTypes e2e test', () => {

    let navBarPage: NavBarPage;
    let documentTypesDialogPage: DocumentTypesDialogPage;
    let documentTypesComponentsPage: DocumentTypesComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load DocumentTypes', () => {
        navBarPage.goToEntity('document-types');
        documentTypesComponentsPage = new DocumentTypesComponentsPage();
        expect(documentTypesComponentsPage.getTitle()).toMatch(/refplattformOneApp.documentTypes.home.title/);

    });

    it('should load create DocumentTypes dialog', () => {
        documentTypesComponentsPage.clickOnCreateButton();
        documentTypesDialogPage = new DocumentTypesDialogPage();
        expect(documentTypesDialogPage.getModalTitle()).toMatch(/refplattformOneApp.documentTypes.home.createOrEditLabel/);
        documentTypesDialogPage.close();
    });

    it('should create and save DocumentTypes', () => {
        documentTypesComponentsPage.clickOnCreateButton();
        documentTypesDialogPage.setTitelInput('titel');
        expect(documentTypesDialogPage.getTitelInput()).toMatch('titel');
        documentTypesDialogPage.setTypeInput('type');
        expect(documentTypesDialogPage.getTypeInput()).toMatch('type');
        documentTypesDialogPage.save();
        expect(documentTypesDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class DocumentTypesComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-document-types div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class DocumentTypesDialogPage {
    modalTitle = element(by.css('h4#myDocumentTypesLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    titelInput = element(by.css('input#field_titel'));
    typeInput = element(by.css('input#field_type'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setTitelInput = function (titel) {
        this.titelInput.sendKeys(titel);
    }

    getTitelInput = function () {
        return this.titelInput.getAttribute('value');
    }

    setTypeInput = function (type) {
        this.typeInput.sendKeys(type);
    }

    getTypeInput = function () {
        return this.typeInput.getAttribute('value');
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
