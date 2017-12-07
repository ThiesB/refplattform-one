import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Industries e2e test', () => {

    let navBarPage: NavBarPage;
    let industriesDialogPage: IndustriesDialogPage;
    let industriesComponentsPage: IndustriesComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Industries', () => {
        navBarPage.goToEntity('industries');
        industriesComponentsPage = new IndustriesComponentsPage();
        expect(industriesComponentsPage.getTitle()).toMatch(/refplattformOneApp.industries.home.title/);

    });

    it('should load create Industries dialog', () => {
        industriesComponentsPage.clickOnCreateButton();
        industriesDialogPage = new IndustriesDialogPage();
        expect(industriesDialogPage.getModalTitle()).toMatch(/refplattformOneApp.industries.home.createOrEditLabel/);
        industriesDialogPage.close();
    });

    it('should create and save Industries', () => {
        industriesComponentsPage.clickOnCreateButton();
        industriesDialogPage.setIndustryNameInput('industryName');
        expect(industriesDialogPage.getIndustryNameInput()).toMatch('industryName');
        industriesDialogPage.save();
        expect(industriesDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class IndustriesComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-industries div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class IndustriesDialogPage {
    modalTitle = element(by.css('h4#myIndustriesLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    industryNameInput = element(by.css('input#field_industryName'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setIndustryNameInput = function (industryName) {
        this.industryNameInput.sendKeys(industryName);
    }

    getIndustryNameInput = function () {
        return this.industryNameInput.getAttribute('value');
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
